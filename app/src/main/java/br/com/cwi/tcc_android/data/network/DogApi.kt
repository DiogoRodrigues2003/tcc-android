package br.com.cwi.tcc_android.data.network

import br.com.cwi.tcc_android.data.network.entity.BreedResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DogApi {

    @GET("/v1/breeds")
    suspend fun getDogBreeds(@Query("limit") limit: Int, @Query("page") page: Int): List<BreedResponse>
}