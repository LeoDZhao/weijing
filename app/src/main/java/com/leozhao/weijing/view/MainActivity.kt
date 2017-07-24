package com.leozhao.weijing.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.leozhao.weijing.R
import com.leozhao.weijing.presenter.ArticleListPresenter

class MainActivity : AppCompatActivity(), IArticleListView {
    val articleListPresenter: ArticleListPresenter = ArticleListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        articleListPresenter.loadArticleList()
    }
}
