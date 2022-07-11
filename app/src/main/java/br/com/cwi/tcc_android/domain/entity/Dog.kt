package br.com.cwi.tcc_android.domain.entity

class Dog(
    id: String,
    name: String,
    val bredFor: String,
    val breedGroup: String,
    lifeSpan: String,
    temperament: String,
    weight: String,
    val height: String,
    type: PetType = PetType.DOG
) : Pet(id, name, lifeSpan, temperament, weight, type)
