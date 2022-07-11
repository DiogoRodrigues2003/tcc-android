package br.com.cwi.tcc_android.presentation.feature.cats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Cat
import br.com.cwi.tcc_android.domain.entity.Dog
import br.com.cwi.tcc_android.domain.entity.PetImage
import br.com.cwi.tcc_android.domain.repository.CatRepository
import br.com.cwi.tcc_android.presentation.base.BaseViewModel

class CatViewModel (
    private val repository: CatRepository
) : BaseViewModel() {

    private val _breeds = MutableLiveData<List<Breed>>()
    val breeds: LiveData<List<Breed>> = _breeds

    private val _breedDetails = MutableLiveData<Cat>()
    val breedDetails: LiveData<Cat> = _breedDetails

    private val _catImage = MutableLiveData<PetImage>()
    val catImage: LiveData<PetImage> = _catImage

    fun fetchBreeds() {
        launch {
            val breedList = repository.getCatBreeds(page)
            emptyPage(breedList)
            _breeds.postValue(breedList)
        }
    }

    fun fetchBreedDetails(name: String?) {
        launch {
            val breed = repository.getCatBreedByName(name)
            _breedDetails.postValue(breed)
        }
    }

    fun fetchCatImage(id: String?) {
        launch {
            val image = repository.getNewCatImage(id)
            _catImage.postValue(image)
        }
    }

    private fun emptyPage(breedList: List<Breed>) {
        if (breedList.isEmpty() && page > 0) {
            page = 0
            fetchBreeds()
        }
    }
}