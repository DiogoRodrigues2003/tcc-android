package br.com.cwi.tcc_android.data.network.mapper

import br.com.cwi.tcc_android.data.network.entity.CatResponse
import br.com.cwi.tcc_android.domain.entity.Cat
import br.com.cwi.tcc_android.presentation.constant.InfoAnswers
import br.com.cwi.tcc_android.presentation.extension.toKilos
import br.com.cwi.tcc_android.presentation.extension.toYears

class CatMapper : DomainMapper<CatResponse, Cat> {

    override fun toDomain(from: CatResponse) = Cat(
        id = from.id,
        name = from.name,
        lap = checker(from.lap),
        natural =  checker(from.natural),
        lifeSpan =  from.lifeSpan.toYears(),
        temperament =  nullChecker(from.temperament),
        weight = from.weight.value.toKilos(),
        hairless = checker(from.hairless)
    )

    override fun toDomain(from: List<CatResponse>) = from.map {
        toDomain(it)
    }
}