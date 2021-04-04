package com.example.a96c0491777659667e4276631a5f95cd1.data.repository

import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.network.IstasyonApi

class IstasyonlarRepository(
    private val api: IstasyonApi
) : SafeApiRequest() {

    suspend fun getIstasyonlar() = apiRequest { api.getIstasyonlar() }
}