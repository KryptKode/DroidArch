package com.kryptkode.users.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kryptkode.commonandroid.viewbinding.viewBinding
import com.kryptkode.feature.users.R
import com.kryptkode.feature.users.databinding.LayoutUsersBinding
import com.kryptkode.users.ui.list.view.UserListView

class UserListFragment : Fragment(R.layout.layout_users) {

    private val binding by viewBinding(LayoutUsersBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UserListView(binding)
    }
}
