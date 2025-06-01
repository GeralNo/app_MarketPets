package com.example.marketpets.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketpets.Models.Juguetes
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton
import android.widget.ImageView
import android.widget.TextView


class JuguetesAdapter(

    val context: Context,
    private val onItemClick: (Juguetes) -> Unit
) : RecyclerView.Adapter<JuguetesAdapter.ViewHolder>() {

    private var juguetesList: List<Juguetes> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(juguetes: Juguetes) {

            itemView.findViewById<TextView>(R.id.juguetesNombre).text = juguetes.nombre ?: "Sin nombre"
            itemView.findViewById<TextView>(R.id.juguetesMarca).text = juguetes.marca ?: "Sin marca"
            itemView.findViewById<TextView>(R.id.juguetesPrecio).text = "$ ${juguetes.precio ?: 0.0}"
            itemView.findViewById<TextView>(R.id.juguetesDisponibilidad).text = "${juguetes.disponibilidad ?: 0} unidades disponibles"
            Glide.with(context).load(juguetes.imagen).into(itemView.findViewById<ImageView>(R.id.juguetesImage))

            itemView.findViewById<MaterialButton>(R.id.btnDetalles).setOnClickListener {
                onItemClick(juguetes)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_juguetes, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = juguetesList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(juguetesList[position])
    }

    fun setData(nuevaLista: List<Juguetes>) {
        juguetesList = nuevaLista
        notifyDataSetChanged()
    }
}
