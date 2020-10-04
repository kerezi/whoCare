package com.whocare.android.app.ui.ocr

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.whocare.android.app.R
import com.whocare.android.app.ocr.IOCRCallBack
import com.whocare.android.app.ocr.OCRAsyncTask
import kotlinx.android.synthetic.main.fragment_ocr.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OcrFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OcrFragment : Fragment(), IOCRCallBack {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ocr, container, false)
        mIOCRCallBack = this
        mImageUrl =
            "https://raw.githubusercontent.com/kerezi/whoCare/main/app/src/main/res/drawable/demopic.jpg"
        // Image url to apply OCR API
        mLanguage = "eng" //Language
        isOverlayRequired = true

        mTxtResult = view.ocr_tv
        val btnCallAPI = view.call_ocr
        btnCallAPI?.setOnClickListener {
            val oCRAsyncTask = OCRAsyncTask(
                activity as Activity, mAPiKey, isOverlayRequired,
                mImageUrl!!,
                mLanguage!!,
                mIOCRCallBack!!
            )
            oCRAsyncTask.execute()
        }

        return view
    }

    private val mAPiKey = "helloworld" //TODO Add your own Registered API key

    private var isOverlayRequired = false
    private var mImageUrl: String? = null
    private var mLanguage: String? = null
    private var mTxtResult: TextView? = null
    private var mIOCRCallBack: IOCRCallBack? = null

     override fun getOCRCallBackResult(response: String?) {
        mTxtResult!!.text = response
    }
}