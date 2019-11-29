package com.mayokun.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Mayokun Adeniyi on 2019-11-09.
 */

/**
 * This creates the Database Access Object popularly known as a 'Dao'
 * to insert, query, update, and delete from the Shopping Item Database
 */
@Dao
interface ShoppingItemDao {

    @Insert
    fun insert(shoppingItem: ShoppingItem)

    @Update
    fun update(shoppingItem: ShoppingItem)

    @Delete
    fun deleteItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_list_table ORDER BY itemId DESC")
    fun getAllItems() :LiveData<List<ShoppingItem>>

    @Query("SELECT * FROM shopping_list_table ORDER BY itemId DESC LIMIT 1")
    fun getOneItem(): ShoppingItem?
}