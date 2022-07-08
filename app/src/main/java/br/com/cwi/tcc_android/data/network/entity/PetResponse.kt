package br.com.cwi.tcc_android.data.network.entity

import com.squareup.moshi.Json

class PetResponse (
    @Json(name="id") val id: Int,
    @Json(name="name") val name: String,
    @Json(name="bred_for") val bredFor: String?,
    @Json(name="breed_group") val breedGroup: String?,
    @Json(name="life_span") val lifeSpan: String?,
    @Json(name="temperament") val temperament: String?,
    @Json(name="weight") val weight: MeasureResponse,
    @Json(name="height") val height: MeasureResponse
)
