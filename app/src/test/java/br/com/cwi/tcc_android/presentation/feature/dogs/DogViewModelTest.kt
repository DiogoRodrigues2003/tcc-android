package br.com.cwi.tcc_android.presentation.feature.dogs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cwi.tcc_android.domain.entity.*
import br.com.cwi.tcc_android.domain.repository.DogRepository
import br.com.cwi.tcc_android.extensions.test
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class DogViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DogViewModel

    private val repository: DogRepository = mockk()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = DogViewModel(repository)
    }

    @Test
    fun whenFetchBreeds_thenPostValueSuccessfully() {
        // Arrange
        val breedObserver = viewModel.breeds.test()
        val breedList = listOf(
            Breed(
                "1",
                "Yorkshire Terrier",
                PetImage("1", "url")
            )
        )

        coEvery { repository.getDogBreeds(any()) } returns breedList

        // Act
        viewModel.fetchBreeds()

        // Assert
        verify { breedObserver.onChanged(any()) }
    }

    @Test
    fun whenFetchBreedsButListIsEmpty_thenMakePageZeroAndPostValueSuccessfullyAf() {
        // Arrange
        viewModel.page = 1
        val breedObserver = viewModel.breeds.test()
        val breedListEmpty: List<Breed> = emptyList()
        val breedList = listOf(
            Breed(
                "1",
                "Yorkshire Terrier",
                PetImage("1", "url")
            )
        )

        coEvery { repository.getDogBreeds(viewModel.page) } returns breedListEmpty
        coEvery { repository.getDogBreeds(0) } returns breedList

        // Act
        viewModel.fetchBreeds()

        // Assert
        assert(viewModel.page == 0)
        verify { breedObserver.onChanged(any()) }
    }

    @Test
    fun whenFetchBreedDetails_thenPostValueSuccessfully() {
        // Arrange
        val breedDetailsObserver = viewModel.breedDetails.test()
        val dogBreed = Dog(
            "1",
            "Yorkshire Terrier",
            "Small vermin hunting",
            "Toy",
            "12 - 16 years",
            "Bold, Independent",
            "2 - 3 kg",
            "20 - 23 cm"
        )
        val name = dogBreed.name

        coEvery { repository.getDogBreedByName(name) } returns dogBreed

        // Act
        viewModel.fetchBreedDetails(name)

        // Assert
        verify { breedDetailsObserver.onChanged(any()) }
    }

    @Test
    fun whenFetchCatImage_thenPostValueSuccessfully() {
        // Arrange
        val dogImageObserver = viewModel.dogImage.test()
        val dogImage = PetImage("1", "url")
        val id = "1"

        coEvery { repository.getNewDogImage(id) } returns dogImage

        // Act
        viewModel.fetchDogImage(id)

        // Assert
        verify { dogImageObserver.onChanged(any()) }
    }
}