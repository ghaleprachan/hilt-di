package app.prgghale.cleanarchitecture.extensions

import app.prgghale.cleanarchitecture.domain.SummaryResponse
import app.prgghale.cleanarchitecture.utils.ResourceStates
import javax.annotation.concurrent.Immutable


fun <T> ResourceStates<T>.uiController(
    loadingDialog: LoadingDialog,
    onSuccess: (T?) -> Unit = {},
    onError: (String, Int?) -> Unit = { _, _ -> }
) {
    when (this) {
        is ResourceStates.Loading -> {
            loadingDialog.showDialog()
        }
        is ResourceStates.Success -> {
            loadingDialog.hideDialog()
            onSuccess(this.data)
        }
        is ResourceStates.Error -> {
            loadingDialog.hideDialog()
            onError(this.message.orEmpty(), this.code)
        }
        else -> {
        }
    }
}

@Immutable
internal data class MainViewState(
    val summary: SummaryResponse? = null,
    val isRefreshing: Boolean = false
) {
    val refreshing get() = isRefreshing
}