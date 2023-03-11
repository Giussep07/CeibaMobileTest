package com.giussepr.ceiba.core.di

import com.giussepr.ceiba.data.mapper.UserResponseMapper
import com.giussepr.ceiba.data.repository.CeibaRepositoryImpl
import com.giussepr.ceiba.data.repository.datasource.remote.CeibaRemoteDataSource
import com.giussepr.ceiba.domain.repository.CeibaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideCeibaRepository(
        remoteDataSource: CeibaRemoteDataSource,
        userResponseMapper: UserResponseMapper
    ): CeibaRepository = CeibaRepositoryImpl(remoteDataSource, userResponseMapper)
}