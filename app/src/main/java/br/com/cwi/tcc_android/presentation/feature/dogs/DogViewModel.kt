package br.com.cwi.tcc_android.presentation.feature.dogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.repository.DogRepository
import br.com.cwi.tcc_android.presentation.base.BaseViewModel

class DogViewModel(
    private val repository: DogRepository
) : BaseViewModel() {

    private val _breeds = MutableLiveData<List<Breed>>()
    val breeds: LiveData<List<Breed>> = _breeds

    fun fetchBreeds() {
        launch {
            val breedList = repository.getDogBreeds(page)
            emptyPage(breedList)
            _breeds.postValue(breedList)
        }
    }

    private fun emptyPage(breedList: List<Breed>) {
        if (breedList.isEmpty() && page > 0) {
            page = 0
            fetchBreeds()
        }
    }
}