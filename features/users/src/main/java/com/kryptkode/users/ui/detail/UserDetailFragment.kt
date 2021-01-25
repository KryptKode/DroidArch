package com.kryptkode.users.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kryptkode.commonandroid.viewbinding.viewBinding
import com.kryptkode.feature.users.R
import com.kryptkode.feature.users.databinding.LayoutUserDetailsBinding
import com.kryptkode.users.model.User
import com.kryptkode.users.navigator.UsersNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.layout_user_details) {

    @Inject
    lateinit var navigator: UsersNavigator

    private val viewModel: UserDetailViewModel by viewModels()

    private val binding by viewBinding(LayoutUserDetailsBinding::bind)

    private val user by lazy { arguments?.getParcelable<User>(USER_ARG_KEY)!! }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.getUserDetails(user.id)
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
