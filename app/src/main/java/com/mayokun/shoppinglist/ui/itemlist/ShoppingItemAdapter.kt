package com.mayokun.shoppinglist.ui.itemlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.data.model.ShoppingItem
import com.mayokun.shoppinglist.databinding.ItemListBinding
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModel
import kotlinx.coroutines.*

/**
 * Created by Mayokun Adeniyi on 2019-11-26.
 */

class ShoppingItemAdapter(val clickListener: ShoppingItemListener):
    ListAdapter<ShoppingItem,ShoppingItemAdapter.ViewHolder>(ShoppingListDiffUtils()){
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
        holder.bind(getItem(position),clickListener)
    }

    class ViewHolder private constructor(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){

        /**
         * This function takes in a shopping item and uses DataBinding to attach each view in the item_list.xml layout to data
         * from the shopping item.
         */
        fun bind(item: ShoppingItem, clickListener: ShoppingItemListener){
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
            binding.deleteBtn.setOnClickListener { view ->
                deleteItem(view, item)
            }

        }

        /**
         * This function is called when the delete button is pressed.
         * It takes in a View and the corresponding Item in that View, gets a reference to the
         * ViewModel and deletes the item on a Coroutine.
         */
        private fun deleteItem(view: View, item: ShoppingItem) {
            val uiScope = CoroutineScope(Dispatchers.Main + Job())
            val dataSource = ShoppingItemDatabase.getInstance(view.context).shoppingItemDao
            val viewModel = HomeFragmentViewModel(dataSource)
            item.let {
                uiScope.launch {
                    withContext(Dispatchers.Default) {
                        viewModel.onDeleteButtonPressed(item)
                    }
                }
            }
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

class ShoppingItemListener(val clickListener: (itemId: Long) -> Unit){
    fun onClick(item: ShoppingItem) = clickListener(item.itemId)
}