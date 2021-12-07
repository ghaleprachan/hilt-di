package app.prgghale.cleanarchitecture.network.repository

import app.prgghale.cleanarchitecture.domain.SummaryResponse
import app.prgghale.cleanarchitecture.exception.handleTryCatch
import app.prgghale.cleanarchitecture.network.apis.MainApi
import app.prgghale.cleanarchitecture.utils.ResourceStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

interface MainRepository {
    suspend fun getUserName(): String

    suspend fun getSummary(): ResourceStates<SummaryResponse>

    suspend fun getSummaryTwo(): ResourceStates<SummaryResponse?>
}

class MainRepositoryImpl @Inject constructor(
    private val test: String,
    private val mainApi: MainApi
) : MainRepository {
    override suspend fun getUserName(): String {
        return test
    }

    override suspend fun getSummary(): ResourceStates<SummaryResponse> {
        return withContext(Dispatchers.Default) {
            handleTryCatch {
                mainApi.getSummary().handleResponse()
            }
        }
    }

    override suspend fun getSummaryTwo(): ResourceStates<SummaryResponse?> {
        return withContext(Dispatchers.Default) {
            handleTryCatch {
                mainApi.getSummaryTwo().handleResponse()
            }
        }
    }
}


/**
 * THis is to handle API response of any type*/
suspend fun <T> Response<T>.handleResponse(onSuccess: suspend (body: T?) -> Unit = {}): ResourceStates<T> {
    return if (isSuccessful) {
        if (body() != null) {
            onSuccess(body())
            ResourceStates.Success(data = body()!!)
        } else {
            ResourceStates.Error(message = message(), code = code())
        }
    } else {
        ResourceStates.Error(message = message(), code = code())
    }
}