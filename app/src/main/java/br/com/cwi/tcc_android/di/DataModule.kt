package br.com.cwi.tcc_android.di

import br.com.cwi.tcc_android.data.network.RetrofitConfig
import br.com.cwi.tcc_android.data.network.mapper.BreedMapper
import br.com.cwi.tcc_android.data.network.mapper.CatMapper
import br.com.cwi.tcc_android.data.network.mapper.PetImageMapper
import br.com.cwi.tcc_android.data.network.mapper.DogMapper
import br.com.cwi.tcc_android.data.repository.CatRepositoryImpl
import br.com.cwi.tcc_android.data.repository.DogRepositoryImpl
import br.com.cwi.tcc_android.domain.repository.CatRepository
import br.com.cwi.tcc_android.domain.repository.DogRepository
import org.koin.dsl.module

val dataModule = module {

    single { RetrofitConfig.dogService }
    single { RetrofitConfig.catService }
    single { BreedMapper() }
    single { DogMapper() }
    single { CatMapper() }
    single { PetImageMapper() }

    factory<DogRepository> { DogRepositoryImpl(get(), get(), get(), get()) }
    factory<CatRepository> { CatRepositoryImpl(get(), get(), get(), get()) }
}