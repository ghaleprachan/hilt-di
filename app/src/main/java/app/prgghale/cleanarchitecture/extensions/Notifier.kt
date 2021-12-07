package app.prgghale.cleanarchitecture.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String?, action: (() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, message.orEmpty(), Snackbar.LENGTH_LONG)
    action?.let {
        snackBar.setAction("Retry") { it() }
    }
    snackBar.show()
}
