package com.example.katalog_pakaian

import android.R.attr.onClick
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog

class ProductAdapter(

    private val onClick: (Product) -> Unit,

    private val onDelete: (Product) -> Unit,

    private val isCart: Boolean = false

) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val imgProduct: ImageView =
            itemView.findViewById(R.id.imgProduct)

        val txtName: TextView =
            itemView.findViewById(R.id.txtProductName)

        val txtPrice: TextView =
            itemView.findViewById(R.id.txtProductPrice)

        val txtRating: TextView =
            itemView.findViewById(R.id.txtRating)

        val btnDelete: ImageView =
            itemView.findViewById(R.id.btnDelete)

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

        val product = getItem(position)

        // Set data produk
        holder.imgProduct.setImageResource(product.image)
        holder.txtName.text = product.name
        holder.txtPrice.text = product.price
        holder.txtRating.text = "⭐ ${product.rating}"

        // Klik produk → detail
        holder.cardProduct.setOnClickListener {

            onClick(product)
        }

        // Tombol hapus
        holder.btnDelete.setOnClickListener {

            val currentPosition = holder.bindingAdapterPosition

            if (currentPosition != RecyclerView.NO_POSITION) {

                val deletedProduct = getItem(currentPosition)

                onDelete(deletedProduct)
            }
        }

        if (isCart) {

            holder.itemView.setOnLongClickListener {

                AlertDialog.Builder(holder.itemView.context)
                    .setTitle("Hapus Produk")
                    .setMessage("Yakin ingin menghapus produk dari keranjang?")
                    .setPositiveButton("Ya") { _, _ ->

                        val currentPosition = holder.bindingAdapterPosition

                        if (currentPosition != RecyclerView.NO_POSITION) {

                            val deletedProduct = getItem(currentPosition)

                            onDelete(deletedProduct)
                        }
                    }
                    .setNegativeButton("Batal", null)
                    .show()

                true
            }
        }
    }
}