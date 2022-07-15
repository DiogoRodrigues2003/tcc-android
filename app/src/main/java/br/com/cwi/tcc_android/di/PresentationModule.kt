package br.com.cwi.tcc_android.di

import br.com.cwi.tcc_android.presentation.feature.cats.CatViewModel
import br.com.cwi.tcc_android.presentation.feature.dogs.DogViewModel
import br.com.cwi.tcc_android.presentation.feature.addPet.AddPetViewModel
import br.com.cwi.tcc_android.presentation.feature.userPets.UserPetsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { DogViewModel(get()) }
    viewModel { CatViewModel(get()) }
    viewModel { UserPetsViewModel(get()) }
    viewModel { AddPetViewModel(get()) }
}