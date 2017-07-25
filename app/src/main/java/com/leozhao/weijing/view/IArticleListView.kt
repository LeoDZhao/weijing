package com.leozhao.weijing.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar

/**
 * Created by dozhao on 7/24/17.
 */
interface IArticleListView {
    fun getRecyclerView(): RecyclerView
    fun getProgressBar(): ProgressBar

    fun getContext(): Context
}