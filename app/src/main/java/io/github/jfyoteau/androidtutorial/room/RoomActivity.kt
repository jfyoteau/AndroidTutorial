package io.github.jfyoteau.androidtutorial.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.jfyoteau.androidtutorial.R
import io.github.jfyoteau.androidtutorial.room.ui.RoomFragment

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room_activity)
        if (savedInstanceState == null) {
            this.supportFragmentManager.beginTransaction()
                .replace(R.id.container, RoomFragment())
                .commitNow()
        }
    }

}
