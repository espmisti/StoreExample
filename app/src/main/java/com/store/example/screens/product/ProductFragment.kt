package com.store.example.screens.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.store.example.R
import com.store.example.databinding.FragmentProductBinding
import com.store.example.utils.adapters.FeedBackAdapter
import android.view.*

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import com.store.example.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private var job: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        //
        val feedback = arrayListOf(R.array.person_1, R.array.person_2, R.array.person_3)
        val lm = LinearLayoutManager(context)
        lm.orientation = LinearLayoutManager.HORIZONTAL
        binding.feedbackRV.layoutManager = lm
        val adapter = FeedBackAdapter(feedback, context!!)

        binding.feedbackRV.adapter = adapter
        var text = arguments?.getStringArray("product")

        binding.name.text = text!![0]

        binding.about.text = text[1]
        binding.ratingBarProduct.rating = text[3].toFloat()
        binding.price.text = text[2]
        binding.ratingFeedback.text = "${text[5]} отзывов"
        binding.availability.text = "В наличии: ${text[6]}"
        binding.productButtonback.setOnClickListener { findNavController().navigate(R.id.mainFragment) }

        binding.productButtonbuy.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(context!!, R.style.SheetDialog)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)
            bottomSheetDialog.setCanceledOnTouchOutside(true)
            var button = bottomSheetDialog.findViewById<Button>(R.id.button_sheetDialog)
            button?.setOnClickListener {
                showDialog(activity)
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.show()
        }
        return binding.root
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        var inflater = super.onGetLayoutInflater(savedInstanceState)
        val wrappedContext = ContextThemeWrapper(requireActivity(), R.style.MyTheme)
        return inflater.cloneInContext(wrappedContext)
    }
    private fun showDialog(activity: Activity?) {
        val dialog = activity?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.dialog_success)
        val dialogButton: Button = dialog?.findViewById(R.id.dialog_button) as Button
        val _name: EditText = dialog.findViewById(R.id.ads_input_name)
        val _number: EditText = dialog.findViewById(R.id.ads_input_number)
        dialogButton.setOnClickListener{
            if(_name.text.isNotEmpty() && _number.text.isNotEmpty()){
                job = CoroutineScope(Dispatchers.IO).launch {
                    Repository().getResponse(_name.text.toString(), _number.text.toString(), "", "asdas")
                }
            } else {
                Toast.makeText(context, "Должы быть все заполненные поля!", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }
}