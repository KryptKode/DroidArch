package com.kryptkode.users.ui.detail.view

import android.view.View
import com.kryptkode.basemvi.UiView
import com.kryptkode.commonandroid.extension.beVisibleIf
import com.kryptkode.feature.users.databinding.IncludeProgressBinding
import com.kryptkode.users.ui.detail.UserDetailViewState

class ProgressView(private val binding: IncludeProgressBinding) : UiView() {

    fun render(state: UserDetailViewState) {
        binding.root.beVisibleIf(state.loading)
    }

    override val rootView: View
        get() = binding.root
}
