package com.whocare.android.app.ui.cost.add

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.whocare.android.app.R
import com.whocare.android.app.ui.main.AppViewModelFactory
import com.whocare.android.app.ui.product.ProductView
import kotlinx.android.synthetic.main.add_cost_fragment.view.*

class AddCostFragment : Fragment() {

    private lateinit var addCostViewModel: AddCostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_cost_fragment, container, false)

        val price = view.price
        val owner = view.owner
        val payer = view.payer


        addCostViewModel = ViewModelProvider(this,
            AppViewModelFactory(activity?.application as Application)).get(
            AddCostViewModel::class.java)

        addCostViewModel.getAllProducts.observe(viewLifecycleOwner, Observer { products ->
            if (view.product is Spinner) {
                view.product.adapter = ArrayAdapter(this?.context as Context, android.R.layout.simple_spinner_item, products)
            }
        })

        view.add_cost_item.setOnClickListener {
//            insertCostToDB(product)
        }

        view.product_add.setOnClickListener {
            findNavController().navigate(R.id.action_addCostFragment_to_productFragment)
        }

        view.cost_items.adapter = CostItemRecyclerViewAdapter()
//        addCostViewModel.getAllCostItemDataByCostId().observe(viewLifecycleOwner, Observer { costItemData ->
//            if (view is RecyclerView && view.adapter is CostRecyclerViewAdapter) {
//                (view.adapter as CostItemRecyclerViewAdapter).setData(costItemData)
//            }
//        })

        return view
    }

    private fun insertCostToDB() {

    }

}