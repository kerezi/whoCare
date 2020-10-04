package com.whocare.android.app.ui.cost.add

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.whocare.android.app.R
import com.whocare.android.app.data.model.Cost
import com.whocare.android.app.data.model.CostItemData

import com.whocare.android.app.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CostItemRecyclerViewAdapter() : RecyclerView.Adapter<CostItemRecyclerViewAdapter.ViewHolder>() {

    private var values = emptyList<CostItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_cost, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.costItem.costItemId.toString()
        holder.contentView.text = "${item.cost.dateTime.toString()} ${item.product.name.toString()}"
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    fun setData(costItemData: List<CostItemData>) {
        this.values = costItemData
        notifyDataSetChanged()
    }
}