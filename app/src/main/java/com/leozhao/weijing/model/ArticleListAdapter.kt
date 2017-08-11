package com.leozhao.weijing.model

import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.leozhao.weijing.Constant
import com.leozhao.weijing.R
import com.leozhao.weijing.view.ArticleActivity

/**
 * Created by dozhao on 7/24/17.
 */

object ArticleListAdapter:
        RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {
    private var mArticleList: MutableList<Article> = mutableListOf()

    fun addArticleList(articleList: List<Article>) {
        mArticleList.addAll(articleList)
    }

    fun clearArticleList() {
        mArticleList.clear()
    }

    fun totalArticleNumber() = mArticleList.size


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(var mCardView: CardView) : RecyclerView.ViewHolder(mCardView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ArticleListAdapter.ViewHolder {
        Log.d(Constant.DEBUG_TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false) as CardView
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(Constant.DEBUG_TAG, "onBindViewHolder, postion: $position")
        val cardView = holder.mCardView
        val article = mArticleList[position]

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val textView = cardView.findViewById(R.id.title) as TextView
        textView.text = article.title

        val imageView = cardView.findViewById(R.id.first_image) as ImageView
        val imgUrl = article.firstImg
        Glide.with(cardView.context)
                .load(imgUrl)
                .placeholder(R.drawable.image_placeholder)
                .into(imageView)
        cardView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(Constant.ARTICLE_URL, article.url)
            intent.putExtra(Constant.ARTICLE_AUTHOR, article.source)
            context.startActivity(intent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mArticleList.size
    }

    override fun onViewRecycled(holder: ViewHolder?) {
        super.onViewRecycled(holder)
        val v1 = holder?.oldPosition
        val v2 = holder?.position
        val v3 = holder?.adapterPosition
        val v4 = holder?.layoutPosition
        Log.d(Constant.DEBUG_TAG, "onViewRecycled: oldPostion: $v1, postion: $v2, adapterPostion: $v3, layoutPostion: $v4")
    }


}