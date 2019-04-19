package io.github.jfyoteau.androidtutorial

import android.os.Bundle
import android.widget.Button

class ResultActivity : BaseActivity() {

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
