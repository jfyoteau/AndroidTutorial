package io.github.jfyoteau.androidtutorial.room.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.jfyoteau.androidtutorial.databinding.RoomItemBinding
import io.github.jfyoteau.androidtutorial.room.data.entity.Word

class WordAdapter(
    private val viewModel: RoomViewModel
) : ListAdapter<Word, WordAdapter.ViewHolder>(TaskDiffCallback()) {

    // =======================================================================
    // region ViewHolder
    // =======================================================================

    class ViewHolder private constructor(
        private val binding: RoomItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: RoomViewModel, item: Word) {
            binding.viewModel = viewModel
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {

            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RoomItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }

        }

    }

    // endregion ViewHolder

    // =======================================================================
    // region アイテムの比較
    // =======================================================================

    class TaskDiffCallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }

    // endregion アイテムの比較

    // =======================================================================
    // region アダプター
    // =======================================================================

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("AndroidTutorial", "[WordAdapter] onBindViewHolder - position: $position")
        val item = getItem(position)
        Log.d("AndroidTutorial", "[WordAdapter] onBindViewHolder - item: ${item.id}")
        holder.bind(this.viewModel, item)
    }

    // endregion アダプター

}