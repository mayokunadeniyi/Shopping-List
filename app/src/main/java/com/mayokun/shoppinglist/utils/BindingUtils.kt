package com.mayokun.shoppinglist.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mayokun.shoppinglist.data.model.ShoppingItem

/**
 * Created by Mayokun Adeniyi on 30/11/2019.
 */

@BindingAdapter("setItemName")
fun TextView.setItemName(item: ShoppingItem?){
    item?.let {
        text = it.itemName
    }
}

@BindingAdapter("setItemQuantity")
fun TextView.setItemQuantity(item: ShoppingItem?){
    item?.let {
        text = it.itemQuantity.toString()
    }
}

@BindingAdapter("setDateCreated")
fun TextView.setDateCreated(item: ShoppingItem?){
    item?.let {
        text = it.dateItemCreated
    }
}