package br.com.cwi.tcc_android.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitConfig {

    private val dogHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("x-api-key", "dc9c3284-f550-45e3-a1b3-2b2536621b77")
            it.proceed(request.build())
        }
        .build()

    private val catHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("x-api-key", "0fa5d09e-ca36-45d4-a0d7-3c941d8c6dab")
            it.proceed(request.build())
        }
        .build()

    val dogService: DogApi = Retrofit.Builder()
        .baseUrl("https://api.thedogapi.com/v1/")
        .client(dogHttpClient)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
        .create(DogApi::class.java)

    val catService: CatApi = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .client(catHttpClient)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()
        .create(CatApi::class.java)
}