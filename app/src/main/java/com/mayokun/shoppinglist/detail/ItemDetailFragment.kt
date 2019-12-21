package com.mayokun.shoppinglist.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.databinding.FragmentItemDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class ItemDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentItemDetailBinding.inflate(layoutInflater)


        return binding.root
    }


}
