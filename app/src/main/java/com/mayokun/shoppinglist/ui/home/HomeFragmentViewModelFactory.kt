package com.mayokun.shoppinglist.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mayokun.shoppinglist.data.database.ShoppingItemDao

/**
 * Created by Mayokun Adeniyi on 2019-11-15.
 */

@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModelFactory (
    private val dataSource: ShoppingItemDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}