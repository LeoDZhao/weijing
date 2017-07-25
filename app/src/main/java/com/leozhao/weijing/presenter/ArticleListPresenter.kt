package com.leozhao.weijing.presenter

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.leozhao.weijing.model.APIClient
import com.leozhao.weijing.model.ArticleList
import com.leozhao.weijing.model.ArticleListAdapter
import com.leozhao.weijing.model.WeiJingService
import com.leozhao.weijing.view.IArticleListView
import retrofit2.Call
import retrofit2.Response

/**
 * Created by dozhao on 7/24/17.
 */

class ArticleListPresenter(view: IArticleListView) {
    val mView = view
    fun initArticleList(): Unit {
        mView.getSwipeRefreshLayout().isRefreshing = true
        val recyclerView = mView.getRecyclerView()
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(mView.getContext())
        recyclerView.layoutManager = mLayoutManager

        refreshArticleList()

        mView.getSwipeRefreshLayout().setOnRefreshListener {
            refreshArticleList()
        }
    }

    fun refreshArticleList() {
        val service = APIClient.getClient().create(WeiJingService::class.java)
        val call = service.listArticles()
        call.enqueue(object: retrofit2.Callback<ArticleList> {
            override fun onResponse(call: Call<ArticleList>?, response: Response<ArticleList>?) {
                Log.d("dozhao", response.toString())
                val body=response?.body()
                Log.d("dozhao", "total page: ${body?.result?.totalPage}")
                val articlelist = body?.result?.articleList
                Log.d("dozhao", "Number of articles: " + articlelist?.size.toString())
                if (articlelist!=null) {
                    for (article in articlelist) {
                        Log.d("dozhao", "title: ${article.title}")
                    }
                    val mAdapter = ArticleListAdapter(articlelist)
                    mView.getRecyclerView().adapter = mAdapter
                }
                mView.getSwipeRefreshLayout().isRefreshing = false
            }

            override fun onFailure(call: Call<ArticleList>?, t: Throwable?) {
                Log.d("dozhao", t?.message)
                mView.getSwipeRefreshLayout().isRefreshing = false
            }

        })
    }
}

