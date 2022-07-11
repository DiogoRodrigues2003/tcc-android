package br.com.cwi.tcc_android.data.network.mapper

import br.com.cwi.tcc_android.data.network.entity.BreedResponse
import br.com.cwi.tcc_android.domain.entity.Breed

class BreedMapper: DomainMapper<BreedResponse, Breed> {

    private val imageMapper: PetImageMapper = PetImageMapper()

    override fun toDomain(from: BreedResponse) = Breed(
        id = from.id,
        name = from.name,
        image = from.image?.let { imageMapper.toDomain(it) }
    )

    override fun toDomain(from: List<BreedResponse>) = from.map {
        toDomain(it)
    }
}