package com.giussepr.ceiba.core.di

import com.giussepr.ceiba.data.mapper.PublicationMapper
import com.giussepr.ceiba.data.mapper.UserResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RemoteMappers {

    @Provides
    fun provideUserRemoteMapper() = UserResponseMapper()

    @Provides
    fun providePublicationMapper() = PublicationMapper()
}