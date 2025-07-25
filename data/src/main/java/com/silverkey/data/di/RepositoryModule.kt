package com.silverkey.data.di

import com.silverkey.data.local.dao.PhotoDao
import com.silverkey.data.remote.api.PhotoApiService
import com.silverkey.data.repository.PhotoRepositoryImpl
import com.silverkey.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePhotoRepository(
        api: PhotoApiService,
        dao: PhotoDao
    ): PhotoRepository {
        return PhotoRepositoryImpl(api, dao)
    }
}