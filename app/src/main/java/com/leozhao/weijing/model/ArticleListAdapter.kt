package com.leozhao.weijing.model

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.leozhao.weijing.R

/**
 * Created by dozhao on 7/24/17.
 */

class ArticleListAdapter(private val articleList: List<Article>):
        RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(var mCardView: CardView) : RecyclerView.ViewHolder(mCardView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ArticleListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false) as CardView
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val textView = holder.mCardView.findViewById(R.id.title) as TextView
        textView.text = articleList[position].title

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return articleList.size
    }
}