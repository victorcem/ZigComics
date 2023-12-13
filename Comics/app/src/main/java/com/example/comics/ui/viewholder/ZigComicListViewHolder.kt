package com.example.comics.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comics.R
import com.example.comics.model.Comic
import kotlinx.android.synthetic.main.item_list.view.*

class ZigComicListViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: OnZigComicClickListener
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list, parent, false)) {

    fun bind(comic: Comic) {
        itemView.apply {
            action_title.text = comic.title
            action_sub_title.text = comic.description

            Glide.with(context)
                .load("${comic.thumbnail?.path}/standard_amazing.${comic.thumbnail?.extension}")
                .into(action_image)
        }
    }


    interface OnZigComicClickListener {
        fun onZigComicClick(comic: Comic, itemPosition: Int)
    }
}