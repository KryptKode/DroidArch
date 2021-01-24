package com.kryptkode.users.ui.list.view

import android.view.View
import androidx.paging.LoadState
import com.kryptkode.basemvi.UiView
import com.kryptkode.commonandroid.extension.beVisibleIf
import com.kryptkode.feature.users.databinding.LayoutUsersBinding
import com.kryptkode.users.ui.list.UserListState
import com.kryptkode.users.ui.list.UserListViewEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

class UserListView(
    private val binding: LayoutUsersBinding
) : UiView() {

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
        }
    }

    val events: Flow<UserListViewEvent>
        get() = merge(
            adapter.onClickItemFlow
                .map { UserListViewEvent.ClickUser(it) },
            adapter.loadStateErrorFlow.map { UserListViewEvent.ShowError(it) }
        )

    suspend fun render(state: UserListState) {
        adapter.submitData(state.items)
    }

    override val rootView: View
        get() = binding.root
}
