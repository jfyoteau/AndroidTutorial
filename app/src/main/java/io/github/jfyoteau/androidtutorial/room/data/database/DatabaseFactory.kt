package io.github.jfyoteau.androidtutorial.room.data.database

import android.content.Context
import androidx.room.Room

interface DatabaseFactory {

    fun getDatabase(context: Context): ProjectDatabase

    companion object : DatabaseFactory {

        @Volatile
        private var db: ProjectDatabase? = null

        override fun getDatabase(context: Context): ProjectDatabase {
            val dbInstance = this.db
            if (dbInstance != null) {
                return dbInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, // アプリケーションのコンテキスト
                    ProjectDatabase::class.java,
                    "project_db" // データバース名
                ).build()
                db = instance
                return instance
            }
        }

    }

}
