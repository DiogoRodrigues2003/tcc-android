package br.com.cwi.tcc_android.data.network.mapper

import br.com.cwi.tcc_android.data.network.entity.PetResponse
import br.com.cwi.tcc_android.domain.entity.Pet

class PetMapper: DomainMapper<PetResponse, Pet> {

    override fun toDomain(from: PetResponse) = Pet(
        id = from.id,
        name = from.name,
        bredFor = nullChecker(from.bredFor),
        breedGroup =  nullChecker(from.breedGroup),
        lifeSpan =  nullChecker(from.lifeSpan),
        temperament =  nullChecker(from.temperament),
        weight = from.weight.value + " kg",
        height = from.height.value + " cm"
    )

    override fun toDomain(from: List<PetResponse>) = from.map {
        toDomain(it)
    }

    private fun nullChecker(value: String?): String {
        if (value === null) return "Unknown"
        return value
    }
}