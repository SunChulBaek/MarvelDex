package kr.pe.ssun.marveldex.network.model

import kr.pe.ssun.marveldex.data.model.Character
import com.google.gson.annotations.SerializedName
import java.util.Date

data class NetworkCharacter(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    // TODO : Date 타입으로 파싱 안되는 경우가 있어 일단 문자열로 받음 (ex. "-0001-11-30T00:00:00-0500")
    @SerializedName("modified") val modified: String?,
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("urls") val urls: List<NetworkUrl>?,
    @SerializedName("thumbnail") val thumbnail: NetworkImage?,
    @SerializedName("comics") val comics: NetworkList?,
    @SerializedName("stories") val stories: NetworkList?,
    @SerializedName("events") val events: NetworkList?,
    @SerializedName("series") val series: NetworkList?,
)

fun NetworkCharacter.asExternalModel() = Character(
    id = id,
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}",
    name = name,
    description = description,
)