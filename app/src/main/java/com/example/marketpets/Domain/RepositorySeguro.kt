package com.example.marketpets.Domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketpets.Models.Seguro
import com.google.firebase.firestore.FirebaseFirestore

class RepositorySeguro {

    fun getSeguroData(): LiveData<MutableList<Seguro>> {
        val mutableLiveData = MutableLiveData<MutableList<Seguro>>()
        FirebaseFirestore.getInstance().collection("Seguro").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Seguro>()
            for (document in result.documents) {
                val tipo = document.getString("tipo")
                val descripcion = document.getString("descripcion")
                val precio = document.getLong("precio")?.toInt()
                val imagen = document.getString("imagen")
                val diversion = Seguro(tipo, descripcion, precio, imagen)
                listData.add(diversion)
            }
            mutableLiveData.value = listData
        }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error getting documents: ", exception)
            }
        return mutableLiveData
    }
}