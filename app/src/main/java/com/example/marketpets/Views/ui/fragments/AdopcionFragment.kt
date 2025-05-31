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
import com.example.marketpets.Adapters.AdopcionAdapter
import com.example.marketpets.Models.Adopcion
import com.example.marketpets.ViewModels.AdopcionViewModel
import com.example.marketpets.databinding.FragmentAdopcionBinding

class AdopcionFragment : Fragment() {

    private var _binding: FragmentAdopcionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdopcionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdopcionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val adapter = AdopcionAdapter(requireContext()) { adopcionSeleccionada ->
            mostrarDetalleProducto(adopcionSeleccionada)
        }
        binding.recyclerViewAdopcion.adapter = adapter

        viewModel.fetchAdopcionData().observe(viewLifecycleOwner) { listaAdopcion ->
            adapter.setData(listaAdopcion)
        }
    }

    private fun mostrarDetalleProducto(adopcion: Adopcion) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(16, 16, 16, 16)

        val imageView = ImageView(requireContext())
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            300
        )
        Glide.with(this).load(adopcion.imagen).into(imageView)
        layout.addView(imageView)

        val nombreText = TextView(requireContext())
        nombreText.text = adopcion.nombre
        nombreText.textSize = 18f
        nombreText.setPadding(0, 8, 0, 4)
        layout.addView(nombreText)

        val descripcionText = TextView(requireContext())
        descripcionText.text = "Descripción: ${adopcion.descripcion}"
        descripcionText.setPadding(0, 4, 0, 4)
        layout.addView(descripcionText)

        val edadText = TextView(requireContext())
        edadText.text = "Edad: ${adopcion.edad}"
        edadText.setPadding(0, 4, 0, 4)
        layout.addView(edadText)

        val razaText = TextView(requireContext())
        razaText.text = "Raza: ${adopcion.raza}"
        razaText.setPadding(0, 4, 0, 4)
        layout.addView(razaText)

        dialog.setView(layout)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cerrar") { _, _ -> }
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}