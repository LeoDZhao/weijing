package com.leozhao.weijing.model

import com.google.gson.annotations.SerializedName


/**
 * Created by dozhao on 7/24/17.
 */
class ArticleList {
    @SerializedName("stateMessage")
    var stateMessage: String? = ""
    @SerializedName("result")
    var result: Result? = null
    class Result {
        @SerializedName("list")
        var articleList: List<Article>? = null
        @SerializedName("totalPage")
        var totalPage: Int? = 0
        @SerializedName("ps")
        var perPage: Int? = 0
        @SerializedName("pno")
        var currentPage: Int? = 0
    }
}