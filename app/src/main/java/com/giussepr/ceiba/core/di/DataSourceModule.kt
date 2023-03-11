package com.giussepr.ceiba.core.di

import com.giussepr.ceiba.data.api.JsonPlaceholderApi
import com.giussepr.ceiba.data.repository.datasource.remote.CeibaRemoteDataSource
import com.giussepr.ceiba.data.repository.datasource.remote.CeibaRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataSourceModule {

    @Provides
    fun provideCeibaRemoteDataSource(jsonPlaceholderApi: JsonPlaceholderApi): CeibaRemoteDataSource =
        CeibaRemoteDataSourceImpl(jsonPlaceholderApi)
}