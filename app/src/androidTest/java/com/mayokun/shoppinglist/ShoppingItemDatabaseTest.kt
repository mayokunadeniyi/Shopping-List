package com.mayokun.shoppinglist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mayokun.shoppinglist.database.ShoppingItem
import com.mayokun.shoppinglist.database.ShoppingItemDao
import com.mayokun.shoppinglist.database.ShoppingItemDatabase
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Created by Mayokun Adeniyi on 2019-11-16.
 */

@RunWith(AndroidJUnit4::class)
class ShoppingItemDatabaseTest {

    private lateinit var shoppingItemDao: ShoppingItemDao
    private lateinit var db: ShoppingItemDatabase

    @Before
    fun createDb(){
      val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context,ShoppingItemDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        shoppingItemDao = db.shoppingItemDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    /**
     * This tests the insert method of the DAO
     */
    @Test
    @Throws(Exception::class)
    fun insertAndGetItem(){
      val item = ShoppingItem(itemName = "Yam",itemQuantity = 5)
        shoppingItemDao.insert(item)
        val newItem = shoppingItemDao.getOneItem()
        assertEquals(newItem?.itemQuantity,5)
    }
}

