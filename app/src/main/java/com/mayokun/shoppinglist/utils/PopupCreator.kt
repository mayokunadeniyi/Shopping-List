package com.mayokun.shoppinglist.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.data.model.ShoppingItem
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModel
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModelFactory

/**
 * Created by Mayokun Adeniyi on 29/11/2019.
 */

class Popup {

   companion object{
       /**
        * This shows the custom dialog for adding a new item
        */
       @SuppressLint("InflateParams")
       fun createPopUp(layoutInflater: LayoutInflater,context: Context,fragment: Fragment): View {
           val builder = AlertDialog.Builder(context)
           val alertDialog: AlertDialog
           val view = layoutInflater.inflate(R.layout.create_new_item,null)
           builder.setView(view)
           alertDialog = builder.create()
           alertDialog.show()

           view.findViewById<Button>(R.id.save_item_button).setOnClickListener {
               val dataSource = ShoppingItemDatabase.getInstance(context).shoppingItemDao

               val homeFragmentViewModelFactory = HomeFragmentViewModelFactory(dataSource)

               val homeFragmentViewModel = ViewModelProviders.of(fragment,homeFragmentViewModelFactory)
                   .get(HomeFragmentViewModel::class.java)

               val name = view.findViewById<EditText>(R.id.itemNameID)?.text.toString()
               val quantity = view.findViewById<EditText>(R.id.itemQuantityID)?.text.toString()

               if (TextUtils.isEmpty(name)) {
                   Toast.makeText(
                       context,
                       "Enter name of the item",
                       Toast.LENGTH_SHORT
                   ).show()
                   return@setOnClickListener
               }

               if (TextUtils.isEmpty(quantity)) {
                   Toast.makeText(
                       context,
                       "Enter Quantity",
                       Toast.LENGTH_SHORT
                   ).show()
                   return@setOnClickListener
               }

               val item = ShoppingItem(
                   itemName = name,
                   itemQuantity = quantity.toInt()
               )
               homeFragmentViewModel.onSaveButtonPressed(item)
               alertDialog.dismiss()
           }

           return view
       }

   }
}