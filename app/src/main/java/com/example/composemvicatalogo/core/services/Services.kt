package com.example.composemvicatalogo.core.services

import com.example.composemvicatalogo.core.models.LifeStyleResponse
import com.example.composemvicatalogo.core.models.SearchLifeStyleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Services {
    @GET("lifestyles/0")
    suspend fun getLifeStyles(): Response<LifeStyleResponse>
    @GET("search/{lifeStyle}")
    suspend fun getSearchLifeStyle(@Path("lifeStyle", encoded = true) lifeStyle: String): Response<SearchLifeStyleResponse>
}