package app.prgghale.cleanarchitecture.base

import android.app.Dialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import app.prgghale.cleanarchitecture.extensions.LoadingDialog
import app.prgghale.cleanarchitecture.extensions.getProgressDialog
import app.prgghale.cleanarchitecture.network.apis.MainApi
import app.prgghale.cleanarchitecture.ui.MainViewModel
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>()

    private val progressDialog: Dialog by lazy {
        this.getProgressDialog()
    }

    val dialogInstance: LoadingDialog by lazy {
        LoadingDialog(progressDialog)
    }

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainApi = retrofit.create(MainApi::class.java)
    }
}