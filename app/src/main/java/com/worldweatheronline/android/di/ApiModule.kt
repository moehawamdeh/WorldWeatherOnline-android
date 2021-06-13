package com.worldweatheronline.android.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.worldweatheronline.android.BuildConfig
import com.worldweatheronline.android.Constants
import com.worldweatheronline.android.data.remote.services.AutoCompleteService
import com.worldweatheronline.android.data.remote.services.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Provides
    fun provideBaseUrl() = Constants.WWO_BASE_URL


    @Provides
    @Singleton
    fun provideGson(): Gson? {
        val gsonBuilder = GsonBuilder()
        gsonBuilder
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
        return gsonBuilder.create()
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor { chain ->
            var request = chain.request()
            val httpUrl = request.url
                    .newBuilder()
                    .addQueryParameter("key", BuildConfig.WWO_API_KEY)
                    .addQueryParameter("format", "json")
//                    .addQueryParameter("lang","ar") //add Arabic translations
                    .addQueryParameter("aqi", "no") //remove air quality data
                    .addQueryParameter("showlocaltime", "no") //remove localtime
                    .addQueryParameter("mca", "no") //monthly   localtime

                    .build()
            request = request.newBuilder()
                    .url(httpUrl)
                    .build()
            chain.proceed(request)
        }
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            //log only in debug
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, WWO_BASE_URL: String): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(WWO_BASE_URL)
            .client(okHttpClient)
            .build()



    // API Services
    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideAutoCompleteService(retrofit: Retrofit): AutoCompleteService {
        return retrofit.create(AutoCompleteService::class.java)
    }

    @Provides
    fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }
}