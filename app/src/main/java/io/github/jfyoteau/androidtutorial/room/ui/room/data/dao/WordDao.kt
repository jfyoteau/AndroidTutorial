package io.github.jfyoteau.androidtutorial.room.ui.room.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import io.github.jfyoteau.androidtutorial.room.ui.room.data.entity.Word

@Dao
interface WordDao {

    fun findAll(): LiveData<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

}

