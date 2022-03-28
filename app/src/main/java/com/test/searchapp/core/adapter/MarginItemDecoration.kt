package com.test.searchapp.core.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val bottomSpaceHeight: Int = 0,
    private val topSpaceHeight: Int = 0,
    private val leftRightSpaceHeight: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        with(outRect) {
            if (position == 0) {
                top = topSpaceHeight
            }
            left = leftRightSpaceHeight
            right = leftRightSpaceHeight
            bottom = bottomSpaceHeight
        }
    }
}