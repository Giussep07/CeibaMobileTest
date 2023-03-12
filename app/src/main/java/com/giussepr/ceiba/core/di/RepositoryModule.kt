package com.giussepr.ceiba.core.di

import android.content.Context
import com.giussepr.ceiba.data.mapper.PublicationMapper
import com.giussepr.ceiba.data.mapper.UserResponseMapper
import com.giussepr.ceiba.data.repository.CeibaRepositoryImpl
import com.giussepr.ceiba.data.repository.datasource.local.CeibaLocalDataSource
import com.giussepr.ceiba.data.repository.datasource.remote.CeibaRemoteDataSource
import com.giussepr.ceiba.data.utils.NetworkUtils
import com.giussepr.ceiba.domain.repository.CeibaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideCeibaRepository(
        remoteDataSource: CeibaRemoteDataSource,
        localDataSource: CeibaLocalDataSource,
        userResponseMapper: UserResponseMapper,
        publicationMapper: PublicationMapper,
        networkUtils: NetworkUtils
    ): CeibaRepository = CeibaRepositoryImpl(
        remoteDataSource,
        localDataSource,
        userResponseMapper,
        publicationMapper,
        networkUtils
    )

    @Provides
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils =
        NetworkUtils(context)
}