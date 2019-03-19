# Fragment

## 解題

フラグメントはアクティビティのサブコンポーネントです。  
フラグメントがビューを管理しますが、ビューを表示するが必要ではありません。  
※フラグメントがビューをもっていないことが可能です。

フラグメントマネージャーがトランザクションでフラグメントのlifecycleを管理します。  
下記の操作が出来ます

- 追加（add）
- 削除（remove）
- 変更（replace）
- 表示（show）
- 非表示（hide）
- つける（attach）
- 切り離す（detach）

## フラグメントの使い方

下記のケースにフラグメントを使います。

- 画面を表示する時
- 部分を再利用する時
- 別の画面へ移動する場合は画面のアニメーションを管理したい時
- 画面のバックスタックを管理する時

## API

2つのAPIが存在します。
- android frameworkの中 (API 28からdeprecatedになりました)
- jetpack library (このライブラリを使います)

### android frameworkのパッケージ

```
android.app
```

### jetpack libraryのパッケージ

```
androidx.fragment.app
```

## dependency

`app/build.gradle`に下記のdependencyを追加する

```groovy
implementation 'androidx.fragment:fragment-ktx:1.0.0'
```

## フラグメントの定義

```kotlin
import androidx.fragment.app.Fragment

class MyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.my_fragment, container, false)

}
```

※フラグメントでデフォルトコンストラクタを定義が必要です。

## 使い方

フラグメントマネージャーでコンテナにフラグメントを追加します。  
コンテナが`ViewGroup`クラスを継承します。  
例えば、`FrameLayout`, `LinearLayout`

フラグメントマネージャーを取得する為に、`FragmentActivity`クラスの`getSupportFragmentManager()`関数を利用します。

### アクティビティ

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            // フラグメントを追加する
            showFragment()
        }
    }

    private fun showFragment() {
        val fm = this.supportFragmentManager
        fm.beginTransaction().apply {
            val fragment = MyFragment()
            add(R.id.fragment_container, fragment)
        }.commit()
    }

}
```

### アクティビティのレイアウト

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#808080"
    tools:context=".MainActivity">

    <FrameLayout
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</FrameLayout>

```

## フラグメントマネージャーのバックスタックにフラグメントの追加

フラグメントマネージャーのバックスタックにフラグメントを追加する為に、`FragmentTransaction`の`addToBackStack(name: String?)`メソッドを利用します。

```kotlin
    private fun showFragmentAndAddToBackstack() {
        val fm = this.supportFragmentManager
        fm.beginTransaction().apply {
            val fragment = MyFragment()
            add(R.id.fragment_container, fragment)
            addToBackStack(null) // フラグメントマネージャーのバックスタックを追加する。null: 名前がなし
        }.commit()
    }
```

## バックスタックのバック

バックスタックの前のステートへ戻る為に`FragmentManager`の`popBackStack()`関数を利用します

```kotlin
    private fun back() {
        val fm = this.supportFragmentManager
        fm.popBackStack();
    }

```

バックスタックの特別のポイントへ戻る時に`FragmentManager`の`popBackStack(name: String?, flag: Int)`

`name`は`Transition.addToBackStack`のメソッドの`name`パラメタの値です。  
`null`の場合はすべての

```kotlin
    private fun back(name: String?, isIncluded: Boolean) {
        val fm = this.supportFragmentManager
        fm.popBackStack(
            name,
            if (isIncluded)
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            else
                0
        );
    }
```

## アニメーション

画面の移動の間にアニメーションを行う時に`FragmentTransaction`の`setCustomAnimations(enter: Int, exit: Int, popEnter: Int, popExit: Int)`

`enter`、`exit`、`popEnter`、`popExit`がアニメーションのリソースIDです

```kotlin
    private fun showFragmentWithAnimation() {
        val fm = this.supportFragmentManager
        fm.beginTransaction().apply {
            val fragment = MyFragment()
            setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.popEnter,
                R.anim.popExit
            )
            replace(R.id.fragment_container, fragment)
            addToBackStack(null) // フラグメントマネージャーのバックスタックを追加する。null: 名前がなし
        }.commit()
    }

```
