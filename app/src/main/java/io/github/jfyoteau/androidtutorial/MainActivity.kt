package io.github.jfyoteau.androidtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupViews()
    }

    private fun setupViews() {
        val menu1Button: Button = findViewById(R.id.button_menu1)
        menu1Button.setOnClickListener {
            Log.d("AndroidTutorial", "menu 1 is clicked")
        }

        findViewById<Button>(R.id.button_menu2).apply {
            setOnClickListener {
                Toast.makeText(this@MainActivity, R.string.main_toast_message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
