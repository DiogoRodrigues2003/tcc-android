package br.com.cwi.tcc_android.presentation.feature.userPets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.tcc_android.data.database.entity.PetEntity
import br.com.cwi.tcc_android.domain.repository.PetLocalRepository
import br.com.cwi.tcc_android.presentation.base.BaseViewModel

class UserPetsViewModel (
    private val petLocalRepository: PetLocalRepository
): BaseViewModel() {

    private val _pets = MutableLiveData<List<PetEntity>>()
    val pets: LiveData<List<PetEntity>> = _pets

    fun fetchPets() {
        launch {
            val petList = petLocalRepository.getAll()
            _pets.postValue(petListSetup(petList))
        }
    }

    fun deletePet(pet: PetEntity) {
        petLocalRepository.remove(pet)
        fetchPets()
    }

    fun petListSetup(petList: List<PetEntity>?): List<PetEntity> {
        val preparedPetList = mutableListOf<PetEntity>()

        petList?.forEach {
            preparedPetList.add(it)
        }

        return preparedPetList
    }
}