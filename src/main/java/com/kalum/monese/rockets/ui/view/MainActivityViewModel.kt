package com.kalum.monese.rockets.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val mSubtitle = MutableLiveData<String>()

    fun setSubtitle(subtitle: String) {
        mSubtitle.postValue(subtitle)
    }
}