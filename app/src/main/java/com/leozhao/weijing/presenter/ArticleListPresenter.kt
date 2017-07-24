package com.leozhao.weijing.presenter

import android.util.Log
import com.leozhao.weijing.model.APIClient
import com.leozhao.weijing.model.ArticleList
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
                }

            }

            override fun onFailure(call: Call<ArticleList>?, t: Throwable?) {
                Log.d("dozhao", t?.message)
            }

        })
    }
}

