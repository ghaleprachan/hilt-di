package app.prgghale.cleanarchitecture.extensions

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.view.isGone
import app.prgghale.cleanarchitecture.R
import app.prgghale.cleanarchitecture.databinding.LayoutProgressDialogBinding
import javax.inject.Inject


fun Context.getProgressDialog(): Dialog {
    return Dialog(this).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
    }
}

fun Dialog.showProgress(@StringRes message: Int = R.string.loading) {
    val loadingMessage = context.getString(message)
    if (isShowing) {
        findViewById<TextView>(R.id.tvProgressMessage)?.apply {
            text = loadingMessage
            isGone = loadingMessage.isEmpty()
        }
    } else {
        val binding = LayoutProgressDialogBinding.inflate(LayoutInflater.from(context), null, false)
        setContentView(binding.root)
        binding.tvProgressMessage.apply {
            text = loadingMessage
            isGone = loadingMessage.isEmpty()
        }
        show()
    }
}

fun Dialog.dismissProgress() {
    if (isShowing) {
        dismiss()
    }
}

class LoadingDialog(
    private val dialog: Dialog
) {
    fun showDialog() {
        dialog.showProgress()
    }

    fun hideDialog() {
        dialog.dismissProgress()
    }
}