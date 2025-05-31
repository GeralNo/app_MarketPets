package com.example.marketpets.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketpets.Models.Adopcion
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton
import android.widget.ImageView
import android.widget.TextView

class AdopcionAdapter(

    val context: Context,
    private val onItemClick: (Adopcion) -> Unit
) : RecyclerView.Adapter<AdopcionAdapter.ViewHolder>() {

    private var adopcionList: List<Adopcion> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(adopcion: Adopcion) {

            itemView.findViewById<TextView>(R.id.adopcionNombre).text = adopcion.nombre ?: "Sin nombre"
            itemView.findViewById<TextView>(R.id.adopcionDescripcion).text = adopcion.descripcion ?: "Sin descripción"
            itemView.findViewById<TextView>(R.id.adopcionEdad).text = adopcion.edad ?: "Sin edad"
            itemView.findViewById<TextView>(R.id.adopcionRaza).text = adopcion.raza ?: "Sin raza"
            Glide.with(context).load(adopcion.imagen).into(itemView.findViewById<ImageView>(R.id.adopcionImage))


            itemView.findViewById<MaterialButton>(R.id.btnDetalles).setOnClickListener {
                onItemClick(adopcion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_adopcion, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = adopcionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adopcionList[position])
    }

    fun setData(nuevaLista: List<Adopcion>) {
        adopcionList = nuevaLista
        notifyDataSetChanged()
    }
}





