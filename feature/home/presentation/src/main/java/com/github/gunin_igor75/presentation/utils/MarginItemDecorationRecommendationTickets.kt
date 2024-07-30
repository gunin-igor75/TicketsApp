package com.github.gunin_igor75.presentation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorationRecommendationTickets(
    private val spaceSize: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        with(outRect) {
            val index = parent.getChildAdapterPosition(view)
            if (index != 0) {
                top = spaceSize
            }
        }
    }
}