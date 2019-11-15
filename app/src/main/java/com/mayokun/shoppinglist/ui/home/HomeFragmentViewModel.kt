package com.mayokun.shoppinglist.ui.home

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.mayokun.shoppinglist.database.ShoppingItem
import com.mayokun.shoppinglist.database.ShoppingItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Created by Mayokun Adeniyi on 2019-11-15.
 */

class HomeFragmentViewModel (
    val database: ShoppingItemDao,
    val dialogFragment: DialogFragment): ViewModel(){

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    
}