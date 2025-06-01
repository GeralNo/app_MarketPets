package com.example.marketpets.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketpets.Domain.RepositorySpa
import com.example.marketpets.Models.Spa


class SpaViewModel : ViewModel() {
    private val repositorySpa = RepositorySpa()
    private val _spaSeleccionada = MutableLiveData<Spa>()

    val spaSeleccionada: LiveData<Spa> get() = _spaSeleccionada

    fun fetchSpaData(): LiveData<MutableList<Spa>> {
        return repositorySpa.getSpaData()
    }

    fun seleccionarSpa(item: Spa) {
        _spaSeleccionada.value=item
    }
}