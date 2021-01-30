package com.koba.memestorage.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.koba.memestorage.R
import com.koba.memestorage.data.MediaItem

class GalleryAdapter: ListAdapter<MediaItem, GalleryAdapter.GalleryViewHolder>(MediaItemDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.gallery_layout, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = getItem(position)

        Glide.with(holder.ivThumbnail)
            .load(item.uri)
            .centerCrop()
            .into(holder.ivThumbnail)
    }

    inner class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var ivThumbnail: ImageView = view.findViewById(R.id.iv_thumbnail)
    }

    class MediaItemDiffCallback : DiffUtil.ItemCallback<MediaItem>() {
        override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem == newItem
        }
    }
}