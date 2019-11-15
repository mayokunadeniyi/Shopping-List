package com.mayokun.shoppinglist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mayokun.shoppinglist.databinding.FragmentHomeBinding
import com.mayokun.shoppinglist.utils.NewItemFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home
            , container, false
        )

        binding.newItemButton.setOnClickListener { createPopUp() }
        return binding.root
    }

    /**
     * This shows the custom dialog for adding a new item
     */
    private fun createPopUp() {
        val newItemFragment = NewItemFragment()
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val prev = fragmentManager?.findFragmentByTag("NewItem")
        if (prev != null) {
            fragmentTransaction?.remove(prev)
        }
        fragmentTransaction?.addToBackStack(null)
        newItemFragment.show(fragmentTransaction!!,"NewItem")
    }

}
