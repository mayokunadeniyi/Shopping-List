package com.mayokun.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mayokun.shoppinglist.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {

        when(supportFragmentManager.fragments[0].javaClass.simpleName){
          "ItemListFragment" -> this.finish()
            "HomeFragment" -> this.finish()

            else -> supportFragmentManager.popBackStack()
        }
    }
}
