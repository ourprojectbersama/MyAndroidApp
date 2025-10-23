package com.example.pendaftaranonline

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // pastikan file xml ini ada di res/layout

        // Ambil data nama user yang dikirim dari MainActivity
        val userName = intent.getStringExtra("userName")

        // Inisialisasi TextView dari layout
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)

        // Kalau ada data user, tampilkan pesan sambutannya
        tvWelcome.text = if (userName.isNullOrEmpty()) {
            "Halo, Selamat datang!"
        } else {
            "Halo, $userName! Selamat datang."
        }
    }
}
