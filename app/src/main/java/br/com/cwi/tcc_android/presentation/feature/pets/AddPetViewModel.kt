package br.com.cwi.tcc_android.presentation.feature.pets

import br.com.cwi.tcc_android.data.database.mapper.toPetEntity
import br.com.cwi.tcc_android.domain.repository.PetLocalRepository
import br.com.cwi.tcc_android.presentation.base.BaseViewModel

class AddPetViewModel (
    private val petLocalRepository: PetLocalRepository
): BaseViewModel() {

    fun setPet(petName: String, breedName: String?, breedId: String?) {
        petLocalRepository.add(toPetEntity(petName, breedName, breedId))
    }

}