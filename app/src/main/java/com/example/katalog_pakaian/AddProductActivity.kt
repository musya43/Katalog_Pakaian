package com.example.katalog_pakaian

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPrice: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        // Inisialisasi View
        etName = findViewById(R.id.etProductName)
        etPrice = findViewById(R.id.etProductPrice)
        etDescription = findViewById(R.id.etProductDescription)
        btnSave = findViewById(R.id.btnSaveProduct)

        // Tombol simpan produk
        btnSave.setOnClickListener {

            val name = etName.text.toString()
            val price = etPrice.text.toString()
            val description = etDescription.text.toString()

            // Validasi input
            if (name.isEmpty() || price.isEmpty() || description.isEmpty()) {

                Toast.makeText(
                    this,
                    "Semua field harus diisi",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // Kirim data ke MainActivity
                val intent = Intent()

                intent.putExtra("name", name)
                intent.putExtra("price", price)
                intent.putExtra("description", description)

                // Default gambar sementara
                intent.putExtra(
                    "image",
                    R.drawable.baru
                )

                setResult(Activity.RESULT_OK, intent)

                Toast.makeText(
                    this,
                    "Produk berhasil ditambahkan",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}