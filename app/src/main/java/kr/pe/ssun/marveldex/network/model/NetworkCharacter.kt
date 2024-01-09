package kr.pe.ssun.marveldex.network.model

import kr.pe.ssun.marveldex.data.model.Character
import com.google.gson.annotations.SerializedName
import java.util.Date

data class NetworkCharacter(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("modified") val modified: Date?,
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