package com.kryptkode.users.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kryptkode.commonandroid.ToastHelper
import com.kryptkode.commonandroid.flowbinding.lifecycleAwareLaunch
import com.kryptkode.commonandroid.viewbinding.viewBinding
import com.kryptkode.core.imageloader.ImageLoader
import com.kryptkode.feature.users.R
import com.kryptkode.feature.users.databinding.LayoutUsersBinding
import com.kryptkode.users.navigator.UsersNavigator
import com.kryptkode.users.ui.list.view.UserListView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.layout_users) {

    @Inject
    lateinit var navigator: UsersNavigator

    @Inject
    lateinit var toastHelper: ToastHelper

    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel: UserListViewModel by viewModels()

    private val binding by viewBinding(LayoutUsersBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userListView = UserListView(binding, imageLoader)

        viewModel.viewState.onEach {
            userListView.render(it)
        }.lifecycleAwareLaunch(viewLifecycleOwner)

        userListView.events
            .onEach(::handleEvent)
            .lifecycleAwareLaunch(viewLifecycleOwner)
    }

    private fun handleEvent(event: UserListViewEvent) {
        when (event) {
            is UserListViewEvent.ShowError -> {
                toastHelper.showMessage(event.error.localizedMessage ?: "An error occurred")
            }

            is UserListViewEvent.ClickUser -> {
                navigator.toUserDetail(event.user)
            }
        }
    }
}
