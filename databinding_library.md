# データ バインディング ライブラリ

データ バインディング ライブラリは、プログラムではなく宣言形式を使用して、レイアウト内の UI コンポーネントをアプリのデータソースにバインドできるサポート ライブラリです。

多くの場合、レイアウトは UI フレームワーク メソッドを呼び出すコードを使用してアクティビティで定義されます。  
たとえば、次のコードは `findViewById()` を呼び出して `TextView` ウィジェットを検索し、`viewModel` 変数の `userName` プロパティにバインドします。

```kotlin
findViewById<TextView>(R.id.sample_text).apply {
    text = viewModel.userName
}
```

次の例は、データ バインディング ライブラリを使用して、レイアウト ファイル内でウィジェットにテキストを直接割り当てる方法を示しています。この場合、上記の Java コードを呼び出す必要はありません。代入式で `@{}` 構文を使用することに注意してください。

```xml
<TextView
    android:text="@{viewmodel.userName}" />
```

## 設定

各々のgradleモジュールの`build.gradle`に下記のコードを追加します。

```groovy
android {
    ...
    dataBinding {
        enabled true
    }
}
```

## レイアウトの定義

式言語を使用すると、変数をレイアウト内のビューに関連付ける式を記述できます。データ バインディング ライブラリは、レイアウト内のビューとデータ オブジェクトをバインドするために必要なクラスを自動的に生成します。ライブラリにはインポートや変数などの機能があり、レイアウトで使用できる機能も用意されています。

ライブラリのこれらの機能は、既存のレイアウトとシームレスに統合されます。たとえば、式で使用できるバインディング変数は、UI レイアウトのルート要素に隣接する data 要素内に定義されます。次の例に示すように、両方の要素が `layout` タグで囲まれています。

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
    </data>
    <ConstraintLayout... /> <!-- UIレイアウトのルートエレメント -->
</layout>
```

## レイアウトのデータ（入力）

レイアウトのデータを定義するために `data` タグに `variable` タグを利用します。  

書き方：

```xml
<variable name="変数名" type="変数型" />
```

サンプル：

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="viewmodel"
            type="com.myapp.data.ViewModel" />
    </data>
    <ConstraintLayout... /> <!-- UIレイアウトのルートエレメント -->
</layout>
```

## データの使い方

データを利用する時に `@{変数名}` のフォームを利用します。

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="name"
            type="String" />
    </data>
    <FrameLayout>
        <TextView
            android:id="@+id/label"
            android:text="@{name}"/>
    </FrameLayout>
</layout>
```

## イベントのハンドル

イベントをハンドルするために2つの方法があります。
- Method reference
- リスナーバインディング

### Method refence

イベントの定義がオブジェクトの関数をマッチする必要があります。

下記のビューモデルがあります。
```kotlin
package jp.tcmobile.project

class MyViewModel {
    fun onClick(view: View) {
        // ...
    }
}
```

ボタンをクリックする時にビューモデルの`onClick`関数を呼び出します。  
その為に、下記のレイアウトを書きます。

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="viewModel"
            type="jp.tcmobile.project.MyViewModel" />
    </data>
    <FrameLayout>
        <Button android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Click me"
           android:onClick="@{viewModel::onClick}"/>
    </FrameLayout>
</layout>
```

### リスナーバインディング

`lambda`を使います

下記のビューモデルがあります。
```kotlin
package jp.tcmobile.project

class MyViewModel {
    fun onClick() {
        // ...
    }
}
```

ボタンをクリックする時にビューモデルの`onClick`関数を呼び出します。  
その為に、下記のレイアウトを書きます。

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="viewModel"
            type="jp.tcmobile.project.MyViewModel" />
    </data>
    <FrameLayout>
        <Button android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Click me"
           android:onClick="@{() -> viewModel.onClick()}"/>
    </FrameLayout>
</layout>
```

## バインディングアダプター

ビューに新しい属性を追加できます。そのために、kotlin 関数を実装する。

```kotlin
@BindingAdapter("visible_or_gone")
fun visibleOrGone(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}
```

レイアウトのビューに `visible_or_gone` 属性を追加します。

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="viewModel"
            type="jp.tcmobile.project.MyViewModel" />

        <variable
            name="visible"
            type="boolean" />
    </data>
    <FrameLayout>
        <Button android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Click me"
           android:onClick="@{() -> viewModel.onClick()}"
           app:visible_or_gone="@{visible}"/>
    </FrameLayout>
</layout>
```

`visible`変数が`true`の場合はボタンを表示します。`visible`が`false`の場合はボタンに非表示します。

## データバインディングのクラス

モジュールをコンパイルする時にandroidのgradleプラグインがデーターバインディングクラスを生成します。

クラス名はレイアウトの名前と`Binding`です。

例）レイアウト名が`my_screen.xml`です。データバインディングのクラス名が`MyScreenBinding`です。

### データバインディングのクラスのインスタンスの作成方法

データバインディングのクラスのインスタンスを作成する為に、データバインディングの`inflate`関数を使います。

```kotlin
val layoutInflater = LayoutInflater.from(context) // contextが Context のインスタンス（アクティビティ）です。
val databinding = MyScreenBinding.inflate(layoutInflater)
```

`inflate`関数が複数パラメタがありますが、ここに紹介しません。

### レイアウトの変数の設定方法

下記のレイアウトがあります。

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="viewModel"
            type="jp.tcmobile.project.MyViewModel" />

        <variable
            name="visible"
            type="boolean" />
    </data>
    <FrameLayout>
        <Button android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="Click me"
           android:onClick="@{() -> viewModel.onClick()}"
           app:visible_or_gone="@{visible}"/>
    </FrameLayout>
</layout>
```

レイアウトを表示する為に`viewmModel`変数と`visible`変数が必要です。  
データバインディングクラスを生成する時にデータバインディングクラスに`viewModel`プロパティと`visible`プロパティを作成します。

```kotlin
val layoutInflater = LayoutInflater.from(context) // contextが Context のインスタンス（アクティビティ）です。
val databinding = MyScreenBinding.inflate(layoutInflater)
databinding.viewModel = ViewModel()
databinding.visible = true
```

### データバインディングクラスのビュー

データバインディングクラスと連携するビューをアクセスするために`root`プロパティを利用します。

```kotlin
val layoutInflater = LayoutInflater.from(context) // contextが Context のインスタンス（アクティビティ）です。
val databinding = MyScreenBinding.inflate(layoutInflater)
val view = databinding.root
```

## 監視可能なデータ オブジェクトの使用

データ バインディング ライブラリは、データの変更を簡単に監視できるクラスとメソッドを提供します。基礎となるデータソースが変更されたときに UI の更新を気にする必要はありません。変数やそれらのプロパティを監視可能にすることができます。ライブラリを使用すると、オブジェクト、フィールド、またはコレクションを監視できるようになります。

その為に`LiveData`のインスタンスを利用します。

```kotlin
class MyViewModel {
    val name = MutableLiveData<String>()
}

class MyActivity {
    override fun onCreate(savedBundle: Bundle?) {
        val databinding: MyScreenBinding = DataBindingUtil.setContentView(this, R.layout.my_screen)
        databinding.lifecycle = this // LiveDataを利用するので、必要です。
        databinding.viewModel = ViewModel()
    }
}
```

my_screen
```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="viewModel"
            type="jp.tcmobile.project.MyViewModel" />
    </data>
    <FrameLayout>
        <TextView android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:text="@{viewModel.name}" />
    </FrameLayout>
</layout>
```

`LiveData`を利用するから、`LiveData`オブジェクトを更新する時にTextViewが自動で更新されます。
