package com.example.a96c0491777659667e4276631a5f95cd1.data.model

import android.location.Location

class UzayAraci(name: String, hiz: Int, kapasite: Int, dayaniklilik: Int) {

    private var aracAdi: String = name
    private var aracHiz: Int = hiz

    private var hasarKapasitesi: Int = 100

    private var seyahatler: Int = 0

    private var simdikiIstasyon: Istasyon = Istasyon(0, 0.0, 0.0, "0", 0, 0, false, false,0)

    ///UGS(Uzay giysisi sayısı)
    private var ugs: Int = kapasite * 10000

    ///EUS(Evrensel uzay süresi)
    private var eus: Int = hiz * 20

    ///DS(Dayanıklılık Süresi)
    private var ds: Int = dayaniklilik * 10000

    fun getSimdikiIstasyon(): Istasyon = simdikiIstasyon
    fun getUGS(): Int = ugs
    fun getEUS(): Int = eus
    fun getDS(): Int = ds

    fun getAracAdi(): String = aracAdi

    fun getHasarKapasitesi(): Int = hasarKapasitesi
    fun getHiz(): Int = aracHiz

    fun getSeyahatler(): Int = seyahatler

    fun istasyonaSeyahat(istasyon: Istasyon) {
        simdikiIstasyon = istasyon
    }

}