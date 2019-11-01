package io.github.jfyoteau.androidtutorial.room.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.jfyoteau.androidtutorial.room.data.dao.WordDao
import io.github.jfyoteau.androidtutorial.room.data.entity.Word

@Database(
    entities = [
        Word::class
    ],
    version = 1
)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

}
