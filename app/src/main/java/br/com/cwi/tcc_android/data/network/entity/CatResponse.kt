package br.com.cwi.tcc_android.data.network.entity

import com.squareup.moshi.Json

class CatResponse (
    @Json(name="id") val id: String,
    @Json(name="name") val name: String,
    @Json(name="life_span") val lifeSpan: String?,
    @Json(name="temperament") val temperament: String?,
    @Json(name="lap") val lap: String?,
    @Json(name="natural") val natural: String?,
    @Json(name="hairless") val hairless: String?,
    @Json(name="weight") val weight: MeasureResponse
)
