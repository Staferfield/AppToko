package com.study.apptoko

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.apptoko.response.produk.Produk

class ProdukAdapter(val listProduk: List<Produk>):RecyclerView.Adapter<ProdukAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = listProduk[position]
        holder.txtNamaProduk.text = produk.nama
        holder.txtHarga.text = produk.harga
    }

    override fun getItemCount(): Int {
        return listProduk.size
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView){
        val txtNamaProduk = itemView.findViewById(R.id.txtNamaProduk) as TextView
        val txtHarga = itemView.findViewById(R.id.txtHarga) as TextView
    }
}