package com.example.roomandpagingwithxml.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomandpagingwithxml.R
import com.example.roomandpagingwithxml.database.data.MangaData

class SavedMangasRecyclerViewAdapter : ListAdapter<MangaData, SavedMangasRecyclerViewAdapter.MyViewHolder>(
    simpleDiffUtil
) {

    companion object {
        val simpleDiffUtil = object : DiffUtil.ItemCallback<MangaData>() {
            override fun areItemsTheSame(oldItem: MangaData, newItem: MangaData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MangaData, newItem: MangaData): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_manga_recycler_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.findViewById<ImageView>(R.id.saved_manga_iv).setImageBitmap(item.mangaImage)
        holder.itemView.findViewById<TextView>(R.id.manga_title_tv).text = item?.mangaTitle
        holder.itemView.findViewById<TextView>(R.id.manga_synopsis_tv).text = item?.mangaDescription
        holder.itemView.findViewById<TextView>(R.id.manga_chapters_tv).text =
            "Chapters: ${(item?.mangaChapters ?: "unavailable")}"
        holder.itemView.findViewById<TextView>(R.id.manga_score_tv).text =
            "Score: ${item?.mangaScore ?: 0}"
    }
}