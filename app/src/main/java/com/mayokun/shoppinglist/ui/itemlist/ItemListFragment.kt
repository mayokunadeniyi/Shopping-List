package com.mayokun.shoppinglist.ui.itemlist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.databinding.FragmentItemListBinding
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

        val application = requireNotNull(this.activity).application

        val itemListViewModel by viewModels<ItemListViewModel> { ItemListViewModelFactory(dataSource,application) }


        binding.itemListViewModel = itemListViewModel
        binding.lifecycleOwner = this


        val adapter = ShoppingItemAdapter(ShoppingItemListener { itemId ->
            val action = ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(itemId)
            this.findNavController().navigate(action)
        })

        binding.shoppingListRecyclerview.adapter = adapter

        itemListViewModel.shoppingItems.observe(this, Observer {
            adapter.submitList(it)
            if (it.isNullOrEmpty()){
                val action = ItemListFragmentDirections.actionItemListFragmentToHomeFragment()
                this.findNavController().navigate(action)
            }
        })


        binding.addAnotherItem.setOnClickListener {
            Popup.createPopUp(layoutInflater,requireContext(),this)
        }


        return binding.root
    }

}