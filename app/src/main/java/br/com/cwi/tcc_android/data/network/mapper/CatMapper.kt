package br.com.cwi.tcc_android.data.network.mapper

import br.com.cwi.tcc_android.data.network.entity.CatResponse
import br.com.cwi.tcc_android.domain.entity.Cat

class CatMapper : DomainMapper<CatResponse, Cat> {

    override fun toDomain(from: CatResponse) = Cat(
        id = from.id,
        name = from.name,
        lap = checker(from.lap),
        natural =  checker(from.natural),
        lifeSpan =  from.lifeSpan + " years",
        temperament =  nullChecker(from.temperament),
        weight = from.weight.value + " kg",
        hairless = checker(from.hairless)
    )

    override fun toDomain(from: List<CatResponse>) = from.map {
        toDomain(it)
    }

    private fun checker(value: String?): String {
        if (value == "1") return "Yes."
        else if (value == "0") return "No."
        return "Unknown"
    }
}