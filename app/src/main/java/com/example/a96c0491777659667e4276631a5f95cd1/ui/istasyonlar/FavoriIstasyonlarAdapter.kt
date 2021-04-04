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
//            holder.recyclerviewIstasyonBinding.tvIstasyonOzellikleri.text =
//                "${Istasyonlar[position].stock}/${Istasyonlar[position].capacity}\n ${Istasyonlar[position].need}EUS"
        holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setOnClickListener {
            favoriIstasyonlar[position].is_favori = false
//            holder.itemView.visibility = View.GONE
            deleteItem(position)
//            listData.removeAt(position)
//            notifyDataSetChanged()
//                if (istasyonlar[position].is_favori) {
//                    holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setImageResource(R.drawable.ic_favori_istasyon)
//                } else {
//                    holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setImageResource(R.drawable.ic_favori)
//                }
        }
//            holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setOnClickListener {
//                Istasyonlar[position].is_favori = !Istasyonlar[position].is_favori
//                if (Istasyonlar[position].is_favori) {
//                    holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setImageResource(R.drawable.ic_favori_istasyon)
//                } else {
//                    holder.recyclerviewIstasyonBinding.ivFavoriIstasyon.setImageResource(R.drawable.ic_favori)
//                }
//                listener.onRecyclerViewItemClick(
//                    holder.recyclerviewIstasyonBinding.ivFavoriIstasyon,
//                    Istasyonlar[position]
//                )
//            }
    }


    inner class FavoriIstasyonlarViewHolder(
        val recyclerviewIstasyonBinding: RecyclerviewFavoriIstasyonBinding
    ) : RecyclerView.ViewHolder(recyclerviewIstasyonBinding.root)
}