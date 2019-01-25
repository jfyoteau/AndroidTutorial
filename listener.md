# ビューのイベント

## javaポイ

```kotlin

class Activity : AppCompatActivity() {

    // ...

    fun setupView() {
        val menu1Button: Button = findViewById(R.id.button_menu1)
        menu1Button.setOnClickListener {
            Log.d("AndroidTutorial", "menu 1 is clicked")
        }

    }

}

```

## kotlin方法

```kotlin
class Activity : AppCompatActivity() {

    // ...

    fun setupView() {
        findViewById<Button>(R.id.button_menu1).apply {
            setOnClickListener {
                Log.d("AndroidTutorial", "menu 1 is clicked")
            }
        }
    }

}
```
