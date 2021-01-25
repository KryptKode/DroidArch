package com.kryptkode.droidarch.navigator

import com.kryptkode.users.navigator.UsersNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
interface ScreenNavigatorModule {

    @Binds
    @ActivityScoped
    fun provideScreenNavigator(navigator: ScreenNavigator): UsersNavigator
}
