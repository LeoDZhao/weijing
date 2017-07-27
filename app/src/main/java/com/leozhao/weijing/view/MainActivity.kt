package com.leozhao.weijing.view

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.leozhao.weijing.R
import com.leozhao.weijing.presenter.ArticleListPresenter


class MainActivity : AppCompatActivity(), IArticleListView {
    override fun getRecyclerView(): RecyclerView = findViewById(R.id.recycler_view) as RecyclerView
    override fun getContext(): Context = this
    override fun getSwipeRefreshLayout(): SwipeRefreshLayout = findViewById(R.id.content_main) as SwipeRefreshLayout

    val articleListPresenter: ArticleListPresenter = ArticleListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        articleListPresenter.initArticleList()
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.setOnClickListener {
            articleListPresenter.scrollArticleListToTop()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_memu, menu)
        return true
    }


}
