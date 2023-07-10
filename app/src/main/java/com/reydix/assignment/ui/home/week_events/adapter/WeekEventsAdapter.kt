package com.reydix.assignment.ui.home.week_events.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.reydix.assignment.R
import com.reydix.assignment.common.base.BaseAdapter
import com.reydix.assignment.ui.home.week_events.WeekEventItem


class WeekEventsAdapter(items: List<WeekEventItem>)
    : BaseAdapter<WeekEventsAdapter.WeekEventsHolder>(items) {

    override val layoutId: Int = R.layout.item_home_events

    override fun onBindData(holder: WeekEventsHolder, item: Any) {
        (item as? WeekEventItem)?.run { holder.bind(this) }
    }

    override fun createViewHolder(view: View): WeekEventsHolder {
        return WeekEventsHolder(view)
    }

    inner class WeekEventsHolder(itemView: View) : BaseViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.item_home_events_img)
        private val titleView: TextView = itemView.findViewById(R.id.item_home_events_title)
        private val dateView: TextView = itemView.findViewById(R.id.item_home_events_date)
        private val placeView: TextView = itemView.findViewById(R.id.item_home_events_place)

        fun bind(item: WeekEventItem) {
            imageView.setImageResource(item.imageRes)
            titleView.text = item.title
            dateView.text = item.date
            placeView.text = item.place
        }
    }
}