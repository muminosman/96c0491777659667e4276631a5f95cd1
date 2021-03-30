package com.example.a96c0491777659667e4276631a5f95cd1.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a96c0491777659667e4276631a5f95cd1.data.repository.IstasyonlarRepository
import com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar.IstasyonlarViewModel

@Suppress("UNCHECKED_CAST")
class IstasyonlarViewModelFactory(
    private val repository: IstasyonlarRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return IstasyonlarViewModel(
            repository
        ) as T
    }
}