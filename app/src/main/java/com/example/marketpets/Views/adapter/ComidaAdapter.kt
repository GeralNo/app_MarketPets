package com.example.marketpets.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketpets.Models.Comida
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton
import android.widget.ImageView
import android.widget.TextView

class ComidaAdapter(

    val context: Context,
    private val onItemClick: (Comida) -> Unit
) : RecyclerView.Adapter<ComidaAdapter.ViewHolder>() {

    private var comidaList: List<Comida> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comida: Comida) {

            itemView.findViewById<TextView>(R.id.comidaNombre).text = comida.nombre ?: "Sin nombre"
            itemView.findViewById<TextView>(R.id.comidaMarca).text = comida.marca ?: "Sin marca"
            itemView.findViewById<TextView>(R.id.comidaPrecio).text = "$ ${comida.precio ?: 0.0}"
            itemView.findViewById<TextView>(R.id.comidaTamano).text = "Tamaño: ${comida.tamano ?: 0}"
            itemView.findViewById<TextView>(R.id.comidaDisponibilidad).text = "${comida.disponibilidad ?: 0} unidades disponibles"
            Glide.with(context).load(comida.imagen).into(itemView.findViewById<ImageView>(R.id.comidaImage))

            itemView.findViewById<MaterialButton>(R.id.btnDetalles).setOnClickListener {
                onItemClick(comida)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_comida, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = comidaList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comidaList[position])
    }

    fun setData(nuevaLista: List<Comida>) {
        comidaList = nuevaLista
        notifyDataSetChanged()
    }
}