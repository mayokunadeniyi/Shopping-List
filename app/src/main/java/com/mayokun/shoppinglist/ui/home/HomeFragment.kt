package com.mayokun.shoppinglist.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

        val homeFragmentViewModelFactory = HomeFragmentViewModelFactory(dataSource)

        val homeFragmentViewModel = ViewModelProviders.of(this,homeFragmentViewModelFactory)
            .get(HomeFragmentViewModel::class.java)

        //Observer to check if the database contains any items
        homeFragmentViewModel.hasContent.observe(this, Observer { state ->
            if (state){
                this.findNavController().navigate(R.id.action_homeFragment_to_itemListFragment)
            }
        })

        binding.homeFragmentViewModel = homeFragmentViewModel
        binding.lifecycleOwner = this


        //Onclick listener for the FAB
        binding.newItemButton.setOnClickListener {
            Popup.createPopUp(layoutInflater,requireContext(),this)
        }

        return binding.root
    }

}
