package com.mayokun.shoppinglist.utils


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.mayokun.shoppinglist.R
import java.lang.IllegalStateException

/**
 * A simple [DialogFragment] subclass.
 * This creates the custom dialog screen to create a new item.
 */
class NewItemDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            //Get the layout inflater
            val inflater = requireActivity().layoutInflater

            builder.setView(inflater.inflate(R.layout.create_new_item,null))
            builder.setTitle(getString(R.string.new_item_dialog_title))
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
