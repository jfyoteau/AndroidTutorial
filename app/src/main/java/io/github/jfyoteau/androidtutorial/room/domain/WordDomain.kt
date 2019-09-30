package io.github.jfyoteau.androidtutorial.room.domain

import androidx.lifecycle.LiveData
import io.github.jfyoteau.androidtutorial.room.data.entity.Word
import kotlinx.coroutines.CoroutineScope

interface WordDomain {

    fun getWords(): LiveData<List<Word>>

    fun insertWord(scope: CoroutineScope, word: Word)

}
