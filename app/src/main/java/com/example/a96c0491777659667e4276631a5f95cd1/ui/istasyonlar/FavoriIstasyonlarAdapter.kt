package com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a96c0491777659667e4276631a5f95cd1.R
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon
import com.example.a96c0491777659667e4276631a5f95cd1.databinding.RecyclerviewFavoriIstasyonBinding

class FavoriIstasyonlarAdapter(
    private val favoriIstasyonlar: List<Istasyon>
) : RecyclerView.Adapter<FavoriIstasyonlarAdapter.FavoriIstasyonlarViewHolder>() {
    private var listData: MutableList<Istasyon> = favoriIstasyonlar as MutableList<Istasyon>

    override fun getItemCount() = favoriIstasyonlar.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriIstasyonlarViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_favori_istasyon,
                parent,
                false
            )
        )

    fun deleteItem(index: Int) {
        listData.removeAt(index)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FavoriIstasyonlarViewHolder, position: Int) {
        holder.recyclerviewIstasyonBinding.istasyon = listData[position]
        holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setOnClickListener {
            favoriIstasyonlar[position].is_favori = false
            deleteItem(position)
        }
    }


    inner class FavoriIstasyonlarViewHolder(
        val recyclerviewIstasyonBinding: RecyclerviewFavoriIstasyonBinding
    ) : RecyclerView.ViewHolder(recyclerviewIstasyonBinding.root)
}