package kr.pe.ssun.marveldex.network.model

import com.google.gson.annotations.SerializedName

data class NetworkImage(
    @SerializedName("path") val path: String?,
    @SerializedName("extension") val extension: String?,
)
