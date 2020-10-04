package com.whocare.android.app.ui.cost.list

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.whocare.android.app.R
import com.whocare.android.app.dummy.DummyContent
import com.whocare.android.app.ui.enroll.EnrollViewModel
import com.whocare.android.app.ui.main.AppViewModelFactory
import kotlinx.android.synthetic.main.fragment_cost_list.view.*

/**
 * A fragment representing a list of Items.
 */
class CostFragment : Fragment() {

    private lateinit var costViewModel: CostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cost_list, container, false)

        view.rv.layoutManager = LinearLayoutManager(context)
        view.rv.adapter = CostRecyclerViewAdapter()

        costViewModel = ViewModelProvider(this,
            AppViewModelFactory(activity?.application as Application)).get(
            CostViewModel::class.java)

        costViewModel.getAllCosts.observe(viewLifecycleOwner, Observer { costs ->
            if (view.rv is RecyclerView && view.rv.adapter is CostRecyclerViewAdapter) {
                (view.rv.adapter as CostRecyclerViewAdapter).setData(costs)
            }
        })

        view.add_cost.setOnClickListener {
            findNavController().navigate(R.id.action_costFragment_to_addCostFragment)
        }
        return view
    }

}