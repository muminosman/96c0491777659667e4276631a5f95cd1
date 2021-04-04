package com.example.a96c0491777659667e4276631a5f95cd1.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import com.example.a96c0491777659667e4276631a5f95cd1.MyApp
import com.example.a96c0491777659667e4276631a5f95cd1.R
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.UzayAraci
import com.example.a96c0491777659667e4276631a5f95cd1.util.goToActivity
import kotlinx.android.synthetic.main.activity_uzay_araci_olusturma.*
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.M)
class UzayAraciOlusturmaActivity : AppCompatActivity()
    ,
    SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private var toplamPuan: Int = 15
    var dayaniklilik = 0
    var hiz = 0
    var kapasite = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uzay_araci_olusturma)

        btnDevamEt?.setOnClickListener(this)
        seekBarDayaniklilik?.setOnSeekBarChangeListener(this)
        seekBarKapasite?.setOnSeekBarChangeListener(this)
        seekBarHiz?.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val sum =
            seekBarDayaniklilik?.progress!! + seekBarHiz?.progress!! + seekBarKapasite?.progress!!
        toplamPuan = 15 - sum
        if (sum > 15) {
            seekBarDayaniklilik?.progress = dayaniklilik
            seekBarKapasite?.progress = kapasite
            seekBarHiz?.progress = hiz
            tvPuanKalmadi.text = getString(R.string.paun_kalmadi)
            tvPuanKalmadi.visibility = View.VISIBLE
            if (dayaniklilik == 0 || kapasite == 0 || hiz == 0) {
                tvPuanKalmadi.text =
                    "${tvPuanKalmadi.text} ve  ${getString(R.string.ozellik_sifir_olmaz)}"
                tvPuanKalmadi.visibility = View.VISIBLE
            }
        } else {
            dayaniklilik = seekBarDayaniklilik?.progress!!
            kapasite = seekBarKapasite?.progress!!
            hiz = seekBarHiz?.progress!!
            tvPuanKalmadi.visibility = View.INVISIBLE
            tvDayaniklilik.text =
                getString(R.string.dayaniklilik).replace("0", dayaniklilik.toString())
            tvHiz.text =
                getString(R.string.hiz).replace("0", hiz.toString())
            tvKapasite.text =
                getString(R.string.kapasite).replace("0", kapasite.toString())
            if (sum == 15 && (dayaniklilik == 0 || kapasite == 0 || hiz == 0)) {
                tvPuanKalmadi.text = getString(R.string.ozellik_sifir_olmaz)
                tvPuanKalmadi.visibility = View.VISIBLE
            }
        }
        tvToplamPuan?.text = "$toplamPuan"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

    fun aracOlustur() {
        MyApp.setMyUzayAraci(UzayAraci(edtAracAdi.text.toString(), hiz, kapasite, dayaniklilik))
        goToActivity(this, AnaEkeanActivity::class.java)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDevamEt -> {
                tvPuanKalmadi.visibility = View.INVISIBLE
                if (edtAracAdi.text.isEmpty()) {
                    edtAracAdi.error = getString(R.string.arac_adi_bos_olmaz)
                } else {
                    if (toplamPuan > 0) {
                        tvPuanKalmadi.text = getString(R.string.paun_kaldi)
                        tvPuanKalmadi.visibility = View.VISIBLE
                    } else {
                        aracOlustur()
                    }
                }

            }
        }
    }
}
