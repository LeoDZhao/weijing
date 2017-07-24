package com.leozhao.weijing.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by dozhao on 7/24/17.
 */
interface WeiJingService {
    //get appkey from https://www.juhe.cn/docs/api/id/147
    @GET("query")
    fun listArticles(@Query("pno") page: Int=1,
                     @Query("ps") perPage: Int=20,
                     @Query("key") key: String="******"
                     ): Call<ArticleList>
}
