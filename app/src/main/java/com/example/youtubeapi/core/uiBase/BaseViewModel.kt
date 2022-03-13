package com.example.youtubeapi.core.uiBase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    val loading = MutableLiveData<Boolean>()


}
