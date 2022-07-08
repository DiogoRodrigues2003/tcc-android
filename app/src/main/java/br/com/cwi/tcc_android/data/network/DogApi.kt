package br.com.cwi.tcc_android.data.network

import br.com.cwi.tcc_android.data.network.entity.BreedResponse
import br.com.cwi.tcc_android.data.network.entity.PetResponse
import br.com.cwi.tcc_android.data.network.entity.PetImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {

    @GET("/v1/breeds")
    suspend fun getDogBreeds(@Query("limit") limit: Int, @Query("page") page: Int): List<BreedResponse>

    @GET("/v1/breeds/search")
    suspend fun getDogBreedByName(@Query("q") name: String?): List<PetResponse>

    @GET("/v1/images/search")
    suspend fun getNewDogImage(@Query("breed_id") id: Int?): List<PetImageResponse>
}