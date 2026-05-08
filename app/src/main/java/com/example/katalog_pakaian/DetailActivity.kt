package com.example.katalog_pakaian

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.katalog_pakaian.CartActivity
import kotlin.jvm.java

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgProduct = findViewById<ImageView>(R.id.imgDetail)
        val txtName = findViewById<TextView>(R.id.txtDetailName)
        val txtPrice = findViewById<TextView>(R.id.txtDetailPrice)
        val txtDesc = findViewById<TextView>(R.id.txtDetailDesc)
        val btnCart = findViewById<Button>(R.id.btnCart)

        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val image = intent.getIntExtra("image", 0)
        val desc = intent.getStringExtra("description")

        imgProduct.setImageResource(image)
        txtName.text = name
        txtPrice.text = price
        txtDesc.text = desc

        btnCart.setOnClickListener {

            val product = Product(
                name ?: "",
                price ?: "",
                image,
                desc ?: ""
            )

            // tambah ke cart
            CartManager.cartItems.add(product)

            Toast.makeText(
                this,
                "Produk masuk keranjang",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(
                Intent(this, CartActivity::class.java)
            )
        }
    }
}