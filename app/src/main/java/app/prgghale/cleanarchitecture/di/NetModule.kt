package app.prgghale.cleanarchitecture.di

import android.content.Context
import app.prgghale.cleanarchitecture.network.intercepter.NetworkConnectionInterceptor
import app.prgghale.cleanarchitecture.network.apis.MainApi
import com.mocklets.pluto.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {

    @Singleton
    @Provides
    fun getContext(
        @ApplicationContext context: Context
    ): Context {
        return context
    }

    @Singleton
    @Provides
    fun getPlutoInterceptor(): PlutoInterceptor {
        return PlutoInterceptor()
    }

    @Singleton
    @Provides
    fun networkInterceptor(
        @ApplicationContext context: Context
    ): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context = context)
    }

    @Singleton
    @Provides
    fun okHttpClient(
        plutoInterceptor: PlutoInterceptor,
        connectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(connectionInterceptor)
            .addInterceptor(plutoInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.covid19api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getMainApi(
        retrofit: Retrofit
    ): MainApi {
        return retrofit.create(MainApi::class.java)
    }

}

