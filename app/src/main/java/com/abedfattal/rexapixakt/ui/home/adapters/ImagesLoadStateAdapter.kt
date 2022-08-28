package com.abedfattal.rexapixakt.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abedfattal.rexapixakt.databinding.ItemLoadingFooterBinding

class ImagesLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ImagesLoadStateAdapter.LoadStateViewHolder>() {


    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            ItemLoadingFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LoadStateViewHolder(binding, retry)
    }

    class LoadStateViewHolder(
        private val binding: ItemLoadingFooterBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retry = retry
        }

        fun bind(state: LoadState) {
            binding.errorMsg= getErrorMessage(state)
            binding.loadState = state
            binding.executePendingBindings()
        }

        private fun getErrorMessage(loadState: LoadState) =
            if (loadState is LoadState.Error) loadState.error.message
            else null
    }

}