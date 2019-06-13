# Kotlin coroutine

## Coroutineの説明 (wikipedia)

`コルーチン`（英: `co-routine`）とはプログラミングの構造の一種。サブルーチンがエントリーからリターンまでを一つの処理単位とするのに対し、コルーチンはいったん処理を中断した後、続きから処理を再開できる。接頭辞 co は協調を意味するが、複数のコルーチンが中断・継続により協調動作を行うことによる。

サブルーチンと異なり、状態管理を意識せずに行えるため、協調的処理、イテレータ、無限リスト、パイプなど、継続状況を持つプログラムが容易に記述できる。

コルーチンはサブルーチンを一般化したものと考えられる。コルーチンをサポートする言語には `Modula-2`、`Simula`、`Icon`、`Lua`、`C#`、`Limbo` などがある。マルチスレッドで原理的には同じことができるため、現在はそちらが使われるケースが多い。これはマルチスレッドであれば直接OSの支援を受けられることや、エントリー/リターンの構造を変えずにコードを多重化できるので、過去の言語との親和性が良いなどが理由である。ただし、マルチスレッドの場合プログラマが同期制御を行わなければならないので、コルーチンのような簡易さはない。 

## Kotlinのcoroutine

`Coroutine`がライトスレッドです。`Coroutine`が`launch`coroutineビルダで`CoroutineScope`に実行します。

```kotlin
fun doProcess() {
    GlobalScope.launch { // バックグランドで新しいcoroutineを立ち上げする
        delay(1000L) // 1秒間待つ。 delay が suspend 関数ですが、
        　　　　　　　// メインスレッドをブロックしない
        println("world!")
    }
    println("Hello,")
    Thread.sleep(2000) // 2秒間JVMをブロックする
}
```

上記のコードに`GlobalScope`で実行します。`GlobalScope`のライフサイグルがプログラムのライフサイグルと同じです。

出力：

```
Hello,
```
1秒後に
```
Hello,
world!
```
1秒後に、`doProcess`関数が完了します。

## ジョブの完了を待つ

`coroutine`の完了を待つ為に、`job.join()`関数を利用します。    
`coroutine`が`job`です。

```kotlin
val job = GlobalScope.launch {
    delay(1000L) // 1秒間待つ。
    println("world!")
}
println("Hello,")
job.join() // jobの完了を待つ
```

## ジョブのキャンセル

ジョブをキャンセルする為に`job.cancel()`関数を利用します。

```kotlin
val job = GlobalScope.launch {
    repeat(1000) { i ->
        println("I'm sleeping $i ...")
        delay(500L)
    }
}
delay(1300L)
println("I'm tired of waiting!")
job.cancel() // ジョブをキャンセルする
job.join() // ジョブの完了を待つ
println("main: now I can quit")
```

出力：

```
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
main: Now I can quit.
```

ただ、ジョブがキャンセルできない場合は処理が止まらない。

```kotlin
val startTime = System.currentTimeMillis()
val job = launch(Dispatchers.Default) {
    var nextPrintTime = startTime
    var i = 0
    while (i < 5) { // 計算のループ
        if (System.currentTimeMillis() >= nextPrintTime) {
            println("job: I'm sleeping ${i++} ...")
            nextPrintTime += 500L
        }
    }
}
delay(1300L)
println("main: I'm tired of waiting!")
job.cancelAndJoin() // ジョブをキャンセルし、完了を待つ
println("main: Now I can quit.")
```

出力：

```
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
job: I'm sleeping 3 ...
job: I'm sleeping 4 ...
main: Now I can quit.
```

キャンセルさせるジョブを作成するために`coroutine`の`isActive`プロパティを利用できます。  
前のコードが下記のコードになります。

```kotlin
val startTime = System.currentTimeMillis()
val job = launch(Dispatchers.Default) {
    var nextPrintTime = startTime
    var i = 0
    while (isActive) { // キャンセルされる計算ループ
        if (System.currentTimeMillis() >= nextPrintTime) {
            println("job: I'm sleeping ${i++} ...")
            nextPrintTime += 500L
        }
    }
}
delay(1300L)
println("main: I'm tired of waiting!")
job.cancelAndJoin() // ジョブをキャンセルし、完了を待つ
println("main: Now I can quit.")
```

出力：

```
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
main: Now I can quit.
```

## スコープビルダ

`coroutine`スコープを作成する為に複数の方法があります。
- `runBlocking`: スレッドをブロックする
- `coroutineScope`: スレッドをブロックしない

Androidの場合は`coroutineScope`の方を利用します。

## androidで`coroutine`の使い方

モジュールで下記のdependenciesを追加します

```groovy
dependencies {
  ...
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:x.x.x"
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:x.x.x"
}
```

## callbackパターン

### 普通の方法

```kotlin
// 遅いリクエストがcallbackを利用する
@UiThread
fun makeNetworkRequest() {
    // 遅いネットワークリクエストが別のスレッドに実行する
    slowFetch { result ->
        // 結果がある時に、このcallbackが結果を取得する
        show(result)
    }
    // ネットワークの結果を待たないので、makeNetworkRequest() がすぐに完了します。
}
```

### coroutineの方法

`coroutine`を利用する場合はcallbackが不要になります。

```kotlin
// 遅いリクエストがcoroutineを利用する
@UiThread
suspend fun makeNetworkRequest() {
    // slowFetchが他の「suspend」関数です。
    // マインスレッドをブロックする代わりに、結果があるまで結果をsuspendします。
    val result = slowFetch()
    // 結果を取得した後に次のステップを実行します。
    show(result)
}

// slowFetchがcoroutineで「main-safe」です
suspend fun slowFetch(): SlowResult {
    // ...
}
```

備考：  
`suspend`関数が`coroutine`スコープに実行する必要があります。

`suspend`キーワードが`async`と同じパターンですが、`suspend`関数を呼び出す時にkotlinが自動で`await()`を呼び出します。

```kotlin
// 遅いリクエストがcoroutineを利用する
@UiThread
suspend fun makeNetworkRequest() {
    // slowFetchが他の「suspend」関数です。
    // マインスレッドをブロックする代わりに、結果があるまで結果をsuspendします。
    val result = slowFetch()
    val another = anotherFetch()
    // 結果を保存する
    database.save(slow, another)
}

// slowFetchがcoroutineで「main-safe」です
suspend fun slowFetch(): SlowResult {
    // ...
}

// anotherFetchがcoroutineで「main-safe」です
suspend fun anotherFetch(): AnotherResult {
    // ...
}
```

最初に`slowFetch()`関数を実行します。結果を取得した後に`anotherFetch()`関数を呼び出します。

## 非同期： async

上のサンプルに`slowFetch()`と`anotherFetch()`が次々に実行されます。

非同期で関数を実行したい時に`async`を利用します。

```kotlin
// 遅いリクエストがcoroutineを利用する
@UiThread
suspend fun makeNetworkRequest() {
    // slowFetchが他の「suspend」関数です。
    // マインスレッドをブロックする代わりに、結果があるまで結果をsuspendします。
    val result = async {
        slowFetch()
    }
    val another = async {
        anotherFetch()
    }
    // 結果を保存する
    database.save(slow.await(), another.await())
}

// slowFetchがcoroutineで「main-safe」です
suspend fun slowFetch(): SlowResult {
    // ...
}

// anotherFetchがcoroutineで「main-safe」です
suspend fun anotherFetch(): AnotherResult {
    // ...
}
```
