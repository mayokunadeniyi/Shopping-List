package com.mayokun.shoppinglist.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mayokun.shoppinglist.data.model.ShoppingItem
import com.mayokun.shoppinglist.data.database.ShoppingItemDao
import kotlinx.coroutines.*
import timber.log.Timber

/**
 * Created by Mayokun Adeniyi on 2019-11-15.
 */

class HomeFragmentViewModel (
    val database: ShoppingItemDao): ViewModel(){

    private var viewModelJob = Job()

    private var _hasContent = MutableLiveData<Boolean>()
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
}