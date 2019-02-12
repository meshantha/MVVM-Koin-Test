package com.kalum.monese.rockets.ui.view.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalum.monese.rockets.data.local.entity.RocketItem
import com.kalum.monese.rockets.data.remote.internal.lazyDeferred
import com.kalum.monese.rockets.data.repository.RocketsRepository

class RocketDetailsViewModel(private val id: String, private val repository: RocketsRepository) : ViewModel() {

    private val detailsRocket = MutableLiveData<RocketItem>()
    val loading = MutableLiveData<Boolean>()

    init {
        loading.value = true
    }

    val _detailsRocket: LiveData<RocketItem> get() = detailsRocket

    internal fun setRocketDetails(course: RocketItem) {
        detailsRocket.value = course
    }

    internal val detailedRocket by lazyDeferred { repository.getRocketById(id) }

}
