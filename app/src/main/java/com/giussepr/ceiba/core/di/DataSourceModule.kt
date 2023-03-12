package com.giussepr.ceiba.core.di

import com.giussepr.ceiba.data.api.JsonPlaceholderApi
import com.giussepr.ceiba.data.database.dao.PublicationDao
import com.giussepr.ceiba.data.database.dao.UserDao
import com.giussepr.ceiba.data.repository.datasource.local.CeibaLocalDataSource
import com.giussepr.ceiba.data.repository.datasource.local.CeibaLocalDataSourceImpl
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

    @Provides
    fun provideCeibaLocalDataSource(
        userDao: UserDao,
        publicationDao: PublicationDao
    ): CeibaLocalDataSource =
        CeibaLocalDataSourceImpl(userDao, publicationDao)
}