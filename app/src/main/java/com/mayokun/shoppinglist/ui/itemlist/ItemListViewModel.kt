package com.mayokun.shoppinglist.ui.itemlist

import android.app.Application
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayokun.shoppinglist.data.database.ShoppingItemDao
import com.mayokun.shoppinglist.data.model.ShoppingItem
import kotlinx.coroutines.*
import timber.log.Timber

/**
 * Created by Mayokun Adeniyi on 09/12/2019.
 */

class ItemListViewModel(
    val database: ShoppingItemDao,
    application: Application) : AndroidViewModel(application){
    
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val shoppingItems = database.getAllItems()



    fun onEditButtonClicked(item: ShoppingItem){
        uiScope.launch {
            update(item)

        }
    }

    private suspend fun update(item: ShoppingItem){
        withContext(Dispatchers.IO){
            database.update(item)
        }
    }


    fun onDeleteButtonPressed(item: ShoppingItem){
        uiScope.launch {
            delete(item)
        }
    }

    private suspend fun delete(item: ShoppingItem){
        withContext(Dispatchers.IO){
            database.deleteItem(item)
        }
    }

}