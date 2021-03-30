package com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a96c0491777659667e4276631a5f95cd1.util.Coroutines
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon
import com.example.a96c0491777659667e4276631a5f95cd1.data.repository.IstasyonlarRepository
import kotlinx.coroutines.Job

class IstasyonlarViewModel(private val repository: IstasyonlarRepository) : ViewModel() {

    private lateinit var job: Job

    private val _istasyonlar = MutableLiveData<List<Istasyon>>()
    val istasyonlar: LiveData<List<Istasyon>>
        get() = _istasyonlar

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

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}