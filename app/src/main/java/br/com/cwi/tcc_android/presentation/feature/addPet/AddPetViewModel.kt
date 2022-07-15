package br.com.cwi.tcc_android.presentation.feature.addPet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.tcc_android.data.database.mapper.toPetEntity
import br.com.cwi.tcc_android.domain.repository.PetLocalRepository
import br.com.cwi.tcc_android.presentation.base.BaseViewModel

class AddPetViewModel (
    private val petLocalRepository: PetLocalRepository
): BaseViewModel() {

    private val _submit = MutableLiveData<Unit>()
    val submit: LiveData<Unit> = _submit

    lateinit var photoUrl: String

    fun setPet(petName: String, breedName: String?, breedId: String?, petType: String?) {
        if (petName.isNotEmpty() && photoUrl.isNotEmpty()) {
            petLocalRepository.add(toPetEntity(petName, breedName, breedId, photoUrl, petType))
            _submit.postValue(Unit)
        }
    }
}