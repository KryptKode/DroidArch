package com.kryptkode.users.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kryptkode.commonandroid.flowbinding.lifecycleAwareLaunch
import com.kryptkode.commonandroid.viewbinding.viewBinding
import com.kryptkode.core.imageloader.ImageLoader
import com.kryptkode.feature.users.R
import com.kryptkode.feature.users.databinding.LayoutUserDetailsBinding
import com.kryptkode.users.model.User
import com.kryptkode.users.navigator.UsersNavigator
import com.kryptkode.users.ui.detail.view.UserContactView
import com.kryptkode.users.ui.detail.view.UserInfoView
import com.kryptkode.users.ui.detail.view.UserLocationView
import com.kryptkode.users.ui.detail.view.UserTitleView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.layout_user_details) {

    @Inject
    lateinit var navigator: UsersNavigator

    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel: UserDetailViewModel by viewModels()

    private val binding by viewBinding(LayoutUserDetailsBinding::bind)

    private val user by lazy { arguments?.getParcelable<User>(USER_ARG_KEY)!! }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.getUserDetails(user)
        }

        val userTitleView = UserTitleView(binding.tvTitle)
        val userInfoView = UserInfoView(binding.cardPersonalDetails, imageLoader)
        val userLocation = UserLocationView(binding.cardAddress)
        val userContactView = UserContactView(binding.cardContact)

        viewModel.viewState.onEach {
            userInfoView.render(it)
            userLocation.render(it)
            userContactView.render(it)
        }.lifecycleAwareLaunch(viewLifecycleOwner)

        merge(userTitleView.events)
            .onEach(::handleEvent)
            .lifecycleAwareLaunch(viewLifecycleOwner)
    }

    private fun handleEvent(event: UserDetailViewEvent) {
        when (event) {
            is UserDetailViewEvent.NavigateBack -> {
                navigator.navigateUp()
            }
        }
    }

    companion object {
        private const val USER_ARG_KEY = "user_arg"

        fun createArguments(user: User): Bundle {
            val args = Bundle()
            args.putParcelable(USER_ARG_KEY, user)
            return args
        }
    }
}
