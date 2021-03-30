package com.example.a96c0491777659667e4276631a5f95cd1.data.repository

import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            //@todo handle api exception
            throw ApiException(
                response.code().toString()
            )
        }
    }

}

class ApiException(message: String): IOException(message)