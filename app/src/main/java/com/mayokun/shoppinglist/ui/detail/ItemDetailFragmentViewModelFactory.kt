package com.mayokun.shoppinglist.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mayokun.shoppinglist.data.database.ShoppingItemDao
import com.mayokun.shoppinglist.ui.detail.ItemDetailFragmentViewModel
import java.lang.IllegalArgumentException

/**
 * Created by Mayokun Adeniyi on 2019-12-21.
 */

@Suppress("UNCHECKED_CAST")
class ItemDetailFragmentViewModelFactory (
    private val dataSource: ShoppingItemDao,
    private val itemId: Long
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemDetailFragmentViewModel::class.java)){
            return ItemDetailFragmentViewModel(
                dataSource,
                itemId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}