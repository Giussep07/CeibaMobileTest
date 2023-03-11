package com.giussepr.ceiba.core.di

import com.giussepr.ceiba.domain.repository.CeibaRepository
import com.giussepr.ceiba.domain.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPostsUseCase(ceibaRepository: CeibaRepository) =
        GetUsersUseCase(ceibaRepository)
}