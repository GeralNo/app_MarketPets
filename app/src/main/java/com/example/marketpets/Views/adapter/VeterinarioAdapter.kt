package com.example.marketpets.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketpets.Models.Veterinario
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton
import android.widget.ImageView
import android.widget.TextView


class VeterinarioAdapter (

    val context: Context,
    private val onItemClick: (Veterinario) -> Unit
) : RecyclerView.Adapter<VeterinarioAdapter.ViewHolder>() {
    private var veterinarioList: List<Veterinario> = listOf()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(veterinario: Veterinario) {

            itemView.findViewById<TextView>(R.id.veterinarioNombre).text = veterinario.nombre ?: "Sin nombre"
            itemView.findViewById<TextView>(R.id.veterinarioDisponibilidad).text = veterinario.disponibilidad ?: "horas disponibles"
            itemView.findViewById<TextView>(R.id.veterinarioPrecio).text = "$ ${veterinario.precio ?: 0.0}"
            Glide.with(context).load(veterinario.imagen).into(itemView.findViewById<ImageView>(R.id.veterinarioImage))

            itemView.findViewById<MaterialButton>(R.id.btnDetalles).setOnClickListener {
                onItemClick(veterinario)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_veterinario, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = veterinarioList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(veterinarioList[position])
    }

    fun setData(nuevaLista: List<Veterinario>) {
        veterinarioList = nuevaLista
        notifyDataSetChanged()
    }
}
