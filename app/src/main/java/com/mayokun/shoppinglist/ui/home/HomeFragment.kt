package com.mayokun.shoppinglist.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.databinding.FragmentHomeBinding
import com.mayokun.shoppinglist.utils.NewItemDialogFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var newItemFragment: NewItemDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home
            , container, false
        )

        //Initialize the dialog fragment for creating a new item
        newItemFragment = NewItemDialogFragment()

        val dataSource = ShoppingItemDatabase.getInstance(this.requireContext()).shoppingItemDao

        val homeFragmentViewModelFactory = HomeFragmentViewModelFactory(dataSource,newItemFragment)

        val homeFragmentViewModel = ViewModelProviders.of(this,homeFragmentViewModelFactory)
            .get(HomeFragmentViewModel::class.java)

        binding.homeFragmentViewModel = homeFragmentViewModel
        binding.lifecycleOwner = this

        //Onclick listener for the FAB
        binding.newItemButton.setOnClickListener { createPopUp() }
        return binding.root
    }

    /**
     * This shows the custom dialog for adding a new item
     */
    private fun createPopUp() {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val prev = fragmentManager?.findFragmentByTag("NewItem")
        if (prev != null) {
            fragmentTransaction?.remove(prev)
        }
        fragmentTransaction?.addToBackStack(null)
        newItemFragment.show(fragmentTransaction!!,"NewItem")
    }

}
