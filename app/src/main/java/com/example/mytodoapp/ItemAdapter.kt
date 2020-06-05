package com.example.mytodoapp

import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodoapp.db.entity.TodoItem

class ItemAdapter(private val items : MutableList<TodoItem>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemAdapter.ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            var view : View= holder.itemView
            view.let {
                val tvName : TextView = it.findViewById(R.id.tvName)
                tvName.text = items[position].name
                val tvDesc : TextView = it.findViewById(R.id.tvDescription)
                tvDesc.text = items[position].description
                val tvCategory : TextView = it.findViewById(R.id.tvCategory)
                tvCategory.text = items[position].category
            }
    }
    public class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun updateItems(updatedList : List<TodoItem>){
        items.clear()
        items.addAll(updatedList)
        notifyDataSetChanged()
    }

}