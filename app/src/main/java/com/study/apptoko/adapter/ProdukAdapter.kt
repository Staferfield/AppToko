package com.study.apptoko.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.study.apptoko.LoginActivity
import com.study.apptoko.R
import com.study.apptoko.api.BaseRetrofit
import com.study.apptoko.response.produk.Produk
import com.study.apptoko.response.produk.ProdukResponsePost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdukAdapter(val listProduk: List<Produk>):RecyclerView.Adapter<ProdukAdapter.ViewHolder>() {

    private val api by lazy { BaseRetrofit().endpoint }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = listProduk[position]
        holder.txtNamaProduk.text = produk.nama
        holder.txtHarga.text = produk.harga

        holder.btnDelete.setOnClickListener {
            Toast.makeText(holder.itemView.context,produk.nama.toString(), Toast.LENGTH_LONG).show()

            val token = LoginActivity.sessionManager.getString("TOKEN")

            api.deleteProduk(token.toString(), produk.id.toInt()).enqueue(
                object :
                    Callback<ProdukResponsePost> {
                    override fun onResponse(
                        call: Call<ProdukResponsePost>,
                        response: Response<ProdukResponsePost>
                    ) {
                        Log.d("ProdukError", response.toString())
                        Toast.makeText(holder.itemView.context, "Data di hapus", Toast.LENGTH_LONG).show()

                        holder.itemView.findNavController().navigate(R.id.produkFragment)

                    }

                    override fun onFailure(call: Call<ProdukResponsePost>, t: Throwable) {
                        Log.e("Data", t.toString())
                    }

                }
            )
        }
    }

    override fun getItemCount(): Int {
        return listProduk.size
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView){
        val txtNamaProduk = itemView.findViewById(R.id.txtNamaProduk) as TextView
        val txtHarga = itemView.findViewById(R.id.txtHarga) as TextView
        val btnDelete = itemView.findViewById(R.id.btnDelete) as ImageButton
    }
}