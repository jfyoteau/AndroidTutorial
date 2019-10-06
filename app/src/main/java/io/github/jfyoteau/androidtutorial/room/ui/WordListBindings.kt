package io.github.jfyoteau.androidtutorial.room.ui

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.jfyoteau.androidtutorial.room.data.entity.Word

@BindingAdapter("textAsLong")
fun setTextView(view: TextView, value: Long) {
    view.text = value.toString()
}

@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: List<Word>?) {
    Log.d("AndroidTutorial", "call setItems: $items")
    (listView.adapter as WordAdapter).submitList(items)
}
