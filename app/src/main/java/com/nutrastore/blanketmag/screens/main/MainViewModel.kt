package com.nutrastore.blanketmag.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application){
    val liveData: MutableLiveData<String> = MutableLiveData()

//    private fun updateLiveData(){
//        liveData.value =
//    }
}