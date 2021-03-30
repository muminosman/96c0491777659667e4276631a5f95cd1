package com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar

import android.view.View
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, istasyon: Istasyon)
}