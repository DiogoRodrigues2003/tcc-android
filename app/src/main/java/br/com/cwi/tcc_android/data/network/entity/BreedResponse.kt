package br.com.cwi.tcc_android.data.network.entity

import com.squareup.moshi.Json

class BreedResponse(
    @Json(name="id") val id: Int,
    @Json(name="name") val name: String,
    @Json(name="image") val image: PetImageResponse
)