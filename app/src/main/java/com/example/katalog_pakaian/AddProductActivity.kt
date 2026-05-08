package com.example.katalog_pakaian

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val btnSave = findViewById<Button>(R.id.btnSaveProduct)

        btnSave.setOnClickListener {

            Toast.makeText(
                this,
                "Produk berhasil ditambahkan",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }
}