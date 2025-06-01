package com.example.marketpets.Domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketpets.Models.Diversion
import com.google.firebase.firestore.FirebaseFirestore

class RepositoryDiversion {

    fun getDiversionData(): LiveData<MutableList<Diversion>> {
        val mutableLiveData = MutableLiveData<MutableList<Diversion>>()
        FirebaseFirestore.getInstance().collection("Diversion").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Diversion>()
            for (document in result.documents) {
                val tipo = document.getString("tipo")
                val descripcion = document.getString("descripcion")
                val precio = document.getLong("precio")?.toInt()
                val imagen = document.getString("imagen")
                val diversion = Diversion(tipo, descripcion, precio, imagen)
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