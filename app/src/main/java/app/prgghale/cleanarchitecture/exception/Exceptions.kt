package app.prgghale.cleanarchitecture.exception

import app.prgghale.cleanarchitecture.utils.ResourceStates
import com.mocklets.pluto.PlutoLog
import java.io.IOException
import java.lang.Exception

inline fun <T> handleTryCatch(func: () -> ResourceStates<T>): ResourceStates<T> {
    return try {
        func()
    } catch (ex: NoInternetException) {
        ResourceStates.Error(message = ex.message.orEmpty())
    } catch (ex: Exception) {
        ResourceStates.Error(message = ex.message.orEmpty())
    }
}

class NoInternetException(message: String) : IOException(message)