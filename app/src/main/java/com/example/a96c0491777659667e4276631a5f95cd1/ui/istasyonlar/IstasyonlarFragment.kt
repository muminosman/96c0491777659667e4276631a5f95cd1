package com.example.a96c0491777659667e4276631a5f95cd1.ui.istasyonlar

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a96c0491777659667e4276631a5f95cd1.MyApp
import com.example.a96c0491777659667e4276631a5f95cd1.R
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.Istasyon
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.IstasyonlarViewModelFactory
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.UzayAraci
import com.example.a96c0491777659667e4276631a5f95cd1.data.model.network.IstasyonApi
import com.example.a96c0491777659667e4276631a5f95cd1.data.repository.IstasyonlarRepository
import kotlinx.android.synthetic.main.favori_listesi_fragment.*
import kotlinx.android.synthetic.main.istasyonlar_fragment.*
import kotlinx.android.synthetic.main.layout_istasyon.*
import kotlinx.android.synthetic.main.layout_istasyon.tvEUS
import kotlinx.android.synthetic.main.toolbar_main_bottom.*
import java.util.*

@SuppressLint("WrongConstant")
class IstasyonlarFragment : Fragment(), RecyclerViewClickListener, View.OnClickListener {
    private var myUzayAraci: UzayAraci
    private lateinit var disPlayistasyonlar: ArrayList<Istasyon>
    private lateinit var _istasyonlar: ArrayList<Istasyon>
    private var factory: IstasyonlarViewModelFactory
    private lateinit var viewModel: IstasyonlarViewModel

    init {
        myUzayAraci = MyApp.getMyUzayAraci()!!
        val api =
            IstasyonApi()
        val repository =
            IstasyonlarRepository(
                api
            )
        factory = IstasyonlarViewModelFactory(repository)

    }


    private fun setupSearchView() {

        val searchView = teSearch
//        val ivSearch: ImageView = view!!.findViewById(R.id.ivSearch)
//        ivSearch.setOnClickListener { v: View? ->
//            if (searchView.text!!.length == 0) {
//                searchView.requestFocus()
//            } else {
//                searchView.setText("")
//            }
//            val imm =
//                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
//        }
        searchView.addTextChangedListener(object : TextWatcher {
            //            private var timer = Timer()
//            private val DELAY: Long = 500 // milliseconds
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
//                timer.cancel()
//                timer = Timer()
//                timer.schedule(
//                    object : TimerTask() {
//                        override fun run() {
//                Toast.makeText(requireContext(), "Ddd", Toast.LENGTH_LONG).show()
//                if (s!!.isNotEmpty()) {
//                    disPlayistasyonlar.clear()
//                    val search = s.toString().toLowerCase(Locale.getDefault())
//                    _istasyonlar.forEach {
//                        if (it.name.toLowerCase(Locale.getDefault()).contains(search)) {
//                            disPlayistasyonlar.add(it)
//                        }
//                    }
//                    revIstasyonlar.adapter!!.notifyDataSetChanged()
//
//                } else {
//                    disPlayistasyonlar.clear()
//                    disPlayistasyonlar.addAll(_istasyonlar)
//
//                    revIstasyonlar.adapter!!.notifyDataSetChanged()
//                }
//                            var dictionaryWordsAdapter = revIstasyonlar.adapter as IstasyonlarAdapter
//                             you will probably need to use runOnUiThread(Runnable action) for some specific actions (e.g. manipulating views)
//                            dictionaryWordsAdapter.getFilter().filter(searchView.text)
//                        }
//                    },
//                    DELAY
//                )
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.istasyonlar_fragment, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_tb_istasyonlar.setOnClickListener(this)
        iv_tb_favorilar.setOnClickListener(this)
        activity?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        viewModel = ViewModelProvider(this, factory).get(IstasyonlarViewModel::class.java)
        viewModel.getIstasyonlar()
        viewModel.istasyonlar.observe(viewLifecycleOwner, Observer { istasyonlar ->
            _istasyonlar = istasyonlar as ArrayList<Istasyon>
            myUzayAraci.istasyonaSeyahat(_istasyonlar[0])
            tvSimdilikIstasyon.text = getString(R.string.mevcit_istasyon).replace(
                "{name}",
                "${myUzayAraci.getSimdikiIstasyon().name}"
            )
            tvSimdilikIstasyon.setVisibility(View.VISIBLE)

            revIstasyonlar.also {
                it.adapter =
                    IstasyonlarAdapter(
                        istasyonlar
                        , this
                    )
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayout.HORIZONTAL, false)
                it.isNestedScrollingEnabled = false
                it.setHasFixedSize(true)
            }
                .getViewTreeObserver()
                .addOnGlobalLayoutListener(
                    object : OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            // At this point the layout is complete and the
                            // dimensions of recyclerView and any child views
                            // are known.
                            revIstasyonlar
                                .getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this)
                            loaderBar.setVisibility(View.GONE)

                            revIstasyonlar.setAlpha(0f)
                            revIstasyonlar.animate()
                                .alpha(1.0f)
                                .setDuration(1000)
                                .start()
                            revIstasyonlar.setVisibility(View.VISIBLE)
                            (loaderBar.getParent() as ViewManager).removeView(loaderBar)
                            revIstasyonlar.get(0)
                        }
                    })

        })
        tvUzayAracinadi.text = myUzayAraci.getAracAdi()
        tvUzayAracininHasar.text = "${myUzayAraci.getHasarKapasitesi()}"
        tvUzayAracininSeyahatler.text = "${myUzayAraci.getSeyahatler()}s"

        tvUGS.text = getString(R.string.ugs).replace("{0}", "${myUzayAraci.getUGS()}")
        tvEUS.text = getString(R.string.eus).replace("{0}", "${myUzayAraci.getEUS()}")
        tvDS.text = getString(R.string.ds).replace("{0}", "${myUzayAraci.getDS()}")

        tvSimdilikIstasyon.text = getString(R.string.mevcit_istasyon).replace(
            "{name}",
            "${myUzayAraci.getSimdikiIstasyon().name}"
        )
        setupSearchView()
    }

    override fun onRecyclerViewItemClick(view: View, istasyon: Istasyon) {
        when (view.id) {
            R.id.btnTravel -> {
                Toast.makeText(requireContext(), "btnTravel ${istasyon.name}", Toast.LENGTH_LONG)
                    .show()

                viewModel.Istasyonlara(istasyon)
                revIstasyonlar.adapter?.notifyDataSetChanged()
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
                revFavoriIstasyonlar.adapter?.notifyDataSetChanged()

            }
        }
    }

    private fun istasyonaSeyahat(istasyon: Istasyon) {
//        myUzayAraci.istasyonaSeyahat(istasyon)
    }

    @SuppressLint("WrongConstant")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_tb_istasyonlar -> {
                iv_tb_favorilar.setImageResource(R.drawable.ic_favori)
                iv_tb_istasyonlar.setImageResource(R.drawable.ic_space_ship_selected)
                viewFlipper.displayedChild = 0
            }
            R.id.iv_tb_favorilar -> {
                iv_tb_favorilar.setImageResource(R.drawable.ic_favori_istasyon)
                iv_tb_istasyonlar.setImageResource(R.drawable.ic_space_ship)
                var xx = revIstasyonlar.adapter as IstasyonlarAdapter
                revFavoriIstasyonlar.also {
                    it.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
                    it.adapter =
                        FavoriIstasyonlarAdapter(
                            xx.getIstasyonlar()
                                .filter { item -> item.is_favori })
                }

                viewFlipper.displayedChild = 1
            }
        }
    }
}