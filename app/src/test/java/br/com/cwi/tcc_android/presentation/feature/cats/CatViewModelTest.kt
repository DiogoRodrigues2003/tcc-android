package br.com.cwi.tcc_android.presentation.feature.cats

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Cat
import br.com.cwi.tcc_android.domain.entity.PetImage
import br.com.cwi.tcc_android.domain.entity.PetType
import br.com.cwi.tcc_android.domain.repository.CatRepository
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
class CatViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: CatViewModel

    private val repository: CatRepository = mockk()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = CatViewModel(repository)
    }

    @Test
    fun whenFetchBreeds_thenPostValueSuccessfully() {
        // Arrange
        val breedObserver = viewModel.breeds.test()
        val breedList = listOf(
            Breed(
                "1",
                "Aegean",
                PetImage("1", "url")
            )
        )

        coEvery { repository.getCatBreeds(any()) } returns breedList

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
                "Aegean",
                PetImage("1", "url")
            )
        )

        coEvery { repository.getCatBreeds(viewModel.page) } returns breedListEmpty
        coEvery { repository.getCatBreeds(0) } returns breedList

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
        val catBreed = Cat(
            "1",
            "Aegean",
            "Unknown",
            "No.",
            "9 - 12 years",
            "Social, Intelligent",
            "3 - 5 kg",
            "No."
        )
        val name = catBreed.name

        coEvery { repository.getCatBreedByName(name) } returns catBreed

        // Act
        viewModel.fetchBreedDetails(name)

        // Assert
        verify { breedDetailsObserver.onChanged(any()) }
    }

    @Test
    fun whenFetchCatImage_thenPostValueSuccessfully() {
        // Arrange
        val catImageObserver = viewModel.catImage.test()
        val catImage = PetImage("1", "url")
        val id = "1"

        coEvery { repository.getNewCatImage(id) } returns catImage

        // Act
        viewModel.fetchCatImage(id)

        // Assert
        verify { catImageObserver.onChanged(any()) }
    }
}