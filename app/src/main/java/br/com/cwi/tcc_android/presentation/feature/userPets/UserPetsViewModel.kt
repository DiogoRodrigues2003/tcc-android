package br.com.cwi.tcc_android.presentation.feature.userPets

import br.com.cwi.tcc_android.domain.repository.PetLocalRepository
import br.com.cwi.tcc_android.presentation.base.BaseViewModel

class UserPetsViewModel (
    private val petLocalRepository: PetLocalRepository
): BaseViewModel() {
}