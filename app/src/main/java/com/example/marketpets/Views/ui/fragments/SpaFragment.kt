package com.example.marketpets.Views.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marketpets.Adapters.SpaAdapter
import com.example.marketpets.Models.Spa
import com.example.marketpets.ViewModels.SpaViewModel
import com.example.marketpets.databinding.FragmentSpaBinding

class SpaFragment : Fragment() {
    private var _binding: FragmentSpaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SpaViewModel by viewModels()
    private lateinit var adapter: SpaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        adapter = SpaAdapter(requireContext()) { spa ->
            viewModel.seleccionarSpa(spa)
            mostrarDetalleProducto(spa)
        }
        binding.recyclerViewSpa.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SpaFragment.adapter
            setHasFixedSize(true)
        }
    }

    private fun setupObservers() {
        viewModel.fetchSpaData().observe(viewLifecycleOwner) { spas ->
            adapter.setData(spas ?: mutableListOf())
        }
    }

    private fun mostrarDetalleProducto(spa: Spa) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(16, 16, 16, 16)

        val imageView = ImageView(requireContext())
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            300
        )
        Glide.with(this).load(spa.imagen).into(imageView)
        layout.addView(imageView)

        val nombreText = TextView(requireContext())
        nombreText.text = spa.nombre
        nombreText.textSize = 18f
        nombreText.setPadding(0, 8, 0, 4)
        layout.addView(nombreText)

        val descripcionText = TextView(requireContext())
        descripcionText.text = "Spa: ${spa.descripcion}"
        descripcionText.setPadding(0, 4, 0, 4)
        layout.addView(descripcionText)


        val precioText = TextView(requireContext())
        precioText.text = "Precio: $ ${spa.precio}"
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