package com.example.pendaftaranonline

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pendaftaranonline.api.AuthResponse
import com.example.pendaftaranonline.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import android.util.Log
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

            RetrofitClient.instance.login(email, password)
                .enqueue(object : Callback<AuthResponse> {
                    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                        Log.d("LOGIN_API", "code=${response.code()} body=${response.body()} errorBody=${response.errorBody()?.string()}")
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
                        Log.e("LOGIN_API", "onFailure: ${t.localizedMessage}", t)
                        Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }







    }
}
