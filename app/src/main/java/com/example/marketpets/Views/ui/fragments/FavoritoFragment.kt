package com.example.marketpets.Views.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketpets.Models.Favorito
import com.example.marketpets.Views.adapter.FavoritoAdapter
import com.example.marketpets.databinding.FragmentFavoritoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoritoFragment : Fragment() {

    private var _binding: FragmentFavoritoBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: FavoritoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritoBinding.inflate(inflater, container, false)
        db = Firebase.firestore
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadUserFavorites()

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        adapter = FavoritoAdapter()
        binding.recyclerViewFavoritos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavoritos.adapter = adapter

        adapter.onEliminarClickListener = { favorito ->
            eliminarFavorito(favorito)
        }
    }

    private fun loadUserFavorites() {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: run {
            Log.w("FavoritoFragment", "Usuario no autenticado")
            return
        }

        db.collection("users")
            .document(currentUserId)
            .collection("favorites")
            .get()
            .addOnSuccessListener { documents ->
                val favoritesList = documents.map { doc ->
                    Favorito(
                        id = doc.id,
                        nombre = doc.getString("nombre") ?: "",
                        categoria = doc.getString("categoria") ?: "",
                        descripcion = doc.getString("descripcion") ?: "",
                        imagen = doc.getString("imagen") ?: ""
                    )
                }
                adapter.submitList(favoritesList)
            }
            .addOnFailureListener { e ->
                Log.e("FavoritoFragment", "Error cargando favoritos", e)
            }
    }

    private fun eliminarFavorito(favorito: Favorito) {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: return


        db.collection("users")
            .document(currentUserId)
            .collection("favorites")
            .document(favorito.id ?: return)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Favorito eliminado", Toast.LENGTH_SHORT).show()
                loadUserFavorites()
            }
            .addOnFailureListener { e ->
                Log.e("FavoritoFragment", "Error eliminando favorito", e)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}