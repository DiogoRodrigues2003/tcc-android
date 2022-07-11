package br.com.cwi.tcc_android.data.database.mapper

import br.com.cwi.tcc_android.data.database.entity.PetEntity
import br.com.cwi.tcc_android.domain.entity.Pet

fun Pet.toEntity(breed: String) = PetEntity (
    id, name, breed, null
)