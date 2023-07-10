package com.reydix.assignment.data.di

import com.reydix.assignment.BuildConfig
import com.reydix.assignment.data.network.PopularPokemonNetworkManager
import com.reydix.assignment.data.network.PopularPokemonService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providePopularPokemonApiService(retrofit: Retrofit): PopularPokemonService {
        return retrofit.create(PopularPokemonService::class.java)
    }


    @Provides
    @Singleton
    fun providePopularPokemonNetworkManager(apiService: PopularPokemonService): PopularPokemonNetworkManager {
        return PopularPokemonNetworkManager(apiService)
    }

    @Provides
    @Singleton
    fun provideRetrofitJson(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.baseNetworkUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {

            // Http Logging interceptor
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(interceptor)
        }


        return clientBuilder.connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .build()
    }
}