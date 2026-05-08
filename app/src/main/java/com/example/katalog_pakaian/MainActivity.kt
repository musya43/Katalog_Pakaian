package com.example.katalog_pakaian

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var searchBar: EditText
    private lateinit var btnAddProduct: Button
    private lateinit var btnCart: ImageButton

    private val productList = mutableListOf(

        Product(
            "Hoodie Black",
            "Rp 250.000",
            R.drawable.hoodie,
            "Hoodie premium warna hitam"
        ),

        Product(
            "Oversized T-Shirt",
            "Rp 150.000",
            R.drawable.tshirt,
            "Kaos oversized nyaman dipakai"
        ),

        Product(
            "Cargo Pants",
            "Rp 300.000",
            R.drawable.cargo,
            "Celana cargo fashion pria"
        ),

        Product(
            "Jacket Denim",
            "Rp 400.000",
            R.drawable.jacket,
            "Jaket denim casual"
        ),

        Product(
            "Sweater Cream",
            "Rp 220.000",
            R.drawable.sweater,
            "Sweater warna cream"
        ),

        Product(
            "Flannel Shirt",
            "Rp 180.000",
            R.drawable.flannel,
            "Kemeja flannel nyaman"
        ),

        Product(
            "Blazzer",
            "Rp 390.000",
            R.drawable.blazzer,
            "Blazzer formal nyaman"
        ),

        Product(
            "Cardigan",
            "Rp 250.000",
            R.drawable.cardigan,
            "Cardigan rajut lembut"
        ),

        Product(
            "Vest",
            "Rp 220.000",
            R.drawable.vest,
            "Vest rajut aesthetic"
        ),

        Product(
            "Crop Shirt",
            "Rp 180.000",
            R.drawable.crop,
            "Crop shirt fit to M"
        )
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerProduct)
        searchBar = findViewById(R.id.etSearch)
        btnAddProduct = findViewById(R.id.btnAddProduct)
        btnCart = findViewById(R.id.btnCart)

        // Grid 2 kolom
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Adapter + Klik Product
        productAdapter = ProductAdapter(productList) { product ->

            val intent = Intent(
                this,
                DetailActivity::class.java
            )

            intent.putExtra("name", product.name)
            intent.putExtra("price", product.price)
            intent.putExtra("image", product.image)
            intent.putExtra("description", product.description)

            startActivity(intent)
        }

        recyclerView.adapter = productAdapter

        // Tombol tambah produk
        btnAddProduct.setOnClickListener {

            val intent = Intent(
                this,
                AddProductActivity::class.java
            )

            startActivity(intent)
        }

        // Tombol cart
        btnCart.setOnClickListener {

            val intent = Intent(
                this,
                CartActivity::class.java
            )

            startActivity(intent)
        }
    }
}