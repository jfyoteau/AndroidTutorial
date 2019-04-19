1. `MyFragmentActivity` アクティビティを作成する

2. アクティビティのレイアウトにフラグメントのコンテナーを追加する
```xml
<FrameLayout
  android:id="@+id/fragment_container"
  android:layout_width="match_parent"
  anndroid:layout_height="match_parent" />
```

3. `SplashFragment` フラグメントを作成する

4. アクティビティに `SPlashFragment` を表示する

```kotlin
class MyFragmentActivity : AppCompatActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_fragment_activity)

        if (savedInstanceState == null) {
            showSplash()
        }
    }

    /**
     * スプラッシュフラグメントを表示する
     * <p>フラグメントがバックスタックに追加しません</p>
     */
    private fun showSplash() {
        val fm = this.supportFragmentManager
        fm.beginTransaction().apply {
            val fragment = SplashFragment()
            add(R.id.fragment_container, fragment)
        }.commitNow() // すぐにフラグメントを表示する
    }

}
```

5. MainActivityのレイアウトに新しいボタンを追加する

    - name: button_menu5
    - label: フラグメント
    - 操作：MyFragmentActivityを呼び出す
