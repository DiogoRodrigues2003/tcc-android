package br.com.cwi.tcc_android.data.repository

import br.com.cwi.tcc_android.data.network.DogApi
import br.com.cwi.tcc_android.data.network.mapper.BreedMapper
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepositoryImpl (
    private val api: DogApi,
    private val breedMapper: BreedMapper
) : DogRepository {

    override suspend fun getDogBreeds(page: Int): List<Breed> {
        val limit = 20
        return withContext(Dispatchers.IO) {
            breedMapper.toDomain(api.getDogBreeds(limit, page))
        }
    }
}