package io.github.jfyoteau.androidtutorial.room.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.jfyoteau.androidtutorial.room.domain.WordDomain
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val domain: WordDomain
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(RoomViewModel::class.java) -> RoomViewModel(domain)
                else -> throw IllegalArgumentException("Unknown modelClass: ${modelClass.name}")
            }
        } as T

}
