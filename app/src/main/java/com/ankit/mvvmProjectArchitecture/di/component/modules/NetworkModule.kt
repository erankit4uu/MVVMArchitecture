package com.ankit.mvvmProjectArchitecture.di.component.modules

import com.ankit.mvvmProjectArchitecture.implementation.ApiEndPointImpl
import com.ankit.mvvmProjectArchitecture.implementation.EndPoint
import com.ankit.mvvmProjectArchitecture.utils.Constants
import com.ankit.mvvmProjectArchitecture.utils.Constants.Companion.ENDPOINT_V1
import com.ankit.mvvmProjectArchitecture.utils.Constants.Companion.GSON_V1
import com.ankit.mvvmProjectArchitecture.utils.Constants.Companion.INTERCEPTOR_HEADER_V1
import com.ankit.mvvmProjectArchitecture.utils.Constants.Companion.INTERCEPTOR_LOGGING_V1
import com.ankit.mvvmProjectArchitecture.utils.Constants.Companion.INTERCEPTOR_RESPONSE_V1
import com.ankit.mvvmProjectArchitecture.utils.Constants.Companion.OKHHTP_CACHE_V1
import com.ankit.mvvmProjectArchitecture.utils.Constants.Companion.OKHHTP_CLIENT_V1
import com.ankit.mvvmProjectArchitecture.utils.Constants.Companion.RETROFIT_V1
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named(INTERCEPTOR_LOGGING_V1)
    internal fun provideLoggingInterceptorV1(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
    @Provides
    @Named(ENDPOINT_V1)
    internal fun provideEndPointV1(@Named(Constants.API_DEVELOPMENT_URL)
                                   URL: String): EndPoint {
        return ApiEndPointImpl().setEndPoint(URL)
    }
    @Provides
    @Singleton
    @Named(OKHHTP_CLIENT_V1)
    internal fun provideOkHttpClientV1(@Named(OKHHTP_CACHE_V1) cache: Cache,
                                       @Named(INTERCEPTOR_LOGGING_V1) logging: HttpLoggingInterceptor,
                                       @Named(INTERCEPTOR_HEADER_V1) headerInterceptor: Interceptor,
                                       @Named(INTERCEPTOR_RESPONSE_V1) globalResponseInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        //builder.networkInterceptors().add(cachingControlInterceptor);

        return builder
            .addInterceptor(headerInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .cache(cache)
            .build()
    }
    @Provides
    @Named(RETROFIT_V1)
    internal fun provideRetrofitV1(@Named(GSON_V1) gson: Gson,
                                   @Named(OKHHTP_CLIENT_V1) okHttpClient: OkHttpClient,
                                   @Named(ENDPOINT_V1) endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(endPoint.url!!)
            .client(okHttpClient)
            .build()
    }
}