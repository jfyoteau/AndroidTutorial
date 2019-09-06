package io.github.jfyoteau.androidtutorial

import android.util.Log
import android.widget.Toast

class MainViewModel(private val activity: MainActivity) {

    fun doMenu1Action() {
        Log.d("AndroidTutorial", "menu 1 is clicked")
    }

    fun doMenu2Action() {
        Toast.makeText(this.activity, R.string.main_toast_message, Toast.LENGTH_SHORT).show()
    }

    fun doMenu3Action() {
        activity.startOtherActivity()
    }

    fun doMenu4Action() {
        activity.startActivityWithResult()
    }

    fun doMenu5Action() {
        activity.startFragmentActivity()
    }

    fun doMenu6Action() {
        activity.startRecyclerViewActivity()
    }

}
