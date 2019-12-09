package com.mayokun.shoppinglist.ui.itemlist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.databinding.FragmentItemListBinding
import com.mayokun.shoppinglist.ui.home.HomeFragment
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModel
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModelFactory
import com.mayokun.shoppinglist.utils.Popup
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class ItemListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Layout binding
        val binding = FragmentItemListBinding.inflate(layoutInflater)

        val dataSource = ShoppingItemDatabase.getInstance(this.requireContext()).shoppingItemDao

        val viewModel by viewModels<ItemListViewModel> { ItemListViewModelFactory(dataSource) }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ShoppingItemAdapter(ShoppingItemListener {
            Toast.makeText(context,"Id is $it",Toast.LENGTH_SHORT).show()
        })

        binding.shoppingListRecyclerview.adapter = adapter

        viewModel.shoppingItems.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.refresh.observe(this, Observer { state ->
            if (state == true){
                adapter.notifyDataSetChanged()
                viewModel.onRefreshFinished()
            }
        })

        binding.addAnotherItem.setOnClickListener {
            Popup.createPopUp(layoutInflater,requireContext(),this)
        }

        return binding.root
    }

}
