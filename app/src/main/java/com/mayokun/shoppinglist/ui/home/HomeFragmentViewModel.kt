package com.mayokun.shoppinglist.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayokun.shoppinglist.database.ShoppingItem
import com.mayokun.shoppinglist.database.ShoppingItemDao
import kotlinx.coroutines.*

/**
 * Created by Mayokun Adeniyi on 2019-11-15.
 */

class HomeFragmentViewModel (
    val database: ShoppingItemDao): ViewModel(){

    private var viewModelJob = Job()

    private var _hasContent = MutableLiveData<Boolean>()
    val hasContent: LiveData<Boolean>
    get() = _hasContent

    val shoppingItems = database.getAllItems()


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


    fun onSaveButtonPressed(item: ShoppingItem){
        uiScope.launch {
            insert(item)
        }
    }

    private suspend fun insert(item: ShoppingItem){
        withContext(Dispatchers.IO){
            database.insert(item)
            _hasContent.postValue(true)
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