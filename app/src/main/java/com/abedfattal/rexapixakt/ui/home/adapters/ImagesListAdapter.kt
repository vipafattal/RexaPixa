package com.abedfattal.rexapixakt.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abedfattal.rexapixakt.databinding.ItemImageBinding
import com.abedfattal.rexapixakt.models.domain.Image
import com.abedfattal.rexapixakt.ui.home.HomeFragmentDirections
import com.abedfattal.rexapixakt.utils.shortToast

class ImagesListAdapter : PagingDataAdapter<Image, ImagesListAdapter.ImageViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { openDetailsPage() }
        }

        fun bind(image: Image) {
            binding.image = image
            binding.executePendingBindings()

        }

        private fun openDetailsPage() {
            val image = getItem(bindingAdapterPosition)
            if (image == null)
                shortToast(itemView.context, "No image found at position[$bindingAdapterPosition]")
            else {
                itemView.findNavController().navigate(
                    directions = HomeFragmentDirections.actionHomeFragmentToImageDetailsFragment(
                        image
                    ),
                )
            }
        }
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(
                oldDirectionItem: Image,
                newDirectionItem: Image
            ): Boolean =
                oldDirectionItem.id == newDirectionItem.id

            override fun areContentsTheSame(
                oldDirectionItem: Image,
                newDirectionItem: Image
            ): Boolean = true
        }
    }

}