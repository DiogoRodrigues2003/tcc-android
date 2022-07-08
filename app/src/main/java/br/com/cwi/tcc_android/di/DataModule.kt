package br.com.cwi.tcc_android.di

import br.com.cwi.tcc_android.data.network.RetrofitConfig
import br.com.cwi.tcc_android.data.network.mapper.BreedMapper
import br.com.cwi.tcc_android.data.network.mapper.PetImageMapper
import br.com.cwi.tcc_android.data.network.mapper.PetMapper
import br.com.cwi.tcc_android.data.repository.DogRepositoryImpl
import br.com.cwi.tcc_android.domain.repository.DogRepository
import org.koin.dsl.module

val dataModule = module {

    single { RetrofitConfig.dogService }
    single { BreedMapper() }
    single { PetMapper() }
    single { PetImageMapper() }

    factory<DogRepository> { DogRepositoryImpl(get(), get(), get(), get()) }
}