package com.leozhao.weijing.presenter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.leozhao.weijing.Constant
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
    val mRecylerView = mView.getRecyclerView()
    val mSwipeRefreshLayout = mView.getSwipeRefreshLayout()
    var loading = false
    fun initArticleList(): Unit {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecylerView.setHasFixedSize(true)

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(mView.getContext())
        mRecylerView.layoutManager = mLayoutManager
        mRecylerView.adapter = ArticleListAdapter

        refreshArticleList()

        mSwipeRefreshLayout.setOnRefreshListener {
            refreshArticleList()
        }

        mRecylerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?,
                                    dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                loadMoreIfNeeded()
            }
        })
    }

    fun refreshArticleList() {
        mSwipeRefreshLayout.isRefreshing = true
        val service = APIClient.getClient().create(WeiJingService::class.java)
        val call = service.listArticles()
        call.enqueue(object: retrofit2.Callback<ArticleList> {
            override fun onResponse(call: Call<ArticleList>?, response: Response<ArticleList>?) {
                Log.d(Constant.DEBUG_TAG, "Response: $response")
                val body=response?.body()
                Log.d(Constant.DEBUG_TAG, "Total page: ${body?.result?.totalPage}")
                Log.d(Constant.DEBUG_TAG, "Current page: ${body?.result?.currentPage}")
                val articleList = body?.result?.articleList
                Log.d(Constant.DEBUG_TAG, "Articles per page: " + articleList?.size.toString())
                if (articleList!=null) {
                    for ((index, article) in articleList.withIndex()) {
                        Log.d(Constant.DEBUG_TAG, "title$index: ${article.title}")
                    }
                    ArticleListAdapter.clearArticleList()
                    ArticleListAdapter.addArticleList(articleList)
                    mRecylerView.adapter.notifyDataSetChanged()
                }
                mSwipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<ArticleList>?, t: Throwable?) {
                Log.d(Constant.DEBUG_TAG, t?.message ?: "No message")
                mSwipeRefreshLayout.isRefreshing = false
            }

        })
    }

    fun scrollArticleListToTop() {
        mRecylerView.scrollToPosition(0)
    }

    fun loadMoreIfNeeded() {
        val layoutManager: LinearLayoutManager = mRecylerView.layoutManager as LinearLayoutManager
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        val visibleThreshold = 5
        if (!loading && totalItemCount <= lastVisibleItem + visibleThreshold) {
            // End has been reached
            // Do something
            val currentPage = ArticleListAdapter.totalArticleNumber() / Constant.ITEM_PER_PAGE
            Log.d(Constant.DEBUG_TAG, "Current page number: $currentPage")
            val service = APIClient.getClient().create(WeiJingService::class.java)
            val call = service.listArticles(page = currentPage + 1)

            call.enqueue(object: retrofit2.Callback<ArticleList> {
                override fun onResponse(call: Call<ArticleList>?, response: Response<ArticleList>?) {
                    Log.d(Constant.DEBUG_TAG, "Response: $response")
                    val body=response?.body()
                    Log.d(Constant.DEBUG_TAG, "Total page: ${body?.result?.totalPage}")
                    Log.d(Constant.DEBUG_TAG, "Current page: ${body?.result?.currentPage}")
                    val articleList = body?.result?.articleList
                    Log.d(Constant.DEBUG_TAG, "Articles per page: " + articleList?.size.toString())
                    if (articleList!=null) {
                        for ((index, article) in articleList.withIndex()) {
                            Log.d(Constant.DEBUG_TAG, "title$index: ${article.title}")
                        }
                        ArticleListAdapter.addArticleList(articleList)
                        mRecylerView.adapter.notifyDataSetChanged()
                    }
                    loading = false

                }

                override fun onFailure(call: Call<ArticleList>?, t: Throwable?) {
                    Log.d(Constant.DEBUG_TAG, t?.message ?: "No message")
                    loading = false
                }

            })
            loading = true
        }
    }

}

