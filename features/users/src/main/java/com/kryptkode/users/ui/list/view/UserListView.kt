package com.kryptkode.users.ui.list.view

import android.view.View
import androidx.paging.LoadState
import com.kryptkode.basemvi.UiView
import com.kryptkode.commonandroid.extension.beVisibleIf
import com.kryptkode.feature.users.databinding.LayoutUsersBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserListView(
    private val binding: LayoutUsersBinding
) : UiView<UserState, UserListViewIntent>() {

    private val adapter = UserListAdapter()

    init {
        binding.retryButton.setOnClickListener { adapter.retry() }
        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }
        binding.usersRecyclerView.setEmptyView(binding.emptyStateGroup)

        binding.usersRecyclerView.adapter = adapter
        adapter.withLoadStateHeaderAndFooter(
            header = UserLoadStateAdapter { adapter.retry() },
            footer = UserLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            // Show loading spinner during initial load or refresh.
            binding.swipeRefresh.isRefreshing = (loadState.refresh is LoadState.Loading)
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.beVisibleIf(loadState.refresh is LoadState.Error)

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let { error ->
            }
        }
    }

    override val intents: Flow<UserListViewIntent>
        get() = adapter.onClickItemFlow
            .map { UserListViewIntent.ClickUser(it) }

    override fun render(state: UserState) {
    }

    override val rootView: View
        get() = binding.root
}
