package com.example.a96c0491777659667e4276631a5f95cd1.data.model

data class Istasyon(
    val capacity: Int,
    val coordinateX: Double,
    val coordinateY: Double,
    val name: String,
    val need: Int,
    val stock: Int,
    var is_favori: Boolean = false,
    var is_traveled: Boolean = true
)