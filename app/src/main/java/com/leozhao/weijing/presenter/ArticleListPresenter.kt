package com.leozhao.weijing.presenter

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
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
    fun loadArticleList(): Unit {
        val recyclerView = mView.getRecyclerView()
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(mView.getContext())
        recyclerView.layoutManager = mLayoutManager

        val progressBar = mView.getProgressBar()
        progressBar.visibility= View.VISIBLE

        //Get ArticleList Data from API
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
                    recyclerView.adapter = mAdapter
                }

                progressBar.visibility= View.GONE

            }

            override fun onFailure(call: Call<ArticleList>?, t: Throwable?) {
                Log.d("dozhao", t?.message)
            }

        })
    }
}

