package com.reydix.assignment.common.base

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView


class CustomSnapHelper : LinearSnapHelper() {

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        if (layoutManager is LinearLayoutManager) {
            if (!needToDoSnap(layoutManager)) {
                return null
            }
        }
        return super.findSnapView(layoutManager)
    }

    private fun needToDoSnap(linearLayoutManager: LinearLayoutManager): Boolean {
        val firstCompletelyVisibleItemPos = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
        val lastCompletelyVisibleItemPos = linearLayoutManager.findLastCompletelyVisibleItemPosition()

        return firstCompletelyVisibleItemPos != 0 && lastCompletelyVisibleItemPos != linearLayoutManager.itemCount - 1
    }
}