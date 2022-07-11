package br.com.cwi.tcc_android.domain.repository

import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Cat
import br.com.cwi.tcc_android.domain.entity.Dog
import br.com.cwi.tcc_android.domain.entity.PetImage

interface CatRepository {
    suspend fun getCatBreeds(page: Int): List<Breed>
    suspend fun getCatBreedByName(name: String?) : Cat
    suspend fun getNewCatImage(id: String?) : PetImage
}