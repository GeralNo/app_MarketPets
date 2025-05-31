package com.example.marketpets.Views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.marketpets.R
import com.google.android.material.card.MaterialCardView

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialCardView>(R.id.card_cuidado).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_cuidadoFragment)
        }

        view.findViewById<MaterialCardView>(R.id.card_comida).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_comidaFragment)
        }

        view.findViewById<MaterialCardView>(R.id.card_juguetes).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_juguetesFragment)
        }

        view.findViewById<MaterialCardView>(R.id.card_veterinario).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_veterinarioFragment)
        }

        view.findViewById<MaterialCardView>(R.id.card_seguros).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_segurosFragment)
        }

        view.findViewById<MaterialCardView>(R.id.card_adopcion).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_adopcionFragment)
        }

        view.findViewById<MaterialCardView>(R.id.card_spa).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_spaFragment)
        }

        view.findViewById<MaterialCardView>(R.id.card_diversion).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_diversionFragment)
        }
    }
}