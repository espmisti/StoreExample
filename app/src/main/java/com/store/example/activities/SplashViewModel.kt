package com.store.example.activities

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatViewInflater
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    var liveData: MutableLiveData<String> = MutableLiveData()


    fun initSplashScreen(){
        viewModelScope.launch {
            delay(2000)
            updateLiveData()
        }
    }
    private fun updateLiveData(){
        liveData.value = "zimfira"
    }
}