package io.github.jfyoteau.androidtutorial.room.data.repository

import androidx.lifecycle.LiveData
import io.github.jfyoteau.androidtutorial.room.data.entity.Word

interface WordRepository {

    fun getWords(): LiveData<List<Word>>

    suspend fun insert(word: Word)

    suspend fun delete(word: Word)

}
