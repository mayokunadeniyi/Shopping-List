package com.mayokun.shoppinglist.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
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

        val dataSource = ShoppingItemDatabase.getInstance(requireContext()).shoppingItemDao

        val itemDetailFragmentArgs by navArgs<ItemDetailFragmentArgs>()

        val viewModel by viewModels<ItemDetailFragmentViewModel> {
            ItemDetailFragmentViewModelFactory(dataSource,itemDetailFragmentArgs.itemId)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.item.observe(viewLifecycleOwner, Observer {
            binding.item = it
        })

        return binding.root
    }


}
