package br.com.cwi.tcc_android.data.network.mapper

import br.com.cwi.tcc_android.data.network.entity.PetImageResponse
import br.com.cwi.tcc_android.domain.entity.PetImage

class PetImageMapper: DomainMapper<PetImageResponse, PetImage> {

    override fun toDomain(from: PetImageResponse) = PetImage(
        id = from.id,
        url = from.url
    )

    override fun toDomain(from: List<PetImageResponse>) = from.map {
        toDomain(it)
    }
}