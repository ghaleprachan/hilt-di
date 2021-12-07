package app.prgghale.cleanarchitecture.di

import android.app.Application
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import app.prgghale.cleanarchitecture.MyApplication
import app.prgghale.cleanarchitecture.base.BaseActivity
import app.prgghale.cleanarchitecture.extensions.LoadingDialog
import app.prgghale.cleanarchitecture.extensions.getProgressDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DialogModule {

    /*@Provides
    @Singleton
    fun activity(): Context {
        return MyApplication.instance.applicationContext
    }

    @Provides
    @Singleton
    fun dialogInstance(
        context: Context
    ): Dialog {
        return context.getProgressDialog()
    }*/

    @Provides
    @Singleton
    fun progressDialog(
        dialog: Dialog
    ): LoadingDialog {
        return LoadingDialog(dialog)
    }

}