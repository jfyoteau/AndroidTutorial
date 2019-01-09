package io.github.jfyoteau.androidtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)

        findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                setResult(2) // resultCode
                finish()
            }
        }
    }

}
