package com.leozhao.weijing.view

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.leozhao.weijing.Constant
import com.leozhao.weijing.R
import android.webkit.WebSettings



class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val url = intent.extras.getString(Constant.ARTICLE_URL)
        val author = intent.extras.getString(Constant.ARTICLE_AUTHOR)
        Log.d(Constant.DEBUG_TAG, "Article detail - URL is: $url")
        Log.d(Constant.DEBUG_TAG, "Article detail - Author is: $author")
        title=author
        val webView: WebView = findViewById(R.id.webview) as WebView
        webView.setWebChromeClient(WebChromeClient())
        webView.setWebViewClient(WebViewClient())
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.setSupportZoom(true)
        settings.builtInZoomControls = false
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.domStorageEnabled = true
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        } else {
            // older android version, disable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }
        webView.loadUrl(url)
    }

}