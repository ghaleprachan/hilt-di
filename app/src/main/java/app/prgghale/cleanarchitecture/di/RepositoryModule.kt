package app.prgghale.cleanarchitecture.di

import app.prgghale.cleanarchitecture.network.apis.MainApi
import app.prgghale.cleanarchitecture.network.repository.MainRepository
import app.prgghale.cleanarchitecture.network.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * We did @InstallIn(ActivityComponent::class) because you want to inject
 * dependency into your Activities
 * This annotation means that all of the dependencies
 * in AnalyticsModule are available in all of the app's activities.*/
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun bindGetUsername(
        mainApi: MainApi
    ): MainRepository {
        return MainRepositoryImpl("This is from hilt injection", mainApi)
    }

}
