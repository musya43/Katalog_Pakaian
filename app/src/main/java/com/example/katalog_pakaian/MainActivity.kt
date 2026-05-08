package com.example.katalog_pakaian

import android.app.Activity
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
    private var filteredList = mutableListOf<Product>()

    private val ADD_PRODUCT_REQUEST = 1

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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerProduct)
        searchBar = findViewById(R.id.etSearch)
        btnAddProduct = findViewById(R.id.btnAddProduct)
        btnCart = findViewById(R.id.btnCart)

        searchBar.addTextChangedListener(object : android.text.TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val query = s.toString().lowercase()

                val result = if (query.isEmpty()) {

                    productList

                } else {

                    productList.filter {
                        it.name.lowercase().contains(query)
                    }
                }

                productAdapter.submitList(result.toList())
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        // Grid 2 kolom
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Adapter
        productAdapter = ProductAdapter(

            // Klik item → buka detail
            onClick = { product ->

                val intent =
                    Intent(this, DetailActivity::class.java)

                intent.putExtra("name", product.name)
                intent.putExtra("price", product.price)
                intent.putExtra("image", product.image)
                intent.putExtra("description", product.description)

                startActivity(intent)
            },

            // Hapus item
            onDelete = { product ->

                productList.remove(product)

                productAdapter.submitList(
                    productList.toList()
                )
            }
        )

        recyclerView.adapter = productAdapter

        // tampilkan data awal
        productAdapter.submitList(productList.toList())

        // tombol tambah produk
        btnAddProduct.setOnClickListener {

            val intent =
                Intent(this, AddProductActivity::class.java)

            startActivityForResult(
                intent,
                ADD_PRODUCT_REQUEST
            )
        }

        // tombol cart
        btnCart.setOnClickListener {

            val intent =
                Intent(this, CartActivity::class.java)

            startActivity(intent)
        }
    }

    // menerima data produk baru
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
            requestCode == ADD_PRODUCT_REQUEST &&
            resultCode == Activity.RESULT_OK &&
            data != null
        ) {

            val name =
                data.getStringExtra("name") ?: ""

            val price =
                data.getStringExtra("price") ?: ""

            val description =
                data.getStringExtra("description") ?: ""

            val image =
                data.getIntExtra(
                    "image",
                    R.drawable.hoodie
                )

            val newProduct = Product(
                name,
                price,
                image,
                description
            )

            // tambah ke list
            productList.add(newProduct)

            // update recycler view
            productAdapter.submitList(
                productList.toList()
            )
        }
    }
}