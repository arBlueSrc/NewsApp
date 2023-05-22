package com.majazi.newsapplication.peresentation.di

import com.majazi.newsapplication.domien.repository.NewsRepository
import com.majazi.newsapplication.domien.usecase.GetDetailNewsUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsListUseCase
import com.majazi.newsapplication.domien.usecase.GetNewsUseCase
import com.majazi.newsapplication.domien.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetNewsUseCase(
        newsRepository: NewsRepository
    ):GetNewsUseCase{
        return GetNewsUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideGetNewsListUseCase(
        newsRepository: NewsRepository
    ):GetNewsListUseCase{
        return GetNewsListUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideGetDetailNewsUseCase(
        newsRepository: NewsRepository
    ):GetDetailNewsUseCase{
        return GetDetailNewsUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideSaveNewsUseCase(
        newsRepository: NewsRepository
    ):SaveNewsUseCase{
        return SaveNewsUseCase(newsRepository)
    }
}