package com.example.marketpets.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketpets.Models.Comida
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log

class RepositoryComida {

    fun getComidaData(): LiveData<MutableList<Comida>> {
        val mutableLiveData = MutableLiveData<MutableList<Comida>>()
        FirebaseFirestore.getInstance().collection("Comida").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Comida>()
            for (document in result.documents) {
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")
                val marca = document.getString("marca")
                val precio = document.getLong("precio")?.toInt()
                val tamano = document.getString("tamano")
                val disponibilidad = document.getLong("disponibilidad")?.toInt()
                val imagen = document.getString("imagen")
                val comida = Comida(nombre, descripcion, marca, precio, tamano, disponibilidad, imagen)
                listData.add(comida)
            }
            mutableLiveData.value = listData
        }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error getting documents: ", exception)
            }
        return mutableLiveData
    }
}