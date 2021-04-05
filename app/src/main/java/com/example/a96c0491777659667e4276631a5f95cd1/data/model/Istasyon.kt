package com.example.a96c0491777659667e4276631a5f95cd1.data.model

data class Istasyon(
    val capacity: Int,
    var coordinateX: Double,
    var coordinateY: Double,

    val name: String,

    var need: Int,
    var stock: Int,
    var is_favori: Boolean = false,
    var is_traveled: Boolean = false,
    var eus: Int
)