package com.example.marketpets.ViewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketpets.Domain.RepositoryJuguetes
import com.example.marketpets.Models.Juguetes


class JuguetesViewModel:ViewModel() {


    private val repositoryJuguetes= RepositoryJuguetes()


    fun fetchJuguetesData():LiveData<MutableList<Juguetes>>{
        val mutableData= MutableLiveData<MutableList<Juguetes>>()
        repositoryJuguetes.getJuguetesData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }


    private val _juguetesSeleccionada = MutableLiveData<Juguetes>()
    val juguetesSeleccionada: LiveData<Juguetes> get() = _juguetesSeleccionada


    fun seleccionarJuguetes(item: Juguetes) {
        _juguetesSeleccionada.value = item
    }
}
