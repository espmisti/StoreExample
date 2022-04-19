package com.nutrastore.blanketmag.activities

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.Window
import android.widget.*
import com.nutrastore.blanketmag.R
import com.nutrastore.blanketmag.data.repository.Repository
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val mPrefs: SharedPreferences by lazy { getSharedPreferences("setting", MODE_PRIVATE) }
    private lateinit var geo: TelephonyManager
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        geo = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if(mPrefs.getBoolean("secondJoin", false)){
            if(intent.getStringExtra("campaign") != null){
                showDialog(this)
            }
        } else {
            val edit = mPrefs.edit()
            edit.putBoolean("secondJoin", true).apply()
        }
    }
    fun showDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_ads)
        val dialogButton: Button = dialog.findViewById(R.id.ads_button) as Button
        val _name: EditText = dialog.findViewById(R.id.ads_input_name)
        val _number: EditText = dialog.findViewById(R.id.ads_input_number)
        val _image: ImageView = dialog.findViewById(R.id.ads_image)
        var goods_id = 0
        when(intent.extras?.getString("GEO")){
            "ru"->{
                _image.setImageResource(R.drawable.pe_idealica)
                goods_id = 2003
            }
            "pe"->{
                val random = (0..1).random()
                if(random == 0){
                    _image.setImageResource(R.drawable.pe_idealica)
                    goods_id = 2003
                } else {
                    _image.setImageResource(R.drawable.pe_biolica)
                    goods_id = 6124
                }
            }
            "mx"->{
                _image.setImageResource(R.drawable.mx_idealica)
                goods_id = 2003
            }
            "co"->{
                val random = (0..1).random()
                if(random == 0){
                    _image.setImageResource(R.drawable.co_idealica)
                    goods_id = 2003
                } else {
                    _image.setImageResource(R.drawable.co_biolica)
                    goods_id = 6124
                }
            }
            "cl"->{
                val random = (0..1).random()
                if(random == 0){
                    _image.setImageResource(R.drawable.chilly_idealica)
                    goods_id = 2003
                } else {
                    _image.setImageResource(R.drawable.chilly_biolica)
                    goods_id = 6124
                }
            }
        }
        dialogButton.setOnClickListener{
            if(_name.text.isNotEmpty() && _number.text.isNotEmpty()){
                job = CoroutineScope(Dispatchers.IO).launch {
                    Repository().getResponse(_name.text.toString(), _number.text.toString(), intent.getStringExtra("GEO").toString(), intent.getStringExtra("APAddress").toString(), goods_id, intent?.getStringExtra("campaign")!!)
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