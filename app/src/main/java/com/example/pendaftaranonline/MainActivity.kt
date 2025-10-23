package com.example.pendaftaranonline

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pendaftaranonline.api.AuthResponse
import com.example.pendaftaranonline.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi email dan password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            RetrofitClient.instance.login(email, password)
//                .enqueue(object : Callback<AuthResponse> {
//                    override fun onResponse(
//                        call: Call<AuthResponse>,
//                        response: Response<AuthResponse>
//                    ) {
//                        if (response.isSuccessful && response.body()?.success == true) {
//                            Toast.makeText(this@MainActivity, "Login berhasil!", Toast.LENGTH_SHORT).show()
//
//                            // lanjut isi data diri
//                            startActivity(Intent(this@MainActivity, IsiDataDiriActivity::class.java))
//                            finish()
//
//                        } else {
//                            Toast.makeText(this@MainActivity, "Login gagal!", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                        Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
//                    }
//                })
            RetrofitClient.instance.login(email, password)
                .enqueue(object : Callback<AuthResponse> {
                    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                        if (response.isSuccessful && response.body()?.success == true) {
                            val user = response.body()?.user
                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            intent.putExtra("userName", user?.name)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@MainActivity, "Login gagal!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })

        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}
