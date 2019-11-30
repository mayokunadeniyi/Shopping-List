package com.mayokun.shoppinglist.ui.itemlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.data.model.ShoppingItem
import com.mayokun.shoppinglist.databinding.ItemListBinding
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModel
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModelFactory
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

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

    class ViewHolder private constructor(private val binding: ItemListBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private val job = Job()
        private val uiScope = CoroutineScope(Dispatchers.Main + job)

        /**
         * This function takes in a shopping item and uses DataBinding to attach each view in the item_list.xml layout to data
         * from the shopping item.
         */
        fun bind(item: ShoppingItem){
            binding.item = item
            binding.executePendingBindings()
            //TODO: Try removing the extension of onclick listener and just call button.setOnClickListener
            binding.deleteBtn.setOnClickListener(this)
            binding.editBtn.setOnClickListener(this)

        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }

        override fun onClick(v: View) {
            binding.deleteBtn.setOnClickListener {
                uiScope.launch {
                     withContext(Dispatchers.IO){
                    val dataSource = ShoppingItemDatabase.getInstance(v.context).shoppingItemDao
                    val viewModel = HomeFragmentViewModel(dataSource)
                    val list = dataSource.getRawList()
                    val item = list[adapterPosition]
                    viewModel.onDeleteButtonPressed(item)
                         Toast.makeText(v.context,"Item deleted",Toast.LENGTH_SHORT).show()
                     }
                }

            }

            binding.editBtn.setOnClickListener {
                //TODO: Update the item
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