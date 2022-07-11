package br.com.cwi.tcc_android.domain.repository

import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Dog
import br.com.cwi.tcc_android.domain.entity.PetImage

interface DogRepository {
    suspend fun getDogBreeds(page: Int): List<Breed>
    suspend fun getDogBreedByName(name: String?) : Dog
    suspend fun getNewDogImage(id: String?) : PetImage
}