package br.com.cwi.tcc_android.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cwi.tcc_android.data.network.DogApi
import br.com.cwi.tcc_android.data.network.entity.BreedResponse
import br.com.cwi.tcc_android.data.network.entity.DogResponse
import br.com.cwi.tcc_android.data.network.entity.MeasureResponse
import br.com.cwi.tcc_android.data.network.entity.PetImageResponse
import br.com.cwi.tcc_android.data.network.mapper.BreedMapper
import br.com.cwi.tcc_android.data.network.mapper.DogMapper
import br.com.cwi.tcc_android.data.network.mapper.PetImageMapper
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.PetImage
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogRepositoryImplTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var repository: DogRepositoryImpl

    private lateinit var breedMapper: BreedMapper
    private lateinit var dogMapper: DogMapper
    private lateinit var petImageMapper: PetImageMapper

    private val api: DogApi = mockk()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = DogRepositoryImpl(
            api,
            breedMapper = BreedMapper(),
            dogMapper = DogMapper(),
            petImageMapper = PetImageMapper()
        )
    }

    @Test
    fun whenGetDogBreeds_thenReturnBreedsSuccessfully() {
        runBlocking {
            //Arrange
            val page = 0
            val breedResponseList = listOf(
                BreedResponse(
                    "1",
                    "Yorkshire Terrier",
                    PetImageResponse("1", "url")
                )
            )
            val breedList = listOf(
                Breed(
                    "1",
                    "Yorkshire Terrier",
                    PetImage("1", "url")
                )
            )

            coEvery { api.getDogBreeds(any(), page) } returns breedResponseList

            //Act
            val breedsResult = repository.getDogBreeds(page)

            //Assert
            assert(breedsResult.first().name == breedList.first().name)
        }
    }

    @Test
    fun whenGetDogBreedByName_thenReturnDogSuccessfully() {
        runBlocking {
            //Arrange
            val dogName = "Yorkshire Terrier"
            val measure = MeasureResponse("measure")
            val dogResponseList = listOf(
                DogResponse(
                    "1",
                    "Yorkshire Terrier",
                    "Small vermin hunting",
                    "Toy",
                    "12 - 16 years",
                    "Bold, Independent",
                    measure,
                    measure
                )
            )

            coEvery { api.getDogBreedByName(dogName) } returns dogResponseList

            //Act
            val dogResult = repository.getDogBreedByName(dogName)

            //Assert
            assert(dogResult.name == dogName)
        }
    }

    @Test
    fun whenGetNewDogImage_thenReturnNewImageSuccessfully() {
        runBlocking {
            //Arrange
            val id = "1"
            val petImageResponseList = listOf(PetImageResponse("1", "url"))

            coEvery { api.getNewDogImage(id) } returns petImageResponseList

            //Act
            val imageResult = repository.getNewDogImage(id)

            //Assert
            assert(imageResult.id == id)
        }
    }
}