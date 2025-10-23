package com.example.pendaftaranonline

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pendaftaranonline.api.BasicResponse
import com.example.pendaftaranonline.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IsiDataDiriActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_isi_data_diri)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etNIK = findViewById<EditText>(R.id.etNIK)
        val etAlamat = findViewById<EditText>(R.id.etAlamat)
        val etNoHp = findViewById<EditText>(R.id.etNoHp)
        val etTanggalLahir = findViewById<EditText>(R.id.etTanggalLahir)
        val rbLaki = findViewById<RadioButton>(R.id.rbLaki)
        val rbPerempuan = findViewById<RadioButton>(R.id.rbPerempuan)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val nik = etNIK.text.toString()
            val alamat = etAlamat.text.toString()
            val hp = etNoHp.text.toString()
            val tgl = etTanggalLahir.text.toString()
            val jk = if (rbLaki.isChecked) "L" else "P"

            RetrofitClient.instance.simpanDataDiri(
                nama, nik, alamat, hp, tgl, jk
            ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                    Toast.makeText(this@IsiDataDiriActivity, "Data tersimpan!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    Toast.makeText(this@IsiDataDiriActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
