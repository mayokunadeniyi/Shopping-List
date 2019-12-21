package com.mayokun.shoppinglist.ui.itemlist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.databinding.FragmentItemListBinding
import com.mayokun.shoppinglist.utils.Popup

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


        val adapter = ShoppingItemAdapter(ShoppingItemListener {
            Toast.makeText(context,"Id is $it",Toast.LENGTH_SHORT).show()
        })

        binding.shoppingListRecyclerview.adapter = adapter

        itemListViewModel.shoppingItems.observe(this, Observer {
            adapter.submitList(it)
        })

        binding.lifecycleOwner = this.viewLifecycleOwner


        binding.addAnotherItem.setOnClickListener {
            Popup.createPopUp(layoutInflater,requireContext(),this)
        }


        return binding.root
    }

}