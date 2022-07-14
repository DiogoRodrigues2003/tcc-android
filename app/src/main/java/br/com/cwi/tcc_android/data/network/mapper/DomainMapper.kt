package br.com.cwi.tcc_android.data.network.mapper

import br.com.cwi.tcc_android.presentation.constant.InfoAnswers

interface DomainMapper<in T, out Model> {
    fun toDomain(from: T): Model
    fun toDomain(from: List<T>): List<Model>

    fun nullChecker(value: String?): String {
        if (value === null) return InfoAnswers.UNKNOWN
        return value
    }

    fun checker(value: String?): String {
        if (value == "1") return InfoAnswers.TRUE
        else if (value == "0") return InfoAnswers.FALSE
        return InfoAnswers.UNKNOWN
    }
}