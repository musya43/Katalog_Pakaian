package com.example.katalog_pakaian

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerCart: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerCart =
            findViewById(R.id.recyclerCart)

        recyclerCart.layoutManager =
            LinearLayoutManager(this)

        // pakai ProductAdapter yang sudah kamu punya
        adapter = ProductAdapter(

            onClick = { /* tidak perlu aksi */ },

            onDelete = { product ->

                // hapus dari cart
                CartManager.cartItems.remove(product)

                // refresh tampilan
                adapter.submitList(
                    CartManager.cartItems.toList()
                )
            }
        )

        recyclerCart.adapter = adapter

        // tampilkan data dari CartManager
        adapter.submitList(
            CartManager.cartItems.toList()
        )
    }
}