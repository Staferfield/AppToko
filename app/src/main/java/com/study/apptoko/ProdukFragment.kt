package com.study.apptoko

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.apptoko.LoginActivity.Companion.sessionManager
import com.study.apptoko.adapter.ProdukAdapter
import com.study.apptoko.api.BaseRetrofit
import com.study.apptoko.response.produk.ProdukResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukFragment : Fragment() {

    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_produk, container, false)

        getProduk(view)

        val btnTambah = view.findViewById<Button>(R.id.btnTambah)
        btnTambah.setOnClickListener{
//            Toast.makeText(activity?.applicationContext, "Click", Toast.LENGTH_LONG).show()

            val bundle = Bundle()
            bundle.putString("status","tambah")

            findNavController().navigate(R.id.produkFormFragment,bundle)
        }

        return view
    }

    fun getProduk(view: View){
        val token = sessionManager.getString("TOKEN")

        // Jalankan API getProduk
        api.getProduk(token.toString()).enqueue(object : Callback<ProdukResponse> {
            override fun onResponse(
                call: Call<ProdukResponse>,
                response: Response<ProdukResponse>
            ) {
                // Log d -> log debug
                Log.d("ProdukData", response.body().toString())

                val txtTotalProduk = view.findViewById(R.id.txtTotalProduk) as TextView
                // Tampilkan data produk menggunakan recycler view
                val rv = view.findViewById(R.id.rv_produk) as RecyclerView

                txtTotalProduk.text = response.body()!!.data.produk.size.toString() + " item"

                rv.setHasFixedSize(true)
                rv.layoutManager = LinearLayoutManager(activity)
                val rvAdapter = ProdukAdapter(response.body()!!.data.produk)
                rv.adapter = rvAdapter
            }

            override fun onFailure(call: Call<ProdukResponse>, t: Throwable) {
                // Log d -> log error
                Log.e("ProdukError", t.toString())
            }

        })
    }

}