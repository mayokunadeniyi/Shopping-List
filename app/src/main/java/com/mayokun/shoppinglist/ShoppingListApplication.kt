package com.mayokun.shoppinglist

import android.app.Application
import timber.log.Timber

/**
 * Created by Mayokun Adeniyi on 2019-11-26.
 */

class ShoppingListApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}