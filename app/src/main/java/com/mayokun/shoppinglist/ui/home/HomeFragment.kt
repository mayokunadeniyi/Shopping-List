package com.mayokun.shoppinglist.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.databinding.FragmentHomeBinding
import com.mayokun.shoppinglist.databinding.FragmentItemListBinding
import com.mayokun.shoppinglist.utils.Popup


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Layout binding
        val binding = FragmentHomeBinding.inflate(layoutInflater)

        //Shopping Item DAO
        val dataSource = ShoppingItemDatabase.getInstance(this.requireContext()).shoppingItemDao

        //ViewModel
        val homeFragmentViewModel by viewModels<HomeFragmentViewModel> { HomeFragmentViewModelFactory(dataSource) }

        /**
         * This observer checks if the database has any content.
         * If there is content in the database, it triggers a navigation
         * from the HomeFragment to the ItemListFragment
         */
        homeFragmentViewModel.hasContent.observe(this, Observer { state ->
            if (state){
                this.findNavController().navigate(R.id.action_homeFragment_to_itemListFragment)
            }
        })

        binding.homeFragmentViewModel = homeFragmentViewModel
        binding.lifecycleOwner = this

        /**
         *  Onclick listener for the FAB to add a new item.
         *  This creates a pop dialog
         *  @see [Popup]
         */
        binding.newItemButton.setOnClickListener {
            Popup.createPopUp(layoutInflater,requireContext(),this)
        }
        return binding.root
    }

}
