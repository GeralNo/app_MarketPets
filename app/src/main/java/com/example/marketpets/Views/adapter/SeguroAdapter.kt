package com.example.marketpets.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketpets.Models.Seguro
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton

class SeguroAdapter(

    val context: Context,
    private val onItemClick: (Seguro) -> Unit
) : RecyclerView.Adapter<SeguroAdapter.ViewHolder>() {

    private var seguroList: List<Seguro> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(seguro: Seguro) {

            itemView.findViewById<TextView>(R.id.seguroNombre).text = seguro.tipo ?: "Sin nombre"
            itemView.findViewById<TextView>(R.id.seguroDescripcion).text = seguro.descripcion ?: "Sin descripción"
            itemView.findViewById<TextView>(R.id.seguroPrecio).text = "$ ${seguro.precio ?: 0.0}"
            Glide.with(context).load(seguro.imagen).into(itemView.findViewById<ImageView>(R.id.seguroImage))


            itemView.findViewById<MaterialButton>(R.id.btnDetalles).setOnClickListener {
                onItemClick(seguro)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_seguro, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = seguroList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(seguroList[position])
    }

    fun setData(nuevaLista: List<Seguro>) {
        seguroList = nuevaLista
        notifyDataSetChanged()
    }
}