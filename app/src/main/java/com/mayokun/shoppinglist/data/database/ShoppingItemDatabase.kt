package com.mayokun.shoppinglist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mayokun.shoppinglist.data.model.ShoppingItem

/**
 * Created by Mayokun Adeniyi on 2019-11-09.
 */

/**
 * This represents the Room Database for the Shopping Items.
 * It creates a new Database if an instance does not exist and returns a
 * reference to an already existing Database.
 */
@Database(entities = [ShoppingItem::class],version = 1, exportSchema = false)
abstract class ShoppingItemDatabase : RoomDatabase(){

    abstract val shoppingItemDao: ShoppingItemDao

    companion object{

        @Volatile
        private var INSTANCE: ShoppingItemDatabase? = null

        fun getInstance(context: Context) : ShoppingItemDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext
                        ,ShoppingItemDatabase::class.java,"shopping_items_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}