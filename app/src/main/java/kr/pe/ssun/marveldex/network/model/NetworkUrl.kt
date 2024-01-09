package kr.pe.ssun.marveldex.network.model

import com.google.gson.annotations.SerializedName

data class NetworkUrl(
    @SerializedName("type") val type: String?,
    @SerializedName("url") val url: String?,
)
