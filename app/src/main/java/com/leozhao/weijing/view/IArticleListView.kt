package com.leozhao.weijing.view

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView

/**
 * Created by dozhao on 7/24/17.
 */
interface IArticleListView {
    fun getRecyclerView(): RecyclerView
    fun getSwipeRefreshLayout(): SwipeRefreshLayout
    fun getContext(): Context
}