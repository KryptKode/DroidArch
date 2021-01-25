package com.kryptkode.users.ui.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kryptkode.core.imageloader.ImageLoader
import com.kryptkode.feature.users.R
import com.kryptkode.feature.users.databinding.ItemUserBinding
import com.kryptkode.users.model.User
import java.util.Locale
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class UserListAdapter(private val imageLoader: ImageLoader) :
    PagingDataAdapter<User, UserListAdapter.UserListViewHolder>(UI_MODEL_DIFF_UTIL) {

    private var listener: Listener? = null

    interface Listener {
        fun onItemClick(user: User)
    }

    val onClickItemFlow = callbackFlow<User> {
        listener = object : Listener {
            override fun onItemClick(user: User) {
                offer(user)
            }
        }
        awaitClose { listener = null }
    }

    val loadStateErrorFlow = callbackFlow {
        val loadStateListener = { loadState: CombinedLoadStates ->
            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                offer(it.error)
            }
            Unit
        }

        addLoadStateListener(loadStateListener)
        awaitClose { removeLoadStateListener(loadStateListener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            ItemUserBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserListViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User?) {
            user?.let {
                binding.nameTextView.text = getString(
                    R.string.name_label,
                    user.title.capitalize(Locale.getDefault()),
                    user.firstName.capitalize(Locale.getDefault()),
                    user.lastName.capitalize(Locale.getDefault())
                )

                binding.emailTextView.text = user.email

                imageLoader.load(user.picture, binding.image)

                binding.root.setOnClickListener {
                    listener?.onItemClick(user)
                }
            }
        }

        private fun getString(resId: Int, vararg args: Any): String {
            return binding.root.context.getString(resId, *args)
        }
    }

    companion object {
        private val UI_MODEL_DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}
