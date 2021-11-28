package com.mindorks.framework.mvi.data.di

import android.app.Application
import com.mindorks.framework.mvi.data.api.UserApi
import com.mindorks.framework.mvi.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ViewScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://5e510330f2c0d300147c034c.mockapi.io/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(provideBaseUrl())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepo(userApi: UserApi) : MainRepository{
        return MainRepository(userApi)
    }
}