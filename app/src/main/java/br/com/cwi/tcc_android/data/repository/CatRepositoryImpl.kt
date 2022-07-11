package br.com.cwi.tcc_android.data.repository

import br.com.cwi.tcc_android.data.network.CatApi
import br.com.cwi.tcc_android.data.network.mapper.BreedMapper
import br.com.cwi.tcc_android.data.network.mapper.CatMapper
import br.com.cwi.tcc_android.data.network.mapper.PetImageMapper
import br.com.cwi.tcc_android.data.network.mapper.DogMapper
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Cat
import br.com.cwi.tcc_android.domain.entity.PetImage
import br.com.cwi.tcc_android.domain.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatRepositoryImpl (
    private val api: CatApi,
    private val breedMapper: BreedMapper,
    private val catMapper: CatMapper,
    private val petImageMapper: PetImageMapper
) : CatRepository {

    override suspend fun getCatBreeds(page: Int): List<Breed> {
        val limit = 20
        return withContext(Dispatchers.IO) {
            val breedList = api.getCatBreeds(limit, page).filter { breed ->
                breed.image?.url !== null
            }
            breedMapper.toDomain(breedList)
        }
    }

    override suspend fun getCatBreedByName(name: String?): Cat {
        return withContext(Dispatchers.IO) {
            catMapper.toDomain(api.getCatBreedByName(name)).first()
        }
    }

    override suspend fun getNewCatImage(id: String?): PetImage {
        return withContext(Dispatchers.IO) {
            petImageMapper.toDomain(api.getNewCatImage(id)).first()
        }
    }
}