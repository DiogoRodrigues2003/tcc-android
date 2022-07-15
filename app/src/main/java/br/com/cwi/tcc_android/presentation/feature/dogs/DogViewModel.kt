package br.com.cwi.tcc_android.presentation.feature.dogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Dog
import br.com.cwi.tcc_android.domain.entity.PetImage
import br.com.cwi.tcc_android.domain.repository.DogRepository
import br.com.cwi.tcc_android.presentation.base.BaseViewModel
import javax.security.auth.callback.Callback

class DogViewModel(
    private val repository: DogRepository
) : BaseViewModel() {

    private val _breeds = MutableLiveData<List<Breed>>()
    val breeds: LiveData<List<Breed>> = _breeds

    private val _breedDetails = MutableLiveData<Dog>()
    val breedDetails: LiveData<Dog> = _breedDetails

    private val _dogImage = MutableLiveData<PetImage>()
    val dogImage: LiveData<PetImage> = _dogImage

    fun fetchBreeds() {
        launch {
            var breedList = repository.getDogBreeds(page)
            if (breedList.isEmpty()) {
                page = 0
                breedList = repository.getDogBreeds(page)
                _breeds.postValue(breedList)
            } else {
                _breeds.postValue(breedList)
            }
        }
    }

    fun fetchBreedDetails(name: String?) {
        launch {
            val breed = repository.getDogBreedByName(name)
            _breedDetails.postValue(breed)
        }
    }

    fun fetchDogImage(id: String?) {
        launch {
            val image = repository.getNewDogImage(id)
            _dogImage.postValue(image)
        }
    }
}