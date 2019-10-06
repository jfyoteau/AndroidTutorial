package io.github.jfyoteau.androidtutorial.room.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jfyoteau.androidtutorial.room.data.entity.Word
import io.github.jfyoteau.androidtutorial.room.domain.WordDomain

class RoomViewModel(
    private val domain: WordDomain
) : ViewModel() {

    /**
     * Two-way databinding: MutableLiveDataを利用する。
     */
    val word = MutableLiveData<String>().apply { value = "" }

    val words = this.domain.getWords()

    fun addWord() {
        Log.d("AndroidTutorial", "[addWord] word to add: ${word.value}")
        var word = this.word.value ?: return
        if (word.isEmpty()) {
            word = "aaa"
        }

        val newWord = Word(word = word)
        this.domain.addWord(this.viewModelScope, newWord)
    }

    fun removeWord(word: Word) {
        this.domain.removeWord(this.viewModelScope, word)
    }

}
