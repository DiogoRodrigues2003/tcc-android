package br.com.cwi.tcc_android.data.network.entity

import com.squareup.moshi.Json

class PetImageResponse(
    @Json(name="id") val id: String,
    @Json(name="url") val url: String
)
