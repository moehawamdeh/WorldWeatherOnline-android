package com.worldweatheronline.android.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.worldweatheronline.android.BuildConfig
import com.worldweatheronline.android.Constants
import com.worldweatheronline.android.data.remote.services.AutoCompleteService
import com.worldweatheronline.android.data.remote.services.WeatherService
import com.worldweatheronline.android.helper.IconsMappingHelper
import com.worldweatheronline.android.util.LocalParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class HelperModule {

    @Provides
    @Singleton
    @Nullable
    fun provideIconsMappingHelper(@ApplicationContext appContext: Context): IconsMappingHelper? {
        //TODO extract utility
        val gson = Gson()
        val map = gson.fromJson<Map<String,String>>(
            LocalParser.loadJSONFromAsset(appContext),
            MutableMap::class.java
        )
        val helper = IconsMappingHelper(map);
        return helper;
    }
}