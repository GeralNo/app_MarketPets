package com.example.marketpets.Domain


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketpets.Models.Veterinario
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log


class RepositoryVeterinario {
    fun getVeterinarioData(): LiveData<MutableList<Veterinario>> {
        val mutableLiveData = MutableLiveData<MutableList<Veterinario>>()
        FirebaseFirestore.getInstance().collection("Veterinario").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Veterinario>()
            for (document in result.documents) {
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")
                val disponibilidad = document.getString("disponibilidad")
                val precio = document.getLong("precio")?.toInt()
                val imagen = document.getString("imagen")
                val veterinario = Veterinario(nombre, descripcion, disponibilidad, precio, imagen)
                listData.add(veterinario)
            }
            mutableLiveData.value = listData
        }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error getting documents: ", exception)
            }
        return mutableLiveData
    }
}
