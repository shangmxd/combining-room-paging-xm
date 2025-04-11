package com.example.roomandpagingwithxml.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.roomandpagingwithxml.R
import com.example.roomandpagingwithxml.data.Manga
import com.example.roomandpagingwithxml.utils.RecyclerViewInterface

class MangaRecyclerViewAdapter(recyclerViewClickInterface: RecyclerViewInterface):PagingDataAdapter<Manga,MangaRecyclerViewAdapter.MyViewHolder>(simpleDiffCallback) {

    private var recyclerViewInterface:RecyclerViewInterface

    init {
        this.recyclerViewInterface = recyclerViewClickInterface
    }

    companion object {
        val simpleDiffCallback = object :DiffUtil.ItemCallback<Manga>() {
            override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
                return oldItem.mal_id == newItem.mal_id
            }

            override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manga_recycler_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.title_tv).text = currentItem?.title
        holder.itemView.findViewById<TextView>(R.id.synopsis_tv).text = currentItem?.synopsis
        holder.itemView.findViewById<TextView>(R.id.chapters_tv).text = "Chapters: ${(currentItem?.chapters?:"unavailable")}"
        holder.itemView.findViewById<TextView>(R.id.score_tv).text = "Score: ${currentItem?.score?:0}"
        holder.itemView.findViewById<ImageView>(R.id.manga_iv).load(currentItem?.images?.jpg?.image_url)
        holder.itemView.findViewById<ImageView>(R.id.save_iv).setOnClickListener {
            recyclerViewInterface.onSaveClicked(currentItem?:null)
        }
    }

}