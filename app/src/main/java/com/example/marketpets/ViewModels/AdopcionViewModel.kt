package com.example.marketpets.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketpets.Domain.RepositoryAdopcion
import com.example.marketpets.Models.Adopcion

class AdopcionViewModel : ViewModel() {

    private val repositoryAdopcion= RepositoryAdopcion()

    fun fetchAdopcionData():LiveData<MutableList<Adopcion>>{
        val mutableData= MutableLiveData<MutableList<Adopcion>>()
        repositoryAdopcion.getAdopcionData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }

    private val _adopcionSeleccionada = MutableLiveData<Adopcion>()
    val adopcionSeleccionada: LiveData<Adopcion> get() = _adopcionSeleccionada

    fun seleccionarAdopcion(item: Adopcion) {
        _adopcionSeleccionada.value = item
    }
}










