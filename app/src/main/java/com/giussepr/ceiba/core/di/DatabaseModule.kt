package com.giussepr.ceiba.core.di

import android.content.Context
import androidx.room.Room
import com.giussepr.ceiba.data.database.CeibaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCeibaDatabase(@ApplicationContext appContext: Context): CeibaDatabase {
        return Room.databaseBuilder(
            appContext,
            CeibaDatabase::class.java,
            "ceiba_database"
        )
            .build()
    }

    @Provides
    fun provideUserDao(ceibaDatabase: CeibaDatabase) = ceibaDatabase.userDao()
}