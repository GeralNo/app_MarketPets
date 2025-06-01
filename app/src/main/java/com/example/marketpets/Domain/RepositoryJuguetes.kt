package com.example.marketpets.Domain


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketpets.Models.Juguetes
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log


class RepositoryJuguetes {


    fun getJuguetesData(): LiveData<MutableList<Juguetes>> {
        val mutableLiveData = MutableLiveData<MutableList<Juguetes>>()
        FirebaseFirestore.getInstance().collection("Juguetes").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Juguetes>()
            for (document in result.documents) {
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")
                val marca = document.getString("marca")
                val precio = document.getDouble("precio")
                val disponibilidad = document.getLong("disponibilidad")?.toInt()
                val imagen = document.getString("imagen")
                val juguetes = Juguetes(nombre, descripcion, marca, precio, disponibilidad, imagen)
                listData.add(juguetes)
            }
            mutableLiveData.value = listData
        }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error getting documents: ", exception)
            }
        return mutableLiveData
    }
}
