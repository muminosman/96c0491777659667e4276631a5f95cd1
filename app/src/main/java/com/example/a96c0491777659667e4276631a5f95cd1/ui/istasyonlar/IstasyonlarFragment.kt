package com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a96c0491777659667e4276631a5f95cd1.R
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.IstasyonlarViewModelFactory
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.network.IstasyonApi
import com.example.a96c0491777659667e4276631a5f95cd1.data.repository.IstasyonlarRepository
import kotlinx.android.synthetic.main.istasyonlar_fragment.*

class IstasyonlarFragment : Fragment() {

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
//
        viewModel.getIstasyonlar()
//
        viewModel.istasyonlar.observe(viewLifecycleOwner, Observer { istasyonlar ->
            revFavoriIstasyonlar.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter =
                    IstasyonlarAdapter(
                        istasyonlar
                    )
            }
        })
    }
    //    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(IstasyonlarViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}