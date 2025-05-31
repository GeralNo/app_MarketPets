package com.example.marketpets.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketpets.Models.Adopcion
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log

class RepositoryAdopcion {

    fun getAdopcionData(): LiveData<MutableList<Adopcion>> {
        val mutableLiveData = MutableLiveData<MutableList<Adopcion>>()
        FirebaseFirestore.getInstance().collection("Adopcion").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Adopcion>()
            for (document in result.documents) {
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")
                val edad = document.getString("edad")
                val raza = document.getString("raza")
                val imagen = document.getString("imagen")
                val adopcion = Adopcion(nombre, descripcion, edad, raza, imagen)
                listData.add(adopcion)
            }
            mutableLiveData.value = listData
        }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error getting documents: ", exception)
            }
        return mutableLiveData
    }
}
