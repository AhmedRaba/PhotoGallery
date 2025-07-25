package com.silverkey.data.di

import android.os.Build
import com.silverkey.data.BuildConfig
import com.silverkey.data.remote.api.PhotoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object NetworkModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {

        private const val BASE_URL = "https://api.pexels.com/v1/"


        @Provides
        @Singleton
        fun provideAuthInterceptor(): Interceptor {
            return Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", BuildConfig.API_KEY)
                    .build()
                chain.proceed(request)
            }
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun providePhotoApi(retrofit: Retrofit): PhotoApiService {
            return retrofit.create(PhotoApiService::class.java)
        }
    }

}