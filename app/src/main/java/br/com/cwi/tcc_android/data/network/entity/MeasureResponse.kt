package br.com.cwi.tcc_android.data.network.entity

import com.squareup.moshi.Json

class MeasureResponse (
    @Json(name="metric") val value: String
)
