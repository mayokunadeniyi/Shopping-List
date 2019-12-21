package com.mayokun.shoppinglist.ui.start


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModel
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : Fragment() {

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataSource = ShoppingItemDatabase.getInstance(requireContext()).shoppingItemDao

        val homeFragmentViewModelFactory = HomeFragmentViewModelFactory(dataSource)

        homeFragmentViewModel = ViewModelProviders.of(this,homeFragmentViewModelFactory)
            .get(HomeFragmentViewModel::class.java)

        homeFragmentViewModel.hasContent.observe(this, Observer {
            val navController = findNavController()
            if (it == true){
                navController.navigate(R.id.action_startFragment_to_itemListFragment)

            }else{
                navController.navigate(R.id.action_startFragment_to_homeFragment)
            }

        })
        return null
    }
}
