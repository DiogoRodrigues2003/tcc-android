package br.com.cwi.tcc_android.domain.repository

import br.com.cwi.tcc_android.data.database.entity.PetEntity

interface PetLocalRepository {
    fun add(petEntity: PetEntity)
    fun remove(petEntity: PetEntity)
    fun getAll(): List<PetEntity>?
}