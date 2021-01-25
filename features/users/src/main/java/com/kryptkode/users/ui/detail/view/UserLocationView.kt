package com.kryptkode.users.ui.detail.view

import android.view.View
import com.kryptkode.basemvi.UiView
import com.kryptkode.feature.users.R
import com.kryptkode.feature.users.databinding.IncludeDetailsAddressBinding
import com.kryptkode.users.ui.detail.UserDetailViewState
import java.util.Locale

class UserLocationView(private val binding: IncludeDetailsAddressBinding) : UiView() {

    fun render(state: UserDetailViewState) {
        val user = state.userDetail
        binding.tvAddress.text = getString(
            R.string.user_address,
            user.location.street.capitalize(Locale.getDefault()),
            user.location.city.capitalize(Locale.getDefault()),
            user.location.state.capitalize(Locale.getDefault())
        )

        binding.tvRegion.text = getString(
            R.string.user_region,
            user.location.country.capitalize(Locale.getDefault()),
            user.location.timezone.toUpperCase(Locale.getDefault()),
        )
    }

    override val rootView: View
        get() = binding.root
}
