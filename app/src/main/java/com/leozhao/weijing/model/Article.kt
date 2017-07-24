package com.leozhao.weijing.model

import com.google.gson.annotations.SerializedName

/**
 * Created by dozhao on 7/24/17.
 */
class Article {
    @SerializedName("id")
    var id: String? = ""
    @SerializedName("title")
    var title: String? = ""
    @SerializedName("source")
    var source: String? = ""
    @SerializedName("firstImg")
    var firstImg: String? = ""
    @SerializedName("mark")
    var mark: String? = ""
    @SerializedName("url")
    var url: String? = ""
}