package com.example.katalog_pakaian

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.jvm.java
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var searchBar: EditText
    private lateinit var btnAddProduct: FloatingActionButton
    private lateinit var btnCart: ImageButton
    private lateinit var bottomNavigation: BottomNavigationView

    private val ADD_PRODUCT_REQUEST = 1

    private val productList = mutableListOf(

        Product(
            1,
            "Hoodie Black",
            "Rp 250.000",
            4.1f,
            R.drawable.hoodie,
            "Hoodie premium warna hitam"
        ),

        Product(
            2,
            "Oversized T-Shirt",
            "Rp 150.000",
            4.2f,
            R.drawable.tshirt,
            "Kaos oversized nyaman dipakai"
        ),

        Product(
            3,
            "Cargo Pants",
            "Rp 300.000",
            4.0f,
            R.drawable.cargo,
            "Celana cargo fashion pria"
        ),

        Product(
            4,
            "Jacket Denim",
            "Rp 400.000",
            3.5f,
            R.drawable.jacket,
            "Jaket denim casual"
        ),

        Product(
            5,
            "Sweater Cream",
            "Rp 220.000",
            4.3f,
            R.drawable.sweater,
            "Sweater warna cream"
        ),

        Product(
            6,
            "Flannel Shirt",
            "Rp 180.000",
            4.8f,
            R.drawable.flannel,
            "Kemeja flannel nyaman"
        ),

        Product(
            7,
            "Blazzer",
            "Rp 390.000",
            4.9f,
            R.drawable.blazzer,
            "Blazzer formal nyaman"
        ),

        Product(
            8,
            "Cardigan",
            "Rp 250.000",
            4.5f,
            R.drawable.cardigan,
            "Cardigan rajut lembut"
        ),

        Product(
            9,
            "Vest",
            "Rp 220.000",
            4.7f,
            R.drawable.vest,
            "Vest rajut aesthetic"
        ),

        Product(
            10,
            "Crop Shirt",
            "Rp 180.000",
            4.8f,
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
        bottomNavigation = findViewById(R.id.bottomNavigation)

        // SEARCH
        searchBar.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

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

            override fun afterTextChanged(s: Editable?) {}
        })

        // GRID 2 KOLOM
        recyclerView.layoutManager =
            GridLayoutManager(this, 2)

        // ADAPTER
        productAdapter = ProductAdapter(

            // Klik item → detail
            onClick = { product ->

                val intent =
                    Intent(this, DetailActivity::class.java)

                intent.putExtra("name", product.name)
                intent.putExtra("price", product.price)
                intent.putExtra("image", product.image)
                intent.putExtra("description", product.description)
                intent.putExtra("rating", product.rating)

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
        productAdapter.submitList(
            productList.toList()
        )

        // tambah produk
        btnAddProduct.setOnClickListener {

            val intent =
                Intent(this, AddProductActivity::class.java)

            startActivityForResult(
                intent,
                ADD_PRODUCT_REQUEST
            )
        }

        // cart
        btnCart.setOnClickListener {

            val intent =
                Intent(this, CartActivity::class.java)

            startActivity(intent)
        }

        // bottom navigation
        bottomNavigation.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.nav_home -> {

                    true
                }

                R.id.nav_favorite -> {

                    val intent =
                        Intent(
                            this,
                            FavoriteActivity::class.java
                        )

                    startActivity(intent)

                    true
                }

                R.id.nav_profile -> {

                    val intent =
                        Intent(
                            this,
                            ProfileActivity::class.java
                        )

                    startActivity(intent)

                    true
                }

                else -> false
            }
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
                productList.size + 1,
                name,
                price,
                4.5f,
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