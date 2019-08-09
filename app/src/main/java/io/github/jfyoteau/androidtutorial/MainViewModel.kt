package io.github.jfyoteau.androidtutorial

import android.content.Context
import android.widget.Toast

class MainViewModel(private val context: Context) {

    fun doMenu2Action() {
        Toast.makeText(this.context, R.string.main_toast_message, Toast.LENGTH_SHORT).show()
    }

}
