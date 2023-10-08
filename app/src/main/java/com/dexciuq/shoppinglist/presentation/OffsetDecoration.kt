package com.dexciuq.shoppinglist.presentation

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class OffsetDecoration(
    start: Int,
    top: Int,
    end: Int,
    bottom: Int
) : ItemDecoration() {

    constructor(horizontal: Int, vertical: Int) : this(
        start = horizontal,
        top = vertical,
        end = horizontal,
        bottom = vertical,
    )

    private val startOffset = start.dp
    private val topOffset = top.dp
    private val endOffset = end.dp
    private val bottomOffset = bottom.dp

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = startOffset
        outRect.top = topOffset
        outRect.right = endOffset
        outRect.bottom = bottomOffset
    }
}

private val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()


//private val Int.dp
//    get() = TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_DIP,
//        this.toFloat(),
//        Resources.getSystem().displayMetrics
//    ).toInt()