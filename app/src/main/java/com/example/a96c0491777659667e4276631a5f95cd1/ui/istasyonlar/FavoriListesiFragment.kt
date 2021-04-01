package com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a96c0491777659667e4276631a5f95cd1.R
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.IstasyonlarViewModelFactory
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.network.IstasyonApi
import com.example.a96c0491777659667e4276631a5f95cd1.data.repository.IstasyonlarRepository
import kotlinx.android.synthetic.main.favori_listesi_fragment.*


class FavoriListesiFragment : Fragment(), RecyclerViewClickListener {

    private lateinit var factory: IstasyonlarViewModelFactory
    private lateinit var viewModel: IstasyonlarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favori_listesi_fragment, container, false)
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
//        viewModel.getFavoriIstasyonlar()

//        viewModel.istasyonlar.observe(viewLifecycleOwner, Observer { favoriIstasyonlar ->
//            revFavoriIstasyonlar.also {
//                it.layoutManager =
//                    LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
//                it.setHasFixedSize(true)
//                it.adapter =
//                    FavoriIstasyonlarAdapter(
//                        favoriIstasyonlar.filter {  inner -> inner.is_favori}
//                        , this
//                    )
//            }
//        })
    }

    override fun onRecyclerViewItemClick(view: View, istasyon: Istasyon) {
        when (view.id) {
            R.id.btnTravel -> {
                Toast.makeText(requireContext(), "btnTravel ${istasyon.name}", Toast.LENGTH_LONG)
                    .show()
            }
            R.id.ivFavoriIstasyon -> {
//                viewModel.addFavoriIstasyonlar(istasyon)
                Toast.makeText(
                    requireContext(),
                    "ivFavoriIstasyon ${istasyon.name}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    //    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(IstasyonlarViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}