package com.example.mobileshowroom.hilt

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mobileshowroom.hilt.service.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient{
        val duration = Duration.ofSeconds(15)
        return OkHttpClient().newBuilder()
            .connectTimeout(duration)
            .readTimeout(duration)
            .writeTimeout(duration)
            .build()
    }

    @Provides
    @Singleton
    fun providesProductService(retrofit: Retrofit): ProductService {
       return retrofit.create(ProductService::class.java)
    }
}