package com.kryptkode.users.ui.detail.view

import android.view.View
import com.kryptkode.basemvi.UiView
import com.kryptkode.feature.users.databinding.IncludeDetailsContactBinding
import com.kryptkode.users.ui.detail.UserDetailViewState
import java.util.Locale

class UserContactView(private val binding: IncludeDetailsContactBinding) : UiView() {

    fun render(state: UserDetailViewState) {
        val user = state.userDetail
        binding.tvEmail.text = user.email.toLowerCase(Locale.getDefault())
        binding.tvPhone.text = user.phone
    }

    override val rootView: View
        get() = binding.root
}
