package com.example.katalog_pakaian

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPrice: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSave: Button
    private lateinit var btnChooseImage: Button
    private lateinit var imgPreview: ImageView

    private var imageUri: Uri? = null

    private val IMAGE_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        etName = findViewById(R.id.etProductName)
        etPrice = findViewById(R.id.etProductPrice)
        etDescription = findViewById(R.id.etProductDescription)
        btnSave = findViewById(R.id.btnSaveProduct)
        btnChooseImage = findViewById(R.id.btnChooseImage)
        imgPreview = findViewById(R.id.imgPreview)

        // pilih gambar
        btnChooseImage.setOnClickListener {

            val intent =
                Intent(Intent.ACTION_OPEN_DOCUMENT)

            intent.addCategory(
                Intent.CATEGORY_OPENABLE
            )

            intent.type = "image/*"

            startActivityForResult(
                intent,
                IMAGE_REQUEST_CODE
            )
        }

        // simpan produk
        btnSave.setOnClickListener {

            val name = etName.text.toString()
            val price = etPrice.text.toString()
            val description = etDescription.text.toString()

            if (
                name.isEmpty() ||
                price.isEmpty() ||
                description.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Semua field harus diisi",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val intent = Intent()

                intent.putExtra("name", name)
                intent.putExtra("price", price)
                intent.putExtra("description", description)

                // kirim URI gambar
                intent.putExtra(
                    "imageUri",
                    imageUri.toString()
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

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (
            requestCode == IMAGE_REQUEST_CODE &&
            resultCode == RESULT_OK &&
            data != null
        ) {

            imageUri = data.data

            imgPreview.setImageURI(imageUri)
        }
    }
}