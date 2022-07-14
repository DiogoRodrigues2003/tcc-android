package br.com.cwi.tcc_android.data.network.mapper

import br.com.cwi.tcc_android.data.network.entity.DogResponse
import br.com.cwi.tcc_android.domain.entity.Dog
import br.com.cwi.tcc_android.presentation.extension.toCentimeters
import br.com.cwi.tcc_android.presentation.extension.toKilos

class DogMapper: DomainMapper<DogResponse, Dog> {

    override fun toDomain(from: DogResponse) = Dog(
        id = from.id,
        name = from.name,
        bredFor = nullChecker(from.bredFor),
        breedGroup =  nullChecker(from.breedGroup),
        lifeSpan =  nullChecker(from.lifeSpan),
        temperament =  nullChecker(from.temperament),
        weight = from.weight.value.toKilos(),
        height = from.height.value.toCentimeters()
    )

    override fun toDomain(from: List<DogResponse>) = from.map {
        toDomain(it)
    }
}