package br.com.cwi.tcc_android.presentation.feature.userPets

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cwi.tcc_android.data.database.entity.PetEntity
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Pet
import br.com.cwi.tcc_android.domain.entity.PetImage
import br.com.cwi.tcc_android.domain.repository.PetLocalRepository
import br.com.cwi.tcc_android.extensions.test
import br.com.cwi.tcc_android.presentation.feature.addPet.AddPetViewModel
import io.mockk.coEvery
import io.mockk.every
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
class UserPetsViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: UserPetsViewModel

    private val petLocalRepository: PetLocalRepository = mockk()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = UserPetsViewModel(petLocalRepository)
    }

    @Test
    fun whenFetchPets_thenPostValueSuccessfully() {
        // Arrange
        val petObserver = viewModel.pets.test()

        coEvery { petLocalRepository.getAll() } returns emptyList()

        // Act
        viewModel.fetchPets()

        // Assert
        verify { petObserver.onChanged(any()) }
    }

    @Test
    fun whenDeletePet_thenDeleteSuccessfullyAndFetchPets() {
        // Arrange
        val pet = PetEntity(
            1,
            "Nikita",
            "Yorkshire Terrier",
            "1",
            "url",
            "DOG"
        )

        every { petLocalRepository.remove(pet) } returns Unit
        coEvery { viewModel.fetchPets() } returns Unit

        // Act
        viewModel.deletePet(pet)

        // Assert
        verify { petLocalRepository.remove(pet) }
        verify { viewModel.fetchPets() }
    }

    @Test
    fun whenNonNullPetListSetup_thenReturnedListIsTheSame() {
        // Arrange
        val petList = listOf(
            PetEntity(
                1,
                "Nikita",
                "Yorkshire Terrier",
                "1",
                "url",
                "DOG"
            )
        )

        // Act
        val preparedPetList = viewModel.petListSetup(petList)

        // Assert
        assert(petList == preparedPetList)
    }
}