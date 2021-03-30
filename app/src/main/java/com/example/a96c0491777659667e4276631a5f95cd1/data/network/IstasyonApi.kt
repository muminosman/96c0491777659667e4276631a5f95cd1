package com.example.a96c0491777659667e4276631a5f95cd1.data.model.network

import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface IstasyonApi {

    @GET("e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    suspend fun getIstasyonlar(): Response<List<Istasyon>>

    companion object {
        operator fun invoke(): IstasyonApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://run.mocky.io/v3/")
                .build()
                .create(IstasyonApi::class.java)

        }
    }
}