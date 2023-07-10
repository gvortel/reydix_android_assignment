package com.reydix.assignment.common.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T : BaseAdapter.BaseViewHolder>(
    private var items: List<Any>
) : RecyclerView.Adapter<T>() {

    abstract val layoutId: Int

    abstract fun onBindData(holder: T, item: Any)
    
    abstract fun createViewHolder(view: View): T

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun updateItems(items: List<Any>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        val item = items[position]
        onBindData(holder, item)
    }

    override fun getItemCount(): Int = items.size
    
}