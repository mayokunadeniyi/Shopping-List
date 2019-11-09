package com.mayokun.shoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Mayokun Adeniyi on 2019-11-09.
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