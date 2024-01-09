package kr.pe.ssun.marveldex.network.model

import com.google.gson.annotations.SerializedName

data class NetworkList(
    @SerializedName("available") val available: Int?,
    @SerializedName("returned") val returned: Int?,
    @SerializedName("collectionURI") val collectionURI: String?,
    @SerializedName("items") val items: List<NetworkSummary>?,
)
