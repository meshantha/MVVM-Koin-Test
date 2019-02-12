package com.kalum.monese.rockets.ui.view.feature.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalum.monese.rockets.data.remote.internal.lazyDeferred
import com.kalum.monese.rockets.data.repository.RocketsRepository

class RocketListViewModel(private val repository: RocketsRepository) : ViewModel() {

    internal val rockets by lazyDeferred() {
        repository.getAllRockets()
    }

    val loading = MutableLiveData<Boolean>()

    init {
        loading.value = true
    }
}
