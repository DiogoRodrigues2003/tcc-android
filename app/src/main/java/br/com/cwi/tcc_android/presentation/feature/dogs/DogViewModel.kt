package br.com.cwi.tcc_android.presentation.feature.dogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Pet
import br.com.cwi.tcc_android.domain.entity.PetImage
import br.com.cwi.tcc_android.domain.repository.DogRepository
import br.com.cwi.tcc_android.presentation.base.BaseViewModel

class DogViewModel(
    private val repository: DogRepository
) : BaseViewModel() {

    private val _breeds = MutableLiveData<List<Breed>>()
    val breeds: LiveData<List<Breed>> = _breeds

    private val _breedDetails = MutableLiveData<Pet>()
    val breedDetails: LiveData<Pet> = _breedDetails

    private val _dogImage = MutableLiveData<PetImage>()
    val dogImage: LiveData<PetImage> = _dogImage

    fun fetchBreeds() {
        launch {
            val breedList = repository.getDogBreeds(page)
            emptyPage(breedList)
            _breeds.postValue(breedList)
        }
    }

    fun fetchBreedDetails(name: String?) {
        launch {
            val breed = repository.getDogBreedByName(name)
            _breedDetails.postValue(breed)
        }
    }

    fun fetchDogImage(id: Int?) {
        launch {
            val image = repository.getNewDogImage(id)
            _dogImage.postValue(image)
        }
    }

    private fun emptyPage(breedList: List<Breed>) {
        if (breedList.isEmpty() && page > 0) {
            page = 0
            fetchBreeds()
        }
    }
}