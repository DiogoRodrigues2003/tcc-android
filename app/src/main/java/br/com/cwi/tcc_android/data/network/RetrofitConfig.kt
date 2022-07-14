package br.com.cwi.tcc_android.data.network

import br.com.cwi.tcc_android.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitConfig {

    private val dogHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader(BuildConfig.X_API_HEADER, BuildConfig.DOG_API_KEY)
            it.proceed(request.build())
        }
        .build()

    private val catHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader(BuildConfig.X_API_HEADER, BuildConfig.CAT_API_KEY)
            it.proceed(request.build())
        }
        .build()

    val dogService: DogApi = Retrofit.Builder()
        .baseUrl(BuildConfig.DOG_API_BASE_URL)
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
        .baseUrl(BuildConfig.CAT_API_BASE_URL)
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