package io.github.jfyoteau.androidtutorial

import android.content.Intent
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("AndroidTutorial", "requestCode: $requestCode - resultCode: $resultCode")
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

        findViewById<Button>(R.id.button_menu3).apply {
            setOnClickListener {
                startOtherActivity()
            }
        }

        findViewById<Button>(R.id.button_menu4).apply {
            setOnClickListener {
                startActivityWithResult()
            }
        }
    }

    private fun startOtherActivity() {
        val intent = Intent(this, OtherActivity::class.java)
        startActivity(intent)
    }

    private fun startActivityWithResult() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivityForResult(intent, 1)
    }

}
