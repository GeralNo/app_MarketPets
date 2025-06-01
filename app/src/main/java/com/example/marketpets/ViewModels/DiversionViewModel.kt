package com.example.marketpets.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketpets.Domain.RepositoryDiversion
import com.example.marketpets.Models.Diversion

class DiversionViewModel : ViewModel() {

    private val repositoryDiversion= RepositoryDiversion()

    fun fetchDiversionData():LiveData<MutableList<Diversion>>{
        val mutableData= MutableLiveData<MutableList<Diversion>>()
        repositoryDiversion.getDiversionData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }

    private val _diversionSeleccionada = MutableLiveData<Diversion>()
    val diversionSeleccionada: LiveData<Diversion> get() = _diversionSeleccionada

    fun seleccionarDiversion(item: Diversion) {
        _diversionSeleccionada.value=item
    }
}