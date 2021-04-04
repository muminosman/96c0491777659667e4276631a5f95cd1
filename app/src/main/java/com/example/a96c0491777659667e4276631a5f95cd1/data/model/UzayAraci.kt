package com.example.a96c0491777659667e4276631a5f95cd1.data.model

import android.location.Location

class UzayAraci(name: String, hiz: Int, kapasite: Int, dayaniklilik: Int) {

    private var aracAdi: String = name

    private var hasarKapasitesi: Int = 100

    private var seyahatler: Int = 0

    private var simdikiIstasyon: Istasyon = Istasyon(0, 0.0, 0.0, "0", 0, 0, false, false)

    ///UGS(Uzay giysisi sayısı)
    private var ugs: Int = kapasite * 10000

    ///EUS(Evrensel uzay süresi)
    private var eus: Int = hiz * 20

    ///DS(Dayanıklılık Süresi)
    private var ds: Int = dayaniklilik * 10000

    fun getUGS(): Int = ugs
    fun getEUS(): Int = eus
    fun getDS(): Int = ds

    fun getAracAdi(): String = aracAdi

    fun getHasarKapasitesi(): Int = hasarKapasitesi

    fun getSeyahatler(): Int = seyahatler
    fun istasyonaSeyahat(istasyon: Istasyon): Boolean {

        if (ugs >= istasyon.need) {
            ugs -= istasyon.need
            istasyon.stock += istasyon.need
            istasyon.need = 0
/////////////////
            val loc1 = Location("")
            loc1.setLatitude(simdikiIstasyon.coordinateX)
            loc1.setLongitude(simdikiIstasyon.coordinateY)

            val loc2 = Location("")
            loc2.setLatitude(istasyon.coordinateX)
            loc2.setLongitude(istasyon.coordinateY)

            val distanceInMeters: Float = loc1.distanceTo(loc2)

            if (eus >= distanceInMeters) {
                eus -= distanceInMeters.toInt()
            }

        } else {


        }

        return false
    }

}