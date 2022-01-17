package com.study.apptoko

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.study.apptoko.response.cart.Cart
import java.text.NumberFormat
import java.util.*


class BayarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bayar, container, false)

        val total = arguments?.getString("TOTAL")
        val my_cart = arguments?.getParcelableArrayList<Cart>("MY_CART")

        // Format ke rupiah
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)

        val txtKembalian = view.findViewById<TextView>(R.id.txtKembalian)

        val txtTotalTransaksiBayar = view.findViewById<TextView>(R.id.txtTotalTransaksiBayar)
        txtTotalTransaksiBayar.setText((numberFormat.format(total?.toDouble()).toString()))

        val txtPenerimaan = view.findViewById<EditText>(R.id.txtPenerimaan)
        txtPenerimaan.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            // Apabila input penerimaan berubah, lakukan dibawah setelah berubah
            override fun afterTextChanged(s: Editable?) {
                var kembalian : Int = 0

                if (txtPenerimaan.text.toString() != ""){
                    val penerimaan: Int = txtPenerimaan.text.toString().toInt()
                    kembalian = penerimaan - total.toString().toInt()

                    if (kembalian>0){
                        txtKembalian.setText(numberFormat.format(kembalian.toDouble()).toString())
                    }else{
                        txtKembalian.setText(numberFormat.format(0).toString())
                    }
                }
            }
        })

        return view
    }

}