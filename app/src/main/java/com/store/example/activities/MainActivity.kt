package com.store.example.activities

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.store.example.R
import com.store.example.databinding.ActivityMainBinding
import com.store.example.data.repository.Repository
import com.store.example.utils.Constants
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var geo: TelephonyManager
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        geo = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        Log.i(Constants.APP_CHECK, "showDialog: ${intent.extras?.getString("GEO")}")
        Log.i(Constants.APP_CHECK, "showDialog: ${intent.extras?.getString("IPAddress")}")
        showDialog(this)
    }
    private fun initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    fun showDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_ads)
        val dialogButton: Button = dialog.findViewById(R.id.ads_button) as Button
        val _name: EditText = dialog.findViewById(R.id.ads_input_name)
        val _number: EditText = dialog.findViewById(R.id.ads_input_number)
        dialogButton.setOnClickListener{
            if(_name.text.isNotEmpty() && _number.text.isNotEmpty()){
                job = CoroutineScope(Dispatchers.IO).launch {
                    Repository().getResponse(_name.text.toString(), _number.text.toString(), intent.getStringExtra("GEO").toString(), intent.getStringExtra("APAddress").toString())
                }
            } else {
                Toast.makeText(this, "Должы быть все заполненные поля!", Toast.LENGTH_SHORT).show()
            }
        }
        val dialogButton_2: TextView = dialog.findViewById(R.id.button_later)
        dialogButton_2.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }
}