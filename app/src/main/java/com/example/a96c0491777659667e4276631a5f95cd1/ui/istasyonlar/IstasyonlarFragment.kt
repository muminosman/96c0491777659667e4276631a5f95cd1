package com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a96c0491777659667e4276631a5f95cd1.MyApp
import com.example.a96c0491777659667e4276631a5f95cd1.R
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.IstasyonlarViewModelFactory
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.UzayAraci
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.network.IstasyonApi
import com.example.a96c0491777659667e4276631a5f95cd1.data.repository.IstasyonlarRepository
import kotlinx.android.synthetic.main.istasyonlar_fragment.*

class IstasyonlarFragment : Fragment(), RecyclerViewClickListener {
    private var myUzayAraci: UzayAraci

    init {
        myUzayAraci = MyApp.getMyUzayAraci()!!
    }

    //    companion object {
//        fun newInstance() = IstasyonlarFragment()
//    }
    private lateinit var factory: IstasyonlarViewModelFactory

    private lateinit var viewModel: IstasyonlarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.istasyonlar_fragment, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api =
            IstasyonApi()
        val repository =
            IstasyonlarRepository(
                api
            )
        factory = IstasyonlarViewModelFactory(repository)

        viewModel = ViewModelProviders.of(this, factory).get(IstasyonlarViewModel::class.java)
        viewModel.getIstasyonlar()

        viewModel.istasyonlar.observe(viewLifecycleOwner, Observer { istasyonlar ->
            revIstasyonlar.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayout.HORIZONTAL, false)
                it.setHasFixedSize(true)
                it.adapter =
                    IstasyonlarAdapter(
                        istasyonlar
                        , this
                    )
            }
        })

        tvUzayAracinadi.text = myUzayAraci.getAracAdi()
        tvUzayAracininHasar.text = "${myUzayAraci.getHasarKapasitesi()}"
        tvUzayAracininSeyahatler.text = "${myUzayAraci.getSeyahatler()}s"

        tvUGS.text = getString(R.string.ugs).replace("{0}", "${myUzayAraci.getUGS()}")
        tvEUS.text = getString(R.string.eus).replace("{0}", "${myUzayAraci.getEUS()}")
        tvDS.text = getString(R.string.ds).replace("{0}", "${myUzayAraci.getDS()}")

    }

    override fun onRecyclerViewItemClick(view: View, istasyon: Istasyon) {
        when (view.id) {
            R.id.btnTravel -> {
                Toast.makeText(requireContext(), "btnTravel ${istasyon.name}", Toast.LENGTH_LONG)
                    .show()
                istasyonaSeyahat(istasyon)

                viewModel.Istasyonlara(istasyon)

                viewModel.istasyonlar.observe(viewLifecycleOwner, Observer { istasyonlar ->
                    Toast.makeText(
                        requireContext(),
                        istasyonlar.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                })


















            }
            R.id.ivFavoriIstasyon -> {
                viewModel.addFavoriIstasyonlar(istasyon)
//                viewModel.getFavoriIstasyonlar()




                Toast.makeText(
                    requireContext(),
                    "ivFavoriIstasyon ${istasyon}",
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }

    private fun istasyonaSeyahat(istasyon: Istasyon) {
        myUzayAraci.istasyonaSeyahat(istasyon)
    }
    //    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(IstasyonlarViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}