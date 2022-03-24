package com.store.example.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.store.example.R

class Dialog {
    fun show(context: Context, layout_id: Int){
        val dialog = Dialog(context)
        setSetting(dialog, layout_id)
    }

    private fun setSetting(dialog: Dialog, layout_id: Int){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(layout_id)
    }
}