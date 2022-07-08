package br.com.cwi.tcc_android.domain.repository

import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Pet
import br.com.cwi.tcc_android.domain.entity.PetImage

interface DogRepository {
    suspend fun getDogBreeds(page: Int): List<Breed>
    suspend fun getDogBreedByName(name: String?) : Pet
    suspend fun getNewDogImage(id: Int?) : PetImage
}