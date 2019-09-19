package io.github.jfyoteau.androidtutorial.room.ui.room.data.database

import android.content.Context
import androidx.room.Room

interface DatabaseResource {

    fun getDatabase(content: Context): ProjectDatabase

    companion object : DatabaseResource {

        @Volatile
        private var db: ProjectDatabase? = null

        override fun getDatabase(context: Context) : ProjectDatabase {
            val dbInstance = this.db
            if (dbInstance != null) {
                return dbInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDatabase::class.java,
                    "project_db"
                ).build()
                db = instance
                return instance
            }
        }

    }

}
