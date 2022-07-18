package br.com.cwi.tcc_android.data.database.mapper

import br.com.cwi.tcc_android.data.database.entity.PetEntity

fun toPetEntity(petName: String, breedName: String?, breedId: String?, petPhotoUrl: String, petType: String?): PetEntity {
    return PetEntity(0, petName, breedName, breedId, petPhotoUrl, petType)
}