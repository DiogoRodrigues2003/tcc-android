package br.com.cwi.tcc_android.data.network

import br.com.cwi.tcc_android.data.network.entity.BreedResponse
import br.com.cwi.tcc_android.data.network.entity.CatResponse
import br.com.cwi.tcc_android.data.network.entity.PetImageResponse
import br.com.cwi.tcc_android.data.network.entity.DogResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("/v1/breeds")
    suspend fun getCatBreeds(@Query("limit") limit: Int, @Query("page") page: Int): List<BreedResponse>

    @GET("/v1/breeds/search")
    suspend fun getCatBreedByName(@Query("q") name: String?): List<CatResponse>

    @GET("/v1/images/search")
    suspend fun getNewCatImage(@Query("breed_id") id: String?): List<PetImageResponse>
}