package br.com.cwi.tcc_android.data.repository

import br.com.cwi.tcc_android.data.database.AppDatabase
import br.com.cwi.tcc_android.data.database.entity.PetEntity
import br.com.cwi.tcc_android.domain.repository.PetLocalRepository

class PetLocalRepositoryImpl (
    appDatabase: AppDatabase
) : PetLocalRepository {

    private val dao = appDatabase.getPetDao()

    override fun add(petEntity: PetEntity) {
        dao.add(petEntity)
    }

    override fun remove(petEntity: PetEntity) {
        dao.remove(petEntity)
    }

    override fun getAll(): List<PetEntity>? = dao.getAll()
}