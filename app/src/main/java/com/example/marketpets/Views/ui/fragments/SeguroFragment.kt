package com.example.marketpets.Views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.marketpets.Adapters.SeguroAdapter
import com.example.marketpets.Models.Seguro
import com.example.marketpets.ViewModels.SeguroViewModel
import com.example.marketpets.databinding.FragmentSeguroBinding

class SeguroFragment: Fragment() {
    private lateinit var adapter: SeguroAdapter
    private var _binding: FragmentSeguroBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SeguroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeguroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }


        adapter = SeguroAdapter(requireContext()) { seguroSeleccionada ->
            mostrarDetalleProducto(seguroSeleccionada)
        }
        binding.recyclerViewSeguro.adapter = adapter

        viewModel.fetchSeguroData().observe(viewLifecycleOwner) { listaSeguro ->
            adapter.setData(listaSeguro)
        }
    }

    private fun mostrarDetalleProducto(seguro: Seguro) {

        val dialog = AlertDialog.Builder(requireContext()).create()
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(16, 16, 16, 16)

        val imageView = ImageView(requireContext())
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            300
        )
        Glide.with(this).load(seguro.imagen).into(imageView)
        layout.addView(imageView)

        val nombreText = TextView(requireContext())
        nombreText.text = seguro.tipo
        nombreText.textSize = 18f
        nombreText.setPadding(0, 8, 0, 4)
        layout.addView(nombreText)

        val descripcionText = TextView(requireContext())
        descripcionText.text = "Descripción: ${seguro.descripcion}"
        descripcionText.setPadding(0, 4, 0, 4)
        layout.addView(descripcionText)

        val precioText = TextView(requireContext())
        precioText.text = "Precio: $ ${seguro.precio}"
        precioText.setPadding(0, 4, 0, 4)
        layout.addView(precioText)

        dialog.setView(layout)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cerrar") { _, _ -> }
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}