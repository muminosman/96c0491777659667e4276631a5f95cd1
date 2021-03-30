package com.example.a96c0491777659667e4276631a5f95cd1.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.a96c0491777659667e4276631a5f95cd1.R
import kotlinx.android.synthetic.main.activity_ana_ekean.*
import kotlinx.android.synthetic.main.favori_listesi_fragment.*
import kotlinx.android.synthetic.main.istasyonlar_fragment.*
import kotlinx.android.synthetic.main.toolbar_main_bottom.*

class AnaEkeanActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_ekean)
        iv_tb_istasyonlar.setOnClickListener(this)
        iv_tb_favorilar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_tb_istasyonlar -> {
                iv_tb_favorilar.setImageResource(R.drawable.ic_favori)
                iv_tb_istasyonlar.setImageResource(R.drawable.ic_space_ship_selected)
                viewFlipper.displayedChild = 0
                revIstasyonlar.adapter?.notifyDataSetChanged()
            }
            R.id.iv_tb_favorilar -> {
                iv_tb_favorilar.setImageResource(R.drawable.ic_favori_istasyon)
                iv_tb_istasyonlar.setImageResource(R.drawable.ic_space_ship)
                revFavoriIstasyonlar.adapter?.notifyDataSetChanged()
                viewFlipper.displayedChild = 1
            }
        }
    }
}