package io.github.jfyoteau.androidtutorial.room.ui.room.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.jfyoteau.androidtutorial.room.ui.room.data.entity.Word
import io.github.jfyoteau.androidtutorial.room.ui.room.data.dao.WordDao

@Database(
    entities = [
        Word::class
    ],
    version = 1
)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

}
