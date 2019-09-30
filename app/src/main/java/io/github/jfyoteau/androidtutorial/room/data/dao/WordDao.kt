package io.github.jfyoteau.androidtutorial.room.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.jfyoteau.androidtutorial.room.data.entity.Word

@Dao
interface WordDao {

    @Query("select * from word")
    fun findAll(): LiveData<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

}
