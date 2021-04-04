package com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a96c0491777659667e4276631a5f95cd1.R
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon
import com.example.a96c0491777659667e4276631a5f95cd1.databinding.RecyclerviewIstasyonBinding

class IstasyonlarAdapter(
    private val istasyonlar: List<Istasyon>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<IstasyonlarAdapter.IstasyonlarViewHolder>() {

    override fun getItemCount() = istasyonlar.size
    fun getIstasyonlar() = istasyonlar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        IstasyonlarViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_istasyon,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: IstasyonlarViewHolder, position: Int) {
        holder.recyclerviewIstasyonBinding.istasyon = istasyonlar[position]
        holder.recyclerviewIstasyonBinding.tvIstasyonOzellikleri.text =
            "${istasyonlar[position].capacity}/${istasyonlar[position].need}\n ${istasyonlar[position].need}EUS"
        holder.recyclerviewIstasyonBinding.btnTravel.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.recyclerviewIstasyonBinding.btnTravel,
                istasyonlar[position]
            )
        }
        holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setOnClickListener {
            istasyonlar[position].is_favori = !istasyonlar[position].is_favori
            if (istasyonlar[position].is_favori) {
                holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setImageResource(R.drawable.ic_favori_istasyon)
            } else {
                holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setImageResource(R.drawable.ic_favori)
            }
//            listener.onRecyclerViewItemClick(
//                holder.recyclerviewIstasyonBinding.ivFavoriIstasyon,
//                istasyonlar[position]
//            )
        }
    }


    inner class IstasyonlarViewHolder(
        val recyclerviewIstasyonBinding: RecyclerviewIstasyonBinding
    ) : RecyclerView.ViewHolder(recyclerviewIstasyonBinding.root)

}