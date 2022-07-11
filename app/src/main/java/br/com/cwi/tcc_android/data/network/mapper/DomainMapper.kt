package br.com.cwi.tcc_android.data.network.mapper

interface DomainMapper<in T, out Model> {
    fun toDomain(from: T): Model
    fun toDomain(from: List<T>): List<Model>

    fun nullChecker(value: String?): String {
        if (value === null) return "Unknown"
        return value
    }
}