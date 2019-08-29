package io.github.jfyoteau.androidtutorial.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.jfyoteau.androidtutorial.R

class MyRecyclerViewAdapter(
    private val data: List<String>
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = this.data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.my_recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val info = this.data[position]

        val itemView = holder.itemView
        val nameView = itemView.findViewById<TextView>(R.id.name)
        nameView?.text = info
    }

}
