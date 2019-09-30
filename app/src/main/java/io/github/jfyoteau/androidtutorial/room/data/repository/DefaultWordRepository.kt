package io.github.jfyoteau.androidtutorial.room.data.repository

import androidx.lifecycle.LiveData
import io.github.jfyoteau.androidtutorial.room.data.dao.WordDao
import io.github.jfyoteau.androidtutorial.room.data.entity.Word

class DefaultWordRepository(
    private val dao: WordDao
) : WordRepository {

    override fun getWords(): LiveData<List<Word>> {
        return this.dao.findAll()
    }

    override suspend fun insert(word: Word) {
        this.dao.insert(word)
    }

    override suspend fun delete(word: Word) {
        this.dao.delete(word)
    }
}
