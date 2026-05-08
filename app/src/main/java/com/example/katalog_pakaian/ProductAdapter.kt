package com.example.katalog_pakaian

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val productList: List<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val imgProduct: ImageView =
            itemView.findViewById(R.id.imgProduct)

        val txtName: TextView =
            itemView.findViewById(R.id.txtProductName)

        val txtPrice: TextView =
            itemView.findViewById(R.id.txtProductPrice)

        val cardProduct: CardView =
            itemView.findViewById(R.id.cardProduct)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {

        val product = productList[position]

        holder.imgProduct.setImageResource(product.image)
        holder.txtName.text = product.name
        holder.txtPrice.text = product.price

        // Klik item produk
        holder.cardProduct.setOnClickListener {
            onClick(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}