package com.example.marketpets.Views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketpets.Models.Favorito
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton

class FavoritoAdapter : ListAdapter<Favorito, FavoritoAdapter.ViewHolder>(DiffCallback()) {

    var onEliminarClickListener: ((Favorito) -> Unit)? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        private val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        private val tvCategoria: TextView = itemView.findViewById(R.id.tvCategoria)
        private val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        private val btnEliminar: MaterialButton = itemView.findViewById(R.id.btnEliminar)


        fun bind(favorito: Favorito) {
            tvNombre.text = favorito.nombre
            tvCategoria.text = favorito.categoria
            tvDescripcion.text = favorito.descripcion

            Glide.with(itemView.context)
                .load(favorito.imagen)
                .placeholder(R.drawable.placeholder_producto)
                .into(imgProducto)

            btnEliminar.setOnClickListener {
                onEliminarClickListener?.invoke(favorito)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_favorito, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DiffCallback : DiffUtil.ItemCallback<Favorito>() {
        override fun areItemsTheSame(oldItem: Favorito, newItem: Favorito): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Favorito, newItem: Favorito): Boolean {
            return oldItem == newItem
        }
    }
}
