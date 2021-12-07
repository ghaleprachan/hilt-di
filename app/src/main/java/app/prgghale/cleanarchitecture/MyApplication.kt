package app.prgghale.cleanarchitecture

import android.app.Application
import android.content.Context
import com.mocklets.pluto.Pluto
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception
import javax.inject.Singleton


/**
 * All app using hilt must have Application class annotated with [HiltAndroidApp]
 *
 * [HiltAndroidApp] triggers hilt's code generation, including a base
 * class for the application that serves as the application-level dependency container
 * */
@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        Pluto.initialize(this)

        instance = this

    }
}