package com.study.apptoko.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.apptoko.R
import com.study.apptoko.response.produk.Produk

class TransaksiAdapter (val listProduk: List<Produk>): RecyclerView.Adapter<TransaksiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaksi, parent, false)
        return TransaksiAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransaksiAdapter.ViewHolder, position: Int) {
        val produk = listProduk[position]
        holder.txtNamaProduk.text = produk.nama
        holder.txtHarga.text = produk.harga

        holder.btnPlus.setOnClickListener {
            val old_value = holder.txtQty.text.toString().toInt()
            val new_value = old_value+1

            holder.txtQty.setText(new_value.toString())
        }

        holder.btnMinus.setOnClickListener {
            val old_value = holder.txtQty.text.toString().toInt()
            val new_value = old_value-1

            // Hanya kurangi kalau nilai lebih dari nol
            if (new_value>=0){
                holder.txtQty.setText(new_value.toString())
            }

        }

    }

    override fun getItemCount(): Int {
        return listProduk.size
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val txtNamaProduk = itemView.findViewById(R.id.txtNamaProduk) as TextView
        val txtHarga = itemView.findViewById(R.id.txtHarga) as TextView
        val txtQty = itemView.findViewById(R.id.txtQty) as TextView
        val btnPlus = itemView.findViewById(R.id.btnPlus) as ImageButton
        val btnMinus = itemView.findViewById(R.id.btnMinus) as ImageButton
    }
}