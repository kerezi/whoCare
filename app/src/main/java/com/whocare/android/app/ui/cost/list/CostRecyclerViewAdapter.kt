package com.whocare.android.app.ui.cost.list

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import com.whocare.android.app.R
import com.whocare.android.app.data.model.Cost

import com.whocare.android.app.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_cost.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CostRecyclerViewAdapter() : RecyclerView.Adapter<CostRecyclerViewAdapter.ViewHolder>() {

    private var values = emptyList<Cost>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_cost, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.dateTime.toString()

        holder.itemView.cost.setOnClickListener {
            val action = CostFragmentDirections.actionCostFragmentToAddCostFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    fun setData(costs: List<Cost>) {
        this.values = costs
        notifyDataSetChanged()
    }
}