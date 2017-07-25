package com.leozhao.weijing.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.leozhao.weijing.R
import com.leozhao.weijing.presenter.ArticleListPresenter

class MainActivity : AppCompatActivity(), IArticleListView {
    override fun getProgressBar(): ProgressBar = findViewById(R.id.progress_bar) as ProgressBar
    override fun getRecyclerView(): RecyclerView = findViewById(R.id.recycler_view) as RecyclerView
    override fun getContext(): Context = this

    val articleListPresenter: ArticleListPresenter = ArticleListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        articleListPresenter.loadArticleList()
    }
}
