package com.kryptkode.users.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DisplayDateModule {

    @Provides
    @Singleton
    fun provideDisplayedDateFormatter(): DisplayedDateFormatter {
        return DisplayedDateFormatter()
    }

    @Provides
    @Singleton
    fun provideDisplayedDateTimeFormatter(): DisplayedDateTimeFormatter {
        return DisplayedDateTimeFormatter()
    }
}
