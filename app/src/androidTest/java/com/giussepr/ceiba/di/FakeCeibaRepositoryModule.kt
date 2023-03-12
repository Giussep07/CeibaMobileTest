package com.giussepr.ceiba.di

import com.giussepr.ceiba.core.di.RepositoryModule
import com.giussepr.ceiba.domain.model.Publication
import com.giussepr.ceiba.domain.model.Result
import com.giussepr.ceiba.domain.model.User
import com.giussepr.ceiba.domain.repository.CeibaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
@Module
object FakeCeibaRepositoryModule {

    @Singleton
    @Provides
    fun provideFakeCeibaRepository(): CeibaRepository = object : CeibaRepository {
        override fun getUsers(): Flow<Result<List<User>>> = flow {
            emit(Result.Success(buildList {
                add(User(id = 1, name = "Leanne Graham", email = "Leanne.Graham@gmail.com", phone = "3124609512"))
                add(User(id = 2, name = "Ervin Howell", email = "Ervin.Howell@gmail.com", phone = "3124609512"))
                add(User(id = 3, name = "Clementine Bauch", email = "Clementine.Bauch@gmail.com", phone = "3124609512"))
                add(User(id = 4, name = "Patricia Lebsack", email = "Patricia.Lebsack@gmail.com", phone = "3124609512"))
            }))
        }

        override fun getUserPublications(userId: Int): Flow<Result<List<Publication>>> = flow {
            emit(Result.Success(emptyList()))
        }
    }
}