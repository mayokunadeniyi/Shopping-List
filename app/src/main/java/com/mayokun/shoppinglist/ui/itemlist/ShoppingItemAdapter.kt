package com.mayokun.shoppinglist.ui.itemlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mayokun.shoppinglist.data.model.ShoppingItem
import com.mayokun.shoppinglist.databinding.ItemListBinding

/**
 * Created by Mayokun Adeniyi on 2019-11-26.
 */

class ShoppingItemAdapter: ListAdapter<ShoppingItem,ShoppingItemAdapter.ViewHolder>(ShoppingListDiffUtils()){
    /**
     * Creates and returns ViewHolder on request from the RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    /**
     * This function takes a ViewHolder, gets a shopping item at a position and binds the
     *data to the ViewHolder
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){

        /**
         * This function takes in a shopping item and uses DataBinding to attach each view in the item_list.xml layout to data
         * from the shopping item.
         */
        fun bind(item: ShoppingItem){
            binding.itemName.text = item.itemName
            binding.itemQuantity.text = item.itemQuantity.toString()
            binding.itemDateAdded.text = item.dateItemCreated
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}

class ShoppingListDiffUtils: DiffUtil.ItemCallback<ShoppingItem>(){
    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem == newItem
    }

}