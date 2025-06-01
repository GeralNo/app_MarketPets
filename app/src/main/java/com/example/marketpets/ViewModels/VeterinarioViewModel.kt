package com.example.marketpets.ViewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketpets.Domain.RepositoryVeterinario
import com.example.marketpets.Models.Veterinario


class VeterinarioViewModel : ViewModel() {
    private val repositoryVeterinario = RepositoryVeterinario()


    fun fetchVeterinarioData(): LiveData<MutableList<Veterinario>> {
        val mutableData = MutableLiveData<MutableList<Veterinario>>()
        repositoryVeterinario.getVeterinarioData().observeForever {
            mutableData.value = it
        }
        return mutableData
    }


    private val _veterinarioSeleccionada = MutableLiveData<Veterinario>()
    val veterinarioSeleccionada: LiveData<Veterinario> get() = _veterinarioSeleccionada


    fun seleccionarVeterinario(item: Veterinario) {
        _veterinarioSeleccionada.value = item
    }
}
