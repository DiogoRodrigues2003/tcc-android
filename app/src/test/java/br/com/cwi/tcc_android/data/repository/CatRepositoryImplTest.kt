package br.com.cwi.tcc_android.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cwi.tcc_android.data.network.CatApi
import br.com.cwi.tcc_android.data.network.entity.*
import br.com.cwi.tcc_android.data.network.mapper.BreedMapper
import br.com.cwi.tcc_android.data.network.mapper.CatMapper
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

class CatRepositoryImplTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var repository: CatRepositoryImpl

    private lateinit var breedMapper: BreedMapper
    private lateinit var catMapper: CatMapper
    private lateinit var petImageMapper: PetImageMapper

    private val api: CatApi = mockk()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = CatRepositoryImpl(
            api,
            breedMapper = BreedMapper(),
            catMapper = CatMapper(),
            petImageMapper = PetImageMapper()
        )
    }

    @Test
    fun whenGetCatBreeds_thenReturnBreedsSuccessfully() {
        runBlocking {
            //Arrange
            val page = 0
            val breedResponseList = listOf(
                BreedResponse(
                    "1",
                    "Aegean",
                    PetImageResponse("1", "url")
                ),
                BreedResponse(
                    "2",
                    "American Curl",
                    null
                )
            )
            val breedList = listOf(
                Breed(
                    "1",
                    "Aegean",
                    PetImage("1", "url")
                )
            )

            coEvery { api.getCatBreeds(any(), page) } returns breedResponseList

            //Act
            val breedsResult = repository.getCatBreeds(page)

            //Assert
            assert(breedsResult.first().name == breedList.first().name)
            assert(breedList.size == 1)
        }
    }

    @Test
    fun whenGetCatBreedByName_thenReturnCatSuccessfully() {
        runBlocking {
            //Arrange
            val catName = "Aegean"
            val measure = MeasureResponse("measure")
            val catResponseList = listOf(
                CatResponse(
                    "1",
                    "Aegean",
                    "9 - 12",
                    "Social, Intelligent",
                    "Unknown",
                    "No.",
                    "No.",
                    measure
                )
            )

            coEvery { api.getCatBreedByName(catName) } returns catResponseList

            //Act
            val catResult = repository.getCatBreedByName(catName)

            //Assert
            assert(catResult.name == catName)
        }
    }

    @Test
    fun whenGetNewCatImage_thenReturnNewImageSuccessfully() {
        runBlocking {
            //Arrange
            val id = "1"
            val petImageResponseList = listOf(PetImageResponse("1", "url"))

            coEvery { api.getNewCatImage(id) } returns petImageResponseList

            //Act
            val imageResult = repository.getNewCatImage(id)

            //Assert
            assert(imageResult.id == id)
        }
    }
}