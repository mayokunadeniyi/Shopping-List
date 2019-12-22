package com.mayokun.shoppinglist.ui.start


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModel
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModelFactory
import com.mayokun.shoppinglist.ui.itemlist.ItemListFragment
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataSource = ShoppingItemDatabase.getInstance(requireContext()).shoppingItemDao

        val homeFragmentViewModel by viewModels<HomeFragmentViewModel> { HomeFragmentViewModelFactory(dataSource) }

        homeFragmentViewModel.hasContent.observe(this, Observer {
            val navController = this.findNavController()
            if (it == true){
                //Start the ItemList Fragment if the database has any content
              val action = StartFragmentDirections.actionStartFragmentToItemListFragment()
                navController.navigate(action)
            }else{
                //Start the Home Fragment if the database has no content
                val action = StartFragmentDirections.actionStartFragmentToHomeFragment()
                navController.navigate(action)
            }
        })
        return null
    }

}
