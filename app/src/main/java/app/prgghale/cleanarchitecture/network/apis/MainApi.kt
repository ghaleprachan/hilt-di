package app.prgghale.cleanarchitecture.network.apis

import app.prgghale.cleanarchitecture.domain.SummaryResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface MainApi {
    companion object {
        private const val GetSummary = "summary"
    }

    @GET(GetSummary)
    suspend fun getSummary(): Response<SummaryResponse>

    @GET(GetSummary)
    suspend fun getSummaryTwo(): Response<SummaryResponse>
}