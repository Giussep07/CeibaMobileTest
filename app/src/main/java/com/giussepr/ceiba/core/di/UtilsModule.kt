package com.giussepr.ceiba.core.di

import com.giussepr.ceiba.utils.DefaultDispatcherProvider
import com.giussepr.ceiba.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UtilsModule {

    @Provides
    fun provideDispatcherProvider():DispatcherProvider = DefaultDispatcherProvider()
}