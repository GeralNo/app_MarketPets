package com.example.marketpets.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketpets.Models.Diversion
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton
import android.widget.ImageView
import android.widget.TextView

class DiversionAdapter(

    val context: Context,
    private val onItemClick: (Diversion) -> Unit
) : RecyclerView.Adapter<DiversionAdapter.ViewHolder>() {

    private var diversionList: List<Diversion> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(diversion: Diversion) {

            itemView.findViewById<TextView>(R.id.diversionTipo).text = diversion.tipo ?: "Sin nombre"
            itemView.findViewById<TextView>(R.id.diversionDescripcion).text = diversion.descripcion ?: "Sin descripción"
            itemView.findViewById<TextView>(R.id.diversionPrecio).text = "$ ${diversion.precio ?: 0.0}"
            Glide.with(context).load(diversion.imagen).into(itemView.findViewById<ImageView>(R.id.diversionImage))


            itemView.findViewById<MaterialButton>(R.id.btnDetalles).setOnClickListener {
                onItemClick(diversion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_diversion, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = diversionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(diversionList[position])
    }

    fun setData(nuevaLista: List<Diversion>) {
        diversionList = nuevaLista
        notifyDataSetChanged()
    }
}