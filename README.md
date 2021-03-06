# AndroidTutorial
Android チュートリアル

1. プロジェクトの作成  
1.1 プロジェクトのテンプレート：　`Add No Activity`  
1.2 gradleの設定  
　・buildToolVersion: `28.0.3`  
　・ソースのコンパティビリティ： `1.8`  
　・kotlinライブラリのバージョン：`kotlin-stdlib-jdk8`  
　・ソースコードのフォーマット：  
　　　・Kotlin: `Set from... > Prefefined Style > Kotlin style guide`  
　　　・XML: `Set from... > Prefefined Style > Android`  
1.3 AndroidX（Jetpack）の対応  
　・`Refactor > Migrate to AndroidX`メニューでAndroidXに移行する  
　・`app/build.gradle`ファイルに下記の依存を追加する  
　　　・`androidTestImplementation 'androidx.test:core:1.0.0'`  
　　　・`androidTestImplementation 'androidx.test.ext:junit:1.0.0'`  
　・deprecatedパッケージを直す  
　　　・ExampleInstrumentedTest.kt:  
　　　　　・`import androidx.test.runner.AndroidJUnit4`を`import androidx.test.ext.junit.runners`に変更する  
　　　　　・`import androidx.test.InstrumentationRegistry`を`import import androidx.test.core.app.ApplicationProvider`に変更する  
　　　　　・`val appContext = InstrumentationRegistry.getTargetContext()`を`val appContext: Application = ApplicationProvider.getApplicationContext()`に変更する

2. メインアクティビティの作成  
　・テンプレート：`Empty Activity`  
　・Activity Name: `MainActivity`  
　・Generate Layout File: `ON`  
　・Layout Name: `main_activity`  
　・Launcher Activity: `ON`  
　・Backwards Compatibility (AppCompat): `ON`  
　・Package Name: `io.github.jfyoteau.androidtutorial.ui`  
　・Source Language: `Kotlin`  
2.1 `main_activity.xml`のレイアウトを変更する  
・`ConstraintLayout`を`LinearLayout`へ変更する  
・`LinearLayout`の`orientation`プロパティを`vertical`に設定する  
2.2 テキストビューと2つのボタンを追加する  
