package com.kryptkode.users.ui.detail.view

import android.view.View
import android.widget.TextView
import com.kryptkode.basemvi.UiView
import com.kryptkode.commonandroid.flowbinding.clicks
import com.kryptkode.users.ui.detail.UserDetailViewEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserTitleView(private val textView: TextView) : UiView() {

    val events: Flow<UserDetailViewEvent>
        get() = textView.clicks().map { UserDetailViewEvent.NavigateBack }

    override val rootView: View
        get() = textView
}
