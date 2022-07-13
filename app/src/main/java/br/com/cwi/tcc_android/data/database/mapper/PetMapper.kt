package br.com.cwi.tcc_android.data.database.mapper

import br.com.cwi.tcc_android.data.database.entity.PetEntity
import br.com.cwi.tcc_android.domain.entity.Pet

fun toPetEntity(petName: String, breedName: String?, breedId: String?, petPhotoUrl: String): PetEntity {
    return PetEntity(0, petName, breedName, breedId, petPhotoUrl)
}