package com.example.marketpets.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketpets.Models.Spa
import com.example.marketpets.R
import com.example.marketpets.databinding.CardViewSpaBinding

class SpaAdapter(
    private val context: Context,
    private val onItemClick: (Spa) -> Unit
) : RecyclerView.Adapter<SpaAdapter.SpaViewHolder>() {

    private var dataList = mutableListOf<Spa>()

    inner class SpaViewHolder(private val binding: CardViewSpaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(spa: Spa) {
            binding.apply {
                spaNombre.text = spa.nombre ?: "Sin nombre"
                spaDescripcion.text = spa.descripcion ?: "Sin descripción"
                spaPrecio.text = "$${spa.precio ?: 0}"

                Glide.with(context)
                    .load(spa.imagen)
                    .placeholder(R.drawable.haircut)
                    .into(spaImage)

                btnDetalles.setOnClickListener { onItemClick(spa) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaViewHolder {
        val binding = CardViewSpaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SpaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpaViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    fun setData(newList: List<Spa>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }
}