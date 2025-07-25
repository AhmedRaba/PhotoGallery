package com.silverkey.data.di

import com.silverkey.domain.repository.PhotoRepository
import com.silverkey.domain.usecase.CachePhotosUseCase
import com.silverkey.domain.usecase.FetchPhotosUseCase
import com.silverkey.domain.usecase.GetCachedPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchPhotosUseCase(repository: PhotoRepository): FetchPhotosUseCase {
        return FetchPhotosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCachePhotosUseCase(repository: PhotoRepository): CachePhotosUseCase {
        return CachePhotosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCachedPhotosUseCase(repository: PhotoRepository): GetCachedPhotosUseCase {
        return GetCachedPhotosUseCase(repository)
    }
}