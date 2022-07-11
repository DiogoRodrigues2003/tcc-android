package br.com.cwi.tcc_android.domain.entity

class Cat(
    id: String,
    name: String,
    val lap: String,
    val natural: String,
    lifeSpan: String,
    temperament: String,
    weight: String,
    val hairless: String,
    type: PetType = PetType.CAT
) : Pet(id, name, lifeSpan, temperament, weight, type)
