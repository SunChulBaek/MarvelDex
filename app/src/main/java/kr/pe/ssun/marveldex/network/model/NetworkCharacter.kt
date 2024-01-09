package kr.pe.ssun.marveldex.network.model

import kr.pe.ssun.marveldex.data.model.Character
import com.google.gson.annotations.SerializedName

data class NetworkCharacter(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("thumbnail") val thumbnail: NetworkImage?,
)

fun NetworkCharacter.asExternalModel() = Character(
    id = id,
    name = name,
    thumbnail = "${thumbnail?.path}.${thumbnail?.extension}"
)