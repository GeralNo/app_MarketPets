package com.example.marketpets.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketpets.Domain.RepositorySeguro
import com.example.marketpets.Models.Seguro

class SeguroViewModel : ViewModel() {

    private val repositorySeguro= RepositorySeguro()

    fun fetchSeguroData():LiveData<MutableList<Seguro>>{
        val mutableData= MutableLiveData<MutableList<Seguro>>()
        repositorySeguro.getSeguroData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }

    private val _seguroSeleccionada = MutableLiveData<Seguro>()
    val seguroSeleccionada: LiveData<Seguro> get() = _seguroSeleccionada

    fun seleccionarSeguro(item: Seguro) {
        _seguroSeleccionada.value = item
    }
}