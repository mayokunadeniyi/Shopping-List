package com.mayokun.shoppinglist.ui.itemlist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mayokun.shoppinglist.R
import com.mayokun.shoppinglist.data.database.ShoppingItemDatabase
import com.mayokun.shoppinglist.databinding.FragmentItemListBinding
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModel
import com.mayokun.shoppinglist.ui.home.HomeFragmentViewModelFactory
import com.mayokun.shoppinglist.utils.Popup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class ItemListFragment : Fragment() {
    lateinit var binding: FragmentItemListBinding
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)
        val dataSource = ShoppingItemDatabase.getInstance(this.requireContext()).shoppingItemDao

        val homeFragmentViewModelFactory = HomeFragmentViewModelFactory(dataSource)

        homeFragmentViewModel = ViewModelProviders.of(this,homeFragmentViewModelFactory)
            .get(HomeFragmentViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_item_list,container,false
        )

        val adapter = ShoppingItemAdapter(ShoppingItemListener {
            Toast.makeText(context,"Id is $it",Toast.LENGTH_SHORT).show()
        })
        binding.shoppingListRecyclerview.adapter = adapter

        homeFragmentViewModel.shoppingItems.observe(this, Observer {
            adapter.submitList(it)
        })

        binding.addAnotherItem.setOnClickListener {
            Popup.createPopUp(layoutInflater,requireContext(),this)
        }

        return binding.root
    }


}
