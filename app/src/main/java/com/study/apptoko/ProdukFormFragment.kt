package com.study.apptoko

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.study.apptoko.api.BaseRetrofit
import com.study.apptoko.response.produk.ProdukResponsePost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukFormFragment : Fragment() {

    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_produk_form, container, false)

        val btnProsesProduk = view.findViewById<Button>(R.id.btnProsesProduk)
        btnProsesProduk.setOnClickListener{
            val txtFormNama = view.findViewById<TextInputEditText>(R.id.txtFormNama)
            val txtFormHarga = view.findViewById<TextInputEditText>(R.id.txtFormHarga)
            val txtFormStok = view.findViewById<TextInputEditText>(R.id.txtFormStok)

            val token = LoginActivity.sessionManager.getString("TOKEN")
            val adminId = LoginActivity.sessionManager.getString("ADMIN_ID")

            api.postProduk(token.toString(), adminId.toString().toInt(), txtFormNama.text.toString(), txtFormHarga.text.toString().toInt(), txtFormStok.text.toString().toInt()).enqueue(
                object :
                    Callback<ProdukResponsePost> {
                    override fun onResponse(
                        call: Call<ProdukResponsePost>,
                        response: Response<ProdukResponsePost>
                    ) {
                        Log.d("ProdukError", response.toString())
                        Toast.makeText(activity?.applicationContext, "Data di input", Toast.LENGTH_LONG).show()

                        findNavController().navigate(R.id.produkFragment)

                    }

                    override fun onFailure(call: Call<ProdukResponsePost>, t: Throwable) {
                        Log.e("Error", t.toString())
                    }

                }
            )
        }

        return view
    }

}