package br.com.cwi.tcc_android.data.repository

import br.com.cwi.tcc_android.data.network.DogApi
import br.com.cwi.tcc_android.data.network.mapper.BreedMapper
import br.com.cwi.tcc_android.data.network.mapper.PetImageMapper
import br.com.cwi.tcc_android.data.network.mapper.DogMapper
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Dog
import br.com.cwi.tcc_android.domain.entity.PetImage
import br.com.cwi.tcc_android.domain.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepositoryImpl (
    private val api: DogApi,
    private val breedMapper: BreedMapper,
    private val dogMapper: DogMapper,
    private val petImageMapper: PetImageMapper
) : DogRepository {

    override suspend fun getDogBreeds(page: Int): List<Breed> {
        val limit = 20
        return withContext(Dispatchers.IO) {
            breedMapper.toDomain(api.getDogBreeds(limit, page))
        }
    }

    override suspend fun getDogBreedByName(name: String?): Dog {
        return withContext(Dispatchers.IO) {
            dogMapper.toDomain(api.getDogBreedByName(name)).first()
        }
    }

    override suspend fun getNewDogImage(id: String?): PetImage {
        return withContext(Dispatchers.IO) {
            petImageMapper.toDomain(api.getNewDogImage(id)).first()
        }
    }
}