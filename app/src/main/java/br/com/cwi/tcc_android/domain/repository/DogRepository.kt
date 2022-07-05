package br.com.cwi.tcc_android.domain.repository

import br.com.cwi.tcc_android.domain.entity.Breed

interface DogRepository {
    suspend fun getDogBreeds(page: Int): List<Breed>
}