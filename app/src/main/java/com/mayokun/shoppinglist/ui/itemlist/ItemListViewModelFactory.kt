package com.mayokun.shoppinglist.ui.itemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mayokun.shoppinglist.data.database.ShoppingItemDao

/**
 * Created by Mayokun Adeniyi on 09/12/2019.
 */

class ItemListViewModelFactory(
    private val dataSource: ShoppingItemDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemListViewModel::class.java)) {
            return ItemListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}