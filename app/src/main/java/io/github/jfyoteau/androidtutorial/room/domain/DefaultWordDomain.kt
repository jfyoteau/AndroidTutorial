package io.github.jfyoteau.androidtutorial.room.domain

import androidx.lifecycle.LiveData
import io.github.jfyoteau.androidtutorial.room.data.entity.Word
import io.github.jfyoteau.androidtutorial.room.data.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DefaultWordDomain(
    private val repository: WordRepository
) : WordDomain {

    override fun getWords(): LiveData<List<Word>> {
        return this.repository.getWords()
    }

    override fun insertWord(scope: CoroutineScope, word: Word) {
        scope.launch {
            repository.insert(word)
        }
    }

}
