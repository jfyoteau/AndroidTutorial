package io.github.jfyoteau.androidtutorial.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.jfyoteau.androidtutorial.room.ui.room.RoomFragment

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RoomFragment.newInstance())
                .commitNow()
        }
    }

}
