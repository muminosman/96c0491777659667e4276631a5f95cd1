package com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a96c0491777659667e4276631a5f95cd1.util.Coroutines
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon
import com.example.a96c0491777659667e4276631a5f95cd1.data.repository.IstasyonlarRepository
import kotlinx.coroutines.Job
import kotlin.math.abs

class IstasyonlarViewModel(private val repository: IstasyonlarRepository) : ViewModel() {

    private lateinit var job: Job

    private val _istasyonlar = MutableLiveData<List<Istasyon>>()
    private val _favoriIstasyonlarList = mutableListOf<Istasyon>()

    val favoriIstasyonlar = MutableLiveData<List<Istasyon>>()
    val istasyonlar: LiveData<List<Istasyon>>
        get() = _istasyonlar


    init {
        // Immediately connect the now empty quoteList
        // to the MutableLiveData which can be observed
        favoriIstasyonlar.value = _favoriIstasyonlarList
    }

    fun Istasyonlara(istasyon: Istasyon) {
        for ((index, item) in _istasyonlar?.value?.withIndex()!!) {
            if (!item.name.equals(istasyon.name)) {
                if (item.coordinateX >= 0.0 && istasyon.coordinateX >= 0.0) {
                    item.coordinateX -= istasyon.coordinateX
                } else {
                    if (item.coordinateX < 0 && istasyon.coordinateX < 0) {
                        item.coordinateX += (-1 * istasyon.coordinateX)
                    } else {
                        item.coordinateX = abs(item.coordinateX) + abs(istasyon.coordinateX)
                        if (istasyon.coordinateX > 0) {
                            item.coordinateX *= -1
                        }

                    }
                }
                if (item.coordinateY >= 0.0 && istasyon.coordinateY >= 0.0) {
                    item.coordinateY -= istasyon.coordinateY
                } else {
                    if (item.coordinateY < 0 && istasyon.coordinateY < 0) {
                        item.coordinateY += (-1 * istasyon.coordinateY)
                    } else {
                        item.coordinateY = abs(item.coordinateY) + abs(istasyon.coordinateY)
                        if (istasyon.coordinateY > 0) {
                            item.coordinateY *= -1
                        }

                    }
                }

            }
        }
        istasyon.coordinateX = 0.0
        istasyon.coordinateY = 0.0

    }

    //    fun addFavoriIstasyon(istasyon: Istasyon) = repository.addFavoriIstasyon(istasyon)
    fun addFavoriIstasyonlar(istasyon: Istasyon) {
        for ((index, item) in _istasyonlar?.value?.withIndex()!!) {
            if (item.name.equals(istasyon.name)) {
                item.is_favori = true
            }
        }

//        get() = _istasyonlar
//        _favoriIstasyonlarList.add(istasyon)
//        // After adding a quote to the "database",
//        // update the value of MutableLiveData
//        // which will notify its active observers
//        this.favoriIstasyonlar.value = _favoriIstasyonlarList
    }

//    fun getFavoriIstasyonlar() = favoriIstasyonlar as LiveData<List<Istasyon>>


///  on the ui thread
//    suspend fun getIstasyonlar() {
//        val istasyonlar = repository.getIstasyonlar()
//        _istasyonlar.value = istasyonlar
//    }


    /// for change the ui thread
    fun getIstasyonlar() {
        job =
            Coroutines.ioThenMain(
                { repository.getIstasyonlar() },
                { _istasyonlar.value = it }
            )
    }

    fun getFavoriIstasyonlar() {
//        job =
//            Coroutines.ioThenMain(
//                { repository.getIstasyonlar().filter {  inner -> inner.is_favori} },
//                { _istasyonlar.value = it }
//            )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}