package com.mayokun.shoppinglist.ui.itemlist

import android.app.AlertDialog
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.data.model.ShoppingItem
import com.mayokun.shoppinglist.databinding.CreateNewItemBinding
import com.mayokun.shoppinglist.databinding.ItemListBinding

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

    class ViewHolder private constructor(private val binding: ItemListBinding):
        RecyclerView.ViewHolder(binding.root){
        val dataSource = ShoppingItemDatabase.getInstance(itemView.context).shoppingItemDao
        val viewModel = ItemListViewModel(dataSource, Application())

        /**
         * This function takes in a shopping item and uses DataBinding to attach each view in the item_list.xml layout to data
         * from the shopping item.
         */
        fun bind(item: ShoppingItem, clickListener: ShoppingItemListener){
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
            binding.deleteBtn.setOnClickListener {
                deleteItem(item)
            }
            binding.editBtn.setOnClickListener {view ->
                editItem(view,item)
            }
        }

        /**
         * This function is called when the edit button is pressed.
         * It takes in a [view] and the corresponding [item] in that view which is being updated.
         * @param view the view in scope
         * @param item the item to be edited and saved to the database.
         */
        private fun editItem(view: View, item: ShoppingItem) {
            val builder = AlertDialog.Builder(view.context)
            val layoutInflater = LayoutInflater.from(view.context)
            val alertDialog: AlertDialog
            val layoutView = layoutInflater.inflate(R.layout.create_new_item,null)
            val newItemBinding = CreateNewItemBinding.bind(layoutView)
            newItemBinding.itemNameID.setText(item.itemName)
            newItemBinding.itemQuantityID.setText(item.itemQuantity.toString())
            builder.setView(layoutView)
            alertDialog = builder.create()
            alertDialog.show()

            newItemBinding.saveItemButton.setOnClickListener {

                binding.item = item.apply {
                    itemName = newItemBinding.itemNameID.text.toString()
                    itemQuantity = newItemBinding.itemQuantityID.text.toString().toInt()
                }

                viewModel.onEditButtonClicked(item)
                alertDialog.dismiss()
            }
        }

        /**
         * This function is called when the delete button is pressed.
         * It takes in an [item] and deletes the item from the database with the help of the viewmodel.
         * @param item the item to be deleted from the database
         */
        private fun deleteItem(item: ShoppingItem) {
            item.let {
               viewModel.onDeleteButtonPressed(item)
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