package io.github.jfyoteau.androidtutorial.room.ui

import androidx.lifecycle.ViewModel
import io.github.jfyoteau.androidtutorial.room.domain.WordDomain

class RoomViewModel(
    private val domain: WordDomain
) : ViewModel() {

    private val words = this.domain.getWords()

}
