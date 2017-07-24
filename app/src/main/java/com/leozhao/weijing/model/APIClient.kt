package com.leozhao.weijing.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by dozhao on 7/24/17.
 */
object APIClient {
    fun getClient(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/weixin/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}