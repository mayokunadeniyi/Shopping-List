package com.mayokun.shoppinglist.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mayokun.shoppinglist.data.model.ShoppingItem

/**
 * Created by Mayokun Adeniyi on 30/11/2019.
 */

@BindingAdapter("itemName")
fun TextView.setItemName(item: ShoppingItem?){
    item?.let {
        text = it.itemName
    }
}

@BindingAdapter("itemQuantity")
fun TextView.setItemQuantity(item: ShoppingItem?){
    item?.let {
        text = it.itemQuantity.toString()
    }
}

@BindingAdapter("dateCreated")
fun TextView.setDateCreated(item: ShoppingItem?){
    item?.let {
        text = it.dateItemCreated
    }
}