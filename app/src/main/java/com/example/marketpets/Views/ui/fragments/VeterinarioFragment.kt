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
import com.example.marketpets.Adapters.VeterinarioAdapter
import com.example.marketpets.Models.Veterinario
import com.example.marketpets.ViewModels.VeterinarioViewModel
import com.example.marketpets.databinding.FragmentVeterinarioBinding


class VeterinarioFragment : Fragment() {


    private var _binding: FragmentVeterinarioBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VeterinarioViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVeterinarioBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }




        val adapter = VeterinarioAdapter(requireContext()) { veterinarioSeleccionada ->
            mostrarDetalleProducto(veterinarioSeleccionada)
        }
        binding.recyclerViewVeterinario.adapter = adapter


        viewModel.fetchVeterinarioData().observe(viewLifecycleOwner) { listaVeterinario ->
            adapter.setData(listaVeterinario)
        }
    }


    private fun mostrarDetalleProducto(veterinario: Veterinario) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(16, 16, 16, 16)


        val imageView = ImageView(requireContext())
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            300
        )
        Glide.with(this).load(veterinario.imagen).into(imageView)
        layout.addView(imageView)


        val nombreText = TextView(requireContext())
        nombreText.text = veterinario.nombre
        nombreText.textSize = 18f
        nombreText.setPadding(0, 8, 0, 4)
        layout.addView(nombreText)


        val descripcionText = TextView(requireContext())
        descripcionText.text = "Descripción: ${veterinario.descripcion}"
        descripcionText.setPadding(0, 4, 0, 4)
        layout.addView(descripcionText)


        val disponibilidadText = TextView(requireContext())
        disponibilidadText.text = "Marca: ${veterinario.descripcion}"
        disponibilidadText.setPadding(0, 4, 0, 4)
        layout.addView(disponibilidadText)


        val precioText = TextView(requireContext())
        precioText.text = "Precio: $ ${veterinario.precio}"
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
