package com.mayokun.shoppinglist.ui.itemlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mayokun.shoppinglist.data.database.ShoppingItemDao

/**
 * Created by Mayokun Adeniyi on 09/12/2019.
 */

@Suppress("UNCHECKED_CAST")
class ItemListViewModelFactory(
    private val dataSource: ShoppingItemDao,
    private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemListViewModel::class.java)) {
            return ItemListViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}