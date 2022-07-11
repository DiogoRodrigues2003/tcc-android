package br.com.cwi.tcc_android.domain.entity

abstract class Pet (
    val id: String,
    val name: String,
    val lifeSpan: String,
    val temperament: String,
    val weight: String,
    val type: PetType
)