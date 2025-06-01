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
import com.example.marketpets.Adapters.JuguetesAdapter
import com.example.marketpets.Models.Juguetes
import com.example.marketpets.ViewModels.JuguetesViewModel
import com.example.marketpets.databinding.FragmentJuguetesBinding


class JuguetesFragment : Fragment() {


    private lateinit var adapter: JuguetesAdapter
    private var _binding: FragmentJuguetesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JuguetesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJuguetesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }


        adapter = JuguetesAdapter(requireContext()) { juguetesSeleccionada ->
            mostrarDetalleProducto(juguetesSeleccionada)
        }
        binding.recyclerViewJuguetes.adapter = adapter


        viewModel.fetchJuguetesData().observe(viewLifecycleOwner) { listaJuguetes ->
            adapter.setData(listaJuguetes)
        }
    }


    private fun mostrarDetalleProducto(juguetes: Juguetes) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(16, 16, 16, 16)


        val imageView = ImageView(requireContext())
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            300
        )
        Glide.with(this).load(juguetes.imagen).into(imageView)
        layout.addView(imageView)


        val nombreText = TextView(requireContext())
        nombreText.text = juguetes.nombre
        nombreText.textSize = 18f
        nombreText.setPadding(0, 8, 0, 4)
        layout.addView(nombreText)


        val descripcionText = TextView(requireContext())
        descripcionText.text = "Descripción: ${juguetes.descripcion}"
        descripcionText.setPadding(0, 4, 0, 4)
        layout.addView(descripcionText)




        val marcaText = TextView(requireContext())
        marcaText.text = "Marca: ${juguetes.marca}"
        marcaText.setPadding(0, 4, 0, 4)
        layout.addView(marcaText)


        val precioText = TextView(requireContext())
        precioText.text = "Precio: $ ${juguetes.precio}"
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
