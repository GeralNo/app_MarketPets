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
import com.example.marketpets.Adapters.DiversionAdapter
import com.example.marketpets.Models.Diversion
import com.example.marketpets.ViewModels.DiversionViewModel
import com.example.marketpets.databinding.FragmentDiversionBinding

class DiversionFragment : Fragment() {

    private var _binding: FragmentDiversionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DiversionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiversionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val adapter = DiversionAdapter(requireContext()) { diversionSeleccionada ->
            mostrarDetalleProducto(diversionSeleccionada)
        }
        binding.recyclerViewDiversion.adapter = adapter

        viewModel.fetchDiversionData().observe(viewLifecycleOwner) { listaDiversion ->
            adapter.setData(listaDiversion)
        }
    }

    private fun mostrarDetalleProducto(diversion: Diversion) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(16, 16, 16, 16)

        val imageView = ImageView(requireContext())
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            300
            )
        Glide.with(this).load(diversion.imagen).into(imageView)
        layout.addView(imageView)

        val tipoText = TextView(requireContext())
        tipoText.text = diversion.tipo
        tipoText.textSize = 18f
        tipoText.setPadding(0, 8, 0, 4)
        layout.addView(tipoText)

        val descripcionText = TextView(requireContext())
        descripcionText.text = "Descripción: ${diversion.descripcion}"
        descripcionText.setPadding(0, 4, 0, 4)
        layout.addView(descripcionText)

        val precioText = TextView(requireContext())
        precioText.text = "Precio: $ ${diversion.precio}"
        precioText.setPadding(0, 4, 0, 4)
        layout.addView(precioText)

        dialog.setView(layout)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cerrar") { _, _ -> }
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}