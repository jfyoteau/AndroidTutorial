package io.github.jfyoteau.androidtutorial.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.jfyoteau.androidtutorial.R

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_activity)

        if (savedInstanceState == null) {
            val fragment = RecyclerViewFragment()

            val ft = this.supportFragmentManager.beginTransaction()
            ft.add(R.id.container, fragment)
            ft.commitNow()
        }
    }
}
