package com.abedfattal.rexapixakt.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abedfattal.rexapixakt.databinding.ItemImageBinding
import com.abedfattal.rexapixakt.models.domain.Image

class ImagesListAdapter : ListAdapter<Image, ImagesListAdapter.ImageViewHolder>(object :
    DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(
        oldDirectionItem: Image,
        newDirectionItem: Image
    ): Boolean =
        oldDirectionItem.id == newDirectionItem.id

    override fun areContentsTheSame(
        oldDirectionItem: Image,
        newDirectionItem: Image
    ): Boolean = true
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image) {
            binding.image = image
            binding.executePendingBindings()

        }
    }
}