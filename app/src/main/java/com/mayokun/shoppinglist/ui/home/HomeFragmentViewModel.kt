package com.mayokun.shoppinglist.ui.home

import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayokun.shoppinglist.database.ShoppingItem
import com.mayokun.shoppinglist.database.ShoppingItemDao
import kotlinx.android.synthetic.main.create_new_item.*
import kotlinx.coroutines.*

/**
 * Created by Mayokun Adeniyi on 2019-11-15.
 */

class HomeFragmentViewModel (
    val database: ShoppingItemDao,
    val dialogFragment: DialogFragment): ViewModel(){

    private var viewModelJob = Job()
    private val _hasContent = MutableLiveData<Boolean>()
    val hasContent: LiveData<Boolean>
    get() = _hasContent



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        checkDataBase()
    }

    /**
     * This checks if there are any items already stored in the database
     */
    private fun checkDataBase(){
        uiScope.launch {
           runOnAnotherThread()
        }
    }

    private suspend fun runOnAnotherThread(){
        withContext(Dispatchers.IO){
            val oneItem = database.getOneItem()
            if (oneItem != null){
                _hasContent.value = true
            }
        }
    }

    fun foo(){
        Log.i("WORKS","It works")
    }

    fun onSaveButtonPressed(){
        uiScope.launch {
            val itemName = dialogFragment.itemNameID.text.toString()
            val itemQuantity = dialogFragment.itemQuantityID.text.toString().toInt()
            val newItem = ShoppingItem(itemName = itemName,itemQuantity = itemQuantity)
            insert(newItem)
        }
    }

    private suspend fun insert(item: ShoppingItem){
        withContext(Dispatchers.IO){
            database.insert(item)
        }
    }

    fun onEditButtonClicked(){
        uiScope.launch {
            val newItemName = dialogFragment.itemNameID.text.toString()
            val newItemQuantity = dialogFragment.itemQuantityID.text.toString().toInt()
            val _newItem = ShoppingItem(itemName = newItemName,itemQuantity = newItemQuantity)
            update(_newItem)
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