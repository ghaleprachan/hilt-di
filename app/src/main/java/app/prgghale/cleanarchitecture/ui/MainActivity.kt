package app.prgghale.cleanarchitecture.ui

import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import app.prgghale.cleanarchitecture.R
import app.prgghale.cleanarchitecture.base.BaseActivity
import app.prgghale.cleanarchitecture.databinding.ActivityMainBinding
import app.prgghale.cleanarchitecture.extensions.LoadingDialog
import app.prgghale.cleanarchitecture.extensions.showSnackBar
import app.prgghale.cleanarchitecture.extensions.uiController
import app.prgghale.cleanarchitecture.utils.ResourceStates
import com.google.android.material.button.MaterialButton
import com.mocklets.pluto.PlutoLog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import org.w3c.dom.Text
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        viewModel.getSummary()
        loginUiStateObserver()
    }

    private fun loginUiStateObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.summary.collect {
                it.uiController(
                    loadingDialog = dialogInstance,
                    onSuccess = { data ->
                        binding.tvOne.text =
                            getString(
                                R.string.total_cases,
                                data?.global?.totalConfirmed.toString()
                            )
                    },
                    onError = { message, _ ->
                        binding.tvOne.text = message
                        binding.root.showSnackBar(message) {
                            viewModel.getSummary()
                        }
                    }
                )

                /*binding.progress.isVisible = it is ResourceStates.Loading
                when (it) {
                    is ResourceStates.Success -> {
                        val responseData = it.data

                        binding.tvOne.text =
                            getString(
                                R.string.total_cases,
                                responseData?.global?.totalConfirmed.toString()
                            )
                    }
                    is ResourceStates.Error -> {
                        binding.tvOne.text = it.message.orEmpty()
                        binding.root.showSnackBar(it.message, action = {
                            viewModel.getSummary()
                        })
                    }
                    else -> {
                    }
                }*/
            }
        }
    }
}

