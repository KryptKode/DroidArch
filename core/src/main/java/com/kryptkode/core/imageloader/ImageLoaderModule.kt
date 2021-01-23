package com.kryptkode.core.imageloader

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
interface ImageLoaderModule {

    @Binds
    @ActivityScoped
    fun provideImageLoader(dispatchers: ImageLoaderImpl): ImageLoader
}
