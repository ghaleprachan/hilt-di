package app.prgghale.cleanarchitecture.domain


import com.google.gson.annotations.SerializedName

data class SummaryResponse(
    @SerializedName("ID")
    val iD: String?,
    @SerializedName("Message")
    val message: String?,
    @SerializedName("Global")
    val global: Global?,
    @SerializedName("Countries")
    val countries: List<Country?>?,
    @SerializedName("Date")
    val date: String?
)

data class Global(
    @SerializedName("NewConfirmed")
    val newConfirmed: Int?,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int?,
    @SerializedName("NewDeaths")
    val newDeaths: Int?,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int?,
    @SerializedName("NewRecovered")
    val newRecovered: Int?,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int?,
    @SerializedName("Date")
    val date: String?
)

data class Country(
    @SerializedName("ID")
    val iD: String?,
    @SerializedName("Country")
    val country: String?,
    @SerializedName("CountryCode")
    val countryCode: String?,
    @SerializedName("Slug")
    val slug: String?,
    @SerializedName("NewConfirmed")
    val newConfirmed: Int?,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int?,
    @SerializedName("NewDeaths")
    val newDeaths: Int?,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int?,
    @SerializedName("NewRecovered")
    val newRecovered: Int?,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int?,
    @SerializedName("Date")
    val date: String?,
)
