package com.example.moviesapi.di

import android.util.Log
import com.example.moviesapi.commom.util.Constants
import com.example.moviesapi.detail.data.remote.api.DetailService
import com.example.moviesapi.detail.data.repository.DetailRepository
import com.example.moviesapi.detail.data.repository.DetailRepositoryImpl
import com.example.moviesapi.home.data.remote.api.HomeService
import com.example.moviesapi.home.data.repository.HomeRepository
import com.example.moviesapi.home.data.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Log.i("API_LOG", message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }


    @Singleton
    @Provides
    fun provideDetailService(retrofit: Retrofit): DetailService {
        return retrofit.create(DetailService::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(service: HomeService): HomeRepository {
        return HomeRepositoryImpl(service)
    }

    @Singleton
    @Provides
    fun provideDetailRepository(service: DetailService): DetailRepository{
        return DetailRepositoryImpl(service)
    }


}