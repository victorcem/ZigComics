package com.example.comics.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comics.model.Comic
import com.example.comics.ui.viewholder.ZigComicListViewHolder


class ZigComicListAdapter(
    private val comics: List<Comic>,
    private val listener: ZigComicListViewHolder.OnZigComicClickListener
) : RecyclerView.Adapter<ZigComicListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZigComicListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ZigComicListViewHolder(inflater, parent, listener)
    }

    override fun onBindViewHolder(holder: ZigComicListViewHolder, position: Int) {
        val comics = comics[position]
        holder.bind(comics)
    }

    override fun getItemCount(): Int = comics.size

}