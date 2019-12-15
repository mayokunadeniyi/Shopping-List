package com.mayokun.shoppinglist.ui.itemlist

import android.widget.Toast
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
    val database: ShoppingItemDao) : ViewModel(){
    
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //To refresh the recycler
    private val _refresh = MutableLiveData<Boolean>()
    val refresh: LiveData<Boolean>
        get() = _refresh


    //To check if the db has content
    private var _hasContent = MutableLiveData<Boolean>()
    val hasContent: LiveData<Boolean>
        get() = _hasContent

    val shoppingItems = database.getAllItems()

    init {
        _refresh.value = false
    }

    /**
     * This checks if there are any items already stored in the database
     */
    private fun checkDataBase(){
        Timber.i("It checked 3")
        uiScope.launch {
            withContext(Dispatchers.IO){
                val oneItem = database.getOneItem()
                if (oneItem != null){
                    _hasContent.postValue(true)
                }else{
                    _hasContent.postValue(false)
                }
            }
        }
    }


    fun onEditButtonClicked(item: ShoppingItem){
        uiScope.launch {
            update(item)

        }
    }

    private suspend fun update(item: ShoppingItem){
        withContext(Dispatchers.IO){
            database.update(item)
            _refresh.postValue(true)
            Timber.i("The current state is ${refresh.value}")
        }
    }

    fun onRefreshFinished(){
        _refresh.value = false
    }

    fun onDeleteButtonPressed(item: ShoppingItem){
        uiScope.launch {
            delete(item)
        }
    }

    private suspend fun delete(item: ShoppingItem){
        withContext(Dispatchers.IO){
            database.deleteItem(item)
            Timber.i("It checked 1")
            checkDataBase()
            Timber.i("It checked 2")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}