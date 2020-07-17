package com.mayokun.shoppinglist.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayokun.shoppinglist.data.model.ShoppingItem
import com.mayokun.shoppinglist.data.database.ShoppingItemDao
import kotlinx.coroutines.*

/**
 * Created by Mayokun Adeniyi on 2019-11-15.
 */

class HomeFragmentViewModel(val database: ShoppingItemDao) : ViewModel() {

    private var _hasContent = MutableLiveData<Boolean>()
    val hasContent: LiveData<Boolean>
    get() = _hasContent

    init {
        checkDataBase()
    }

    /**
     * This checks if there are any items already stored in the database.
     * The value of the mutable live data [_hasContent] is updated based on
     * if there is an item in the database or not.
     */
    private fun checkDataBase() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val oneItem = database.getOneItem()
                if (oneItem != null) {
                    _hasContent.postValue(true)
                } else {
                    _hasContent.postValue(false)
                }
            }
        }
    }

    fun doneNavigating(){
        _hasContent.value = false
    }

    /**
     * This is triggered when the save button on the popup dialog is pressed.
     * Takes in a new item and sends it to the [insert] function to be saved in
     * the database on a coroutine.
     * @param item the item to be sent to the [insert] function
     */
    fun onSaveButtonPressed(item: ShoppingItem){
        viewModelScope.launch {
            insert(item)
        }
    }

    /**
     * This is a coroutine-friendly function that inserts a new item into the database.
     * @param item the item to be inserted into the database
     */
    private suspend fun insert(item: ShoppingItem){
        withContext(Dispatchers.IO){
            database.insert(item)
            _hasContent.postValue(true)
        }
    }
}