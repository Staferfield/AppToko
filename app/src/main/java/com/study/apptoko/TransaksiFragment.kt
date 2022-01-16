package com.study.apptoko

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.apptoko.adapter.TransaksiAdapter
import com.study.apptoko.api.BaseRetrofit
import com.study.apptoko.response.produk.ProdukResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransaksiFragment : Fragment() {

    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transaksi, container, false)

        getProduk(view)

        return view
    }

// * seharusnya digabungkan menjadi satu dengan produk
    fun getProduk(view: View){
        val token = LoginActivity.sessionManager.getString("TOKEN")

        // Jalankan API getProduk
        api.getProduk(token.toString()).enqueue(object : Callback<ProdukResponse> {
            override fun onResponse(
                call: Call<ProdukResponse>,
                response: Response<ProdukResponse>
            ) {
                // Log d -> log debug
                Log.d("ProdukData", response.body().toString())

//                val txtTotalProduk = view.findViewById(R.id.txtTotalProduk) as TextView
                // Tampilkan data produk menggunakan recycler view
                val rv = view.findViewById(R.id.rv_transaksi) as RecyclerView

//                txtTotalProduk.text = response.body()!!.data.produk.size.toString() + " item"

                rv.setHasFixedSize(true)
                rv.layoutManager = LinearLayoutManager(activity)
                val rvAdapter = TransaksiAdapter(response.body()!!.data.produk)
                rv.adapter = rvAdapter
            }

            override fun onFailure(call: Call<ProdukResponse>, t: Throwable) {
                // Log d -> log error
                Log.e("TransaksiError", t.toString())
            }

        })
    }

}