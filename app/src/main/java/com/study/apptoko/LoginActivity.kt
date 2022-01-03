package com.study.apptoko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.study.apptoko.api.BaseRetrofit
import com.study.apptoko.response.login.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    // inisialisasi variabel
    // sama dengan private val api = BaseRetrofit().endpoint, tetapi by lazy lebih optimal untuk memory karena hanya akan di inisialisasi saat digunakan
    private val api by lazy { BaseRetrofit().endpoint }

    // Jalankan saat inisialisasi activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Deklarasi komponen sesuai UI di Layout
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtEmail = findViewById<TextInputEditText>(R.id.txtEmail)
        val txtPassword = findViewById<TextInputEditText>(R.id.txtPassword)

        // Buat listener dan tindakan saat button di klik
        btnLogin.setOnClickListener {
            Toast.makeText(this, "Login Proses", Toast.LENGTH_SHORT).show()

            // Jalankan API login
            api.login(txtEmail.text.toString(), txtPassword.text.toString()).enqueue(
                object :
                Callback<LoginResponse> {
                    // Apabila mendapat respon
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        Log.e("LoginData",response.toString())
                    }
                    // Apabila error
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.e("LoginError",t.toString())
                    }
                }
            )
        }


    }
}