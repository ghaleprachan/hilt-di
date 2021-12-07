package app.prgghale.cleanarchitecture.utils

/**
 * A generic class that holds a value with its loading status.
 *
 *
 * @param T */
sealed class ResourceStates<out T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = -1
) {
    class Success<T>(data: T) : ResourceStates<T>(data)
    class Loading<T>(data: T? = null) : ResourceStates<T>(data)
    class Error<T>(message: String, code: Int? = -1, errorData: T? = null) :
        ResourceStates<T>(errorData, message, code)

    class None<T> : ResourceStates<T>()
}