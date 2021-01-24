package com.kryptkode.users.ui.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kryptkode.commonandroid.extension.beVisibleIf
import com.kryptkode.feature.users.databinding.NetworkStateItemBinding

class UserLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<UserLoadStateAdapter.FarmerLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): FarmerLoadStateViewHolder {

        return FarmerLoadStateViewHolder(
            NetworkStateItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FarmerLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class FarmerLoadStateViewHolder(private val binding: NetworkStateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }

            binding.progressBar.beVisibleIf(loadState is LoadState.Loading)
            binding.retryButton.beVisibleIf(loadState !is LoadState.Loading)
            binding.errorMsg.beVisibleIf(loadState !is LoadState.Loading)

            binding.retryButton.setOnClickListener {
                retry()
            }
        }
    }
}
