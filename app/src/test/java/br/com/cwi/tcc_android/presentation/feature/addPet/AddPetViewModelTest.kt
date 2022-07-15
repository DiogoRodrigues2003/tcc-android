package br.com.cwi.tcc_android.presentation.feature.addPet

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import br.com.cwi.tcc_android.data.database.entity.PetEntity
import br.com.cwi.tcc_android.data.database.mapper.toPetEntity
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Cat
import br.com.cwi.tcc_android.domain.entity.PetImage
import br.com.cwi.tcc_android.domain.repository.CatRepository
import br.com.cwi.tcc_android.domain.repository.PetLocalRepository
import br.com.cwi.tcc_android.extensions.test
import br.com.cwi.tcc_android.presentation.feature.cats.CatViewModel
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
class AddPetViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: AddPetViewModel

    private val petLocalRepository: PetLocalRepository = mockk()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = AddPetViewModel(petLocalRepository)
    }

/*
    @Test
    fun test() {
        // Arrange
        val submitObserver = viewModel.submit.test()
        viewModel.photoUrl = "url"

        val petName = "Nikita"
        val breedName = "Yorkshire Terrier"
        val breedId = "1"
        val petType = "DOG"

        val pet = PetEntity(
            0,
            petName,
            breedName,
            breedId,
            viewModel.photoUrl,
            petType
        )

        //coEvery { toPetEntity(petName, breedName, breedId, viewModel.photoUrl, petType) } returns pet
        when { petLocalRepository.add(pet) } returns Unit

        // Act
        viewModel.setPet(petName, breedName, breedId, petType)

        // Assert
        verify { petLocalRepository.add(pet) }
        verify { submitObserver.onChanged(any()) }
    }

 */
}