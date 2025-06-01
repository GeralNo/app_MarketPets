package com.example.marketpets.Domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketpets.Models.Spa
import com.google.firebase.firestore.FirebaseFirestore

class RepositorySpa {
    fun getSpaData(): LiveData<MutableList<Spa>> {
        val mutableLiveData = MutableLiveData<MutableList<Spa>>()
        FirebaseFirestore.getInstance().collection("Spa")
            .get()
            .addOnSuccessListener { result ->
                val listData = result.toObjects(Spa::class.java).toMutableList()
                Log.d("FirestoreData", "Datos obtenidos: ${listData.size} items")
                mutableLiveData.value = listData
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error al obtener datos", exception)
                mutableLiveData.value = mutableListOf()
            }
        return mutableLiveData
    }
}