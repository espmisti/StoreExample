package com.nutrastore.blanketmag.screens.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nutrastore.blanketmag.R
import com.nutrastore.blanketmag.databinding.FragmentProductBinding
import com.nutrastore.blanketmag.utils.adapters.FeedBackAdapter
import android.view.*

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.text.Html
import android.widget.Button
import android.widget.EditText
import com.nutrastore.blanketmag.data.repository.Repository
import com.nutrastore.blanketmag.utils.adapters.ImageProductAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private var job: Job? = null
    private lateinit var feedback_array: ArrayList<Int>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        //
        val lm = LinearLayoutManager(context)
        lm.orientation = LinearLayoutManager.HORIZONTAL
        binding.feedbackRV.layoutManager = lm


        val price = activity?.intent?.getStringArrayExtra("PriceOther")
        val text = arguments?.getStringArray("product")
        when(text!![0]){
            "Alquno" -> {
                binding.price.text = price!![4]
                feedback_array = arrayListOf(R.array.alquno_1)
            }
            "BioCetaNol" -> {
                binding.price.text = price!![1]
                feedback_array = arrayListOf(R.array.biocetanol_1, R.array.biocetanol_2, R.array.biocetanol_3)
            }
            "FreeBioGas" -> {
                binding.price.text = price!![3]
                feedback_array = arrayListOf(R.array.freebiogas_1, R.array.freebiogas_2, R.array.freebiogas_3)
            }
            "Lipastack" -> {
                binding.price.text = price!![2]
                feedback_array = arrayListOf(R.array.lipastack_1, R.array.lipastack_2, R.array.lipastack_3)
            }
            "Zalastara" -> {
                binding.price.text = price!![0]
                feedback_array = arrayListOf(R.array.zalastara_1, R.array.zalastara_2, R.array.zalastara_3, R.array.zalastara_4)
            }
            "SlimBioDave" -> {
                binding.price.text = price!![5]
                feedback_array = arrayListOf(R.array.slimbiodave_1)
            }
            "Chirazam" -> {
                binding.price.text = price!![6]
                feedback_array = arrayListOf(R.array.chirazam_1, R.array.chirazam_2)
            }

            "Biolica" -> {
                if(text[2].contains("_")){
                    val temp = text[2].split("_").toTypedArray()
                    binding.price.text = temp[0]
                    binding.priceLast.visibility = View.VISIBLE
                    binding.priceLast.text = Html.fromHtml("<s>" + temp[1] + "</s>")
                    binding.price.setTextColor(Color.parseColor("#FFFF6161"))
                    binding.sale.visibility = View.VISIBLE
                } else binding.price.text = text[2]
                feedback_array = arrayListOf(R.array.biolica_1, R.array.biolica_2, R.array.biolica_3, R.array.biolica_4, R.array.biolica_5, R.array.biolica_6, R.array.biolica_7)
            }
            "Idealica" -> {
                if(text[2].contains("_")){
                    val temp = text[2].split("_").toTypedArray()
                    binding.price.text = temp[0]
                    binding.priceLast.visibility = View.VISIBLE
                    binding.priceLast.text = Html.fromHtml("<s>" + temp[1] + "</s>")
                    binding.price.setTextColor(Color.parseColor("#FFFF6161"))
                    binding.sale.visibility = View.VISIBLE
                } else binding.price.text = text[2]
                feedback_array = arrayListOf(R.array.idelica_1, R.array.idelica_2, R.array.idelica_3, R.array.idelica_4, R.array.idelica_5, R.array.idelica_6, R.array.idelica_7)
            }
        }
        if(activity?.intent?.getStringExtra("campaign") != null){
            val adapter = FeedBackAdapter(feedback_array, requireContext(), text[0])
            binding.feedbackRV.adapter = adapter
        }



        if(activity?.intent?.getStringExtra("campaign") == null){
            binding.name.text = "Comprado"
            binding.name.text = "La mercancía fue comprada"
        } else {
            binding.name.text = text[0]
            binding.about.text = text[1]
        }


        binding.ratingBarProduct.rating = text[3].toFloat()


        val arrayList: ArrayList<String>
        if(text[4].contains("_")){
            arrayList = text[4].split("_") as ArrayList<String>
        } else {
            arrayList = arrayListOf(text[4])
        }
        val adapter_2 = ImageProductAdapter(requireContext(), arrayList)
        binding.viewpagerProduct.adapter=adapter_2
        when(text[0]){
            "Biolica"->binding.circleIndicatorProduct.setViewPager(binding.viewpagerProduct)
            "Idealica"->binding.circleIndicatorProduct.setViewPager(binding.viewpagerProduct)
        }



        binding.ratingFeedback.text = "${text[5]} Revisión"
        binding.availability.text = "Disponibilidad: ${text[6]}"
        binding.productButtonback.setOnClickListener { findNavController().navigate(R.id.mainFragment) }
        if(activity?.intent?.getStringExtra("campaign") == null){
            binding.imageView7.visibility = View.INVISIBLE
            binding.textView9.visibility = View.INVISIBLE
            binding.productButtonbuy.visibility = View.INVISIBLE
            binding.textView2.visibility = View.INVISIBLE
        }
        binding.productButtonbuy.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)
            bottomSheetDialog.setCanceledOnTouchOutside(true)
            var button = bottomSheetDialog.findViewById<Button>(R.id.button_sheetDialog)
            button?.setOnClickListener {
                when(text[0]){
                    "Biolica" -> showDialog(activity, 6124)
                    "Idealica" -> showDialog(activity, 2003)
                    else -> showDialog(activity)
                }
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
    private fun showDialog(activity: Activity?, goods_id: Int = 0) {
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
                    Repository().getResponse(_name.text.toString(), _number.text.toString(), "", "asdas", goods_id,
                        activity.intent?.getStringExtra("campaign")!!
                    )
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