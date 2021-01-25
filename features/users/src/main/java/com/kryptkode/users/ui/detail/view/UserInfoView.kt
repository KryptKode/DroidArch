package com.kryptkode.users.ui.detail.view

import android.view.View
import com.kryptkode.basemvi.UiView
import com.kryptkode.core.imageloader.ImageLoader
import com.kryptkode.feature.users.R
import com.kryptkode.feature.users.databinding.IncludeDetailsPersonalBinding
import com.kryptkode.users.ui.detail.UserDetailViewState
import java.util.Locale

class UserInfoView(
    private val binding: IncludeDetailsPersonalBinding,
    private val imageLoader: ImageLoader
) : UiView() {

    fun render(state: UserDetailViewState) {
        val user = state.userDetail

        imageLoader.load(user.picture, binding.imagePic)

        binding.tvGender.text = user.gender.capitalize(Locale.getDefault())
        binding.tvDob.text = getString(R.string.user_dob, user.dateOfBirth)

        binding.tvFullName.text = getString(
            R.string.name_label,
            user.title.capitalize(Locale.getDefault()),
            user.firstName.capitalize(Locale.getDefault()),
            user.lastName.capitalize(Locale.getDefault())
        )
    }

    override val rootView: View
        get() = binding.root
}
