package com.route.aigeneration.ui.ai_models.fragments.speech

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.route.aigeneration.R
import com.route.aigeneration.base.BaseFragment
import com.route.aigeneration.databinding.FragmentSpeechToTextBinding


class SpeechToTextFragment: BaseFragment<FragmentSpeechToTextBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadVideo()
    }
    private fun uploadVideo(){
        binding.uploadBtn.setOnClickListener {
            binding.apply {
                loadingLayout.loadingCardView.visibility = View.VISIBLE
                uploadBtn.visibility = View.GONE
                // remove handler method when api sends video
                Handler(Looper.getMainLooper()).postDelayed({
                    loadingLayout.loadingCardView.visibility = View.GONE
                    downloadBtn.visibility = View.VISIBLE
                    uploadTV.visibility = View.GONE
                },5000)
            }

        }
    }
    override fun getLayoutID(): Int = R.layout.fragment_speech_to_text
}