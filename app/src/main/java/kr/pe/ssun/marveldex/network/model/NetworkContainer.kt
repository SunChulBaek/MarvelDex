package kr.pe.ssun.marveldex.network.model

import com.google.gson.annotations.SerializedName

data class NetworkContainer<T>(
    @SerializedName("offset") val offset: Int?,
    @SerializedName("limit") val limit: Int?,
    @SerializedName("total") val total: Int?,
    @SerializedName("count") val count: Int?,
    @SerializedName("results") val results: List<T>?,
)
