package kr.pe.ssun.marveldex.network.model

import com.google.gson.annotations.SerializedName

data class NetworkWrapper<T>(
    @SerializedName("code") val code: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("data") val data: NetworkContainer<T>?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("copyright") val copyright: String?,
    @SerializedName("attributionText") val attrText: String?,
    @SerializedName("attributionHTML") val attrHTML: String?,
)
