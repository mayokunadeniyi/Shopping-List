package com.mayokun.shoppinglist.ui.itemlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mayokun.shoppinglist.data.database.ShoppingItemDao
import com.mayokun.shoppinglist.data.model.ShoppingItem
import kotlinx.coroutines.*

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

    /**
     * This is triggered when the edit button is pressed and then the save button on the popup dialog is pressed.
     * Takes in an edited item and sends it to the [update] function to be updated in
     * the database on a coroutine.
     * @param item the edited item to be sent to the [update] function
     */
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