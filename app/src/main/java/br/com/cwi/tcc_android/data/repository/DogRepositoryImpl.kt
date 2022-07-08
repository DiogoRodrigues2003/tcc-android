package br.com.cwi.tcc_android.data.repository

import br.com.cwi.tcc_android.data.network.DogApi
import br.com.cwi.tcc_android.data.network.mapper.BreedMapper
import br.com.cwi.tcc_android.data.network.mapper.PetImageMapper
import br.com.cwi.tcc_android.data.network.mapper.PetMapper
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Pet
import br.com.cwi.tcc_android.domain.entity.PetImage
import br.com.cwi.tcc_android.domain.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepositoryImpl (
    private val api: DogApi,
    private val breedMapper: BreedMapper,
    private val petMapper: PetMapper,
    private val petImageMapper: PetImageMapper
) : DogRepository {

    override suspend fun getDogBreeds(page: Int): List<Breed> {
        val limit = 20
        return withContext(Dispatchers.IO) {
            breedMapper.toDomain(api.getDogBreeds(limit, page))
        }
    }

    override suspend fun getDogBreedByName(name: String?): Pet {
        return withContext(Dispatchers.IO) {
            petMapper.toDomain(api.getDogBreedByName(name)).first()
        }
    }

    override suspend fun getNewDogImage(id: Int?): PetImage {
        return withContext(Dispatchers.IO) {
            petImageMapper.toDomain(api.getNewDogImage(id)).first()
        }
    }
}