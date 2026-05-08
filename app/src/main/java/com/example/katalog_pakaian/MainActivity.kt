package com.example.katalog_pakaian

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.katalog_pakaian.Product
import com.example.katalog_pakaian.ProductAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var searchBar: EditText
    private lateinit var btnAddProduct: Button

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
            "Blazzer formal premium"
        ),

        Product(
            "Cardigan",
            "Rp 250.000",
            R.drawable.cardigan,
            "Cardigan rajut lembut"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerProduct)
        searchBar = findViewById(R.id.etSearch)
        btnAddProduct = findViewById(R.id.btnAddProduct)

        // RecyclerView Grid 2 Kolom
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Adapter
        productAdapter = ProductAdapter(productList)

        recyclerView.adapter = productAdapter

        // Tombol tambah produk
        btnAddProduct.setOnClickListener {

            Toast.makeText(
                this,
                "Tambah produk berhasil diklik",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}