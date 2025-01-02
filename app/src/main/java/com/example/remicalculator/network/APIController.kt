package com.example.remicalculator.network


import com.example.remicalculator.Data.Translation
import com.example.remicalculator.Data.TranslationResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.POST


private const val BASE_URL = "https://google-translate113.p.rapidapi.com/"
private const val P_KEY = "3f5b2b562amsh5711e1b9f595049p1cbfc8jsnf585ae2cd7a4"
private const val HOST = "google-translate113.p.rapidapi.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()



interface ApiService{
    @Headers(
        "x-rapidapi-key: $P_KEY",
        "x-rapidapi-host: $HOST"
    )
    @POST("api/v1/translator/text")
    suspend fun getTranslation(
        @Body translation : Translation
    ) : Response<TranslationResponse>
}

object Api {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java)}
}