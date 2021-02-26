package com.diwixis.feature_movie_list.domain

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

internal abstract class PaginationListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (isAbleToLoadMore) {
            if (firstVisibleItemPosition >= totalItemCount - PAGE_SIZE &&
                firstVisibleItemPosition >= 0 &&
                totalItemCount >= PAGE_SIZE
            ) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
    abstract val isAbleToLoadMore: Boolean

    companion object {
        const val PAGE_START = 1
        private const val PAGE_SIZE = 5
    }
}