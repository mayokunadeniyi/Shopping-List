package com.mayokun.shoppinglist.ui.itemlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.data.model.ShoppingItem

/**
 * Created by Mayokun Adeniyi on 2019-11-26.
 */

class ShoppingItemAdapter: RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder>(){
    var data = listOf<ShoppingItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val _itemName: TextView = itemView.findViewById(R.id.item_name)
        val _itemQuantity: TextView = itemView.findViewById(R.id.item_quantity)
        val _dateAdded: TextView = itemView.findViewById(R.id.item_date_added)

        fun bind(item: ShoppingItem){
            _itemName.text = item.itemName
            _itemQuantity.text = item.itemQuantity.toString()
            _dateAdded.text = item.dateItemCreated
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list
                ,parent,false)
                return ViewHolder(view)
            }
        }
    }
}