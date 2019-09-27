package io.github.jfyoteau.androidtutorial.room.data.database

import androidx.room.Database
import io.github.jfyoteau.androidtutorial.room.data.dao.WordDao
import io.github.jfyoteau.androidtutorial.room.data.entity.Word

@Database(
    entities = [
        Word::class
    ],
    version = 1
)
abstract class ProjectDatabase {

    abstract fun wordDao(): WordDao

}
