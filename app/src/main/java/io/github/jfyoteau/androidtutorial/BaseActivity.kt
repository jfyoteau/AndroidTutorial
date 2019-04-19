package io.github.jfyoteau.androidtutorial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("AndroidTutorial", "[${this.javaClass.simpleName}] onCreate")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("AndroidTutorial", "[${this.javaClass.simpleName}] onActivityResult")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("AndroidTutorial", "[${this.javaClass.simpleName}] onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("AndroidTutorial", "[${this.javaClass.simpleName}] onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("AndroidTutorial", "[${this.javaClass.simpleName}] onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("AndroidTutorial", "[${this.javaClass.simpleName}] onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("AndroidTutorial", "[${this.javaClass.simpleName}] onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AndroidTutorial", "[${this.javaClass.simpleName}] onDestroy")
    }

}
