package com.route.aigeneration.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.route.aigeneration.R
import com.route.aigeneration.base.BaseFragment
import com.route.aigeneration.databinding.FragmentVideoEditorBinding
import com.route.aigeneration.ui.ai_models.ModelsActivity
import com.route.aigeneration.utils.Constant

class VideoEditorFragment : BaseFragment<FragmentVideoEditorBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMusicClick()
        initVoiceGenClick()
        initISpeechToTextClick()
    }


    private fun initMusicClick() {
        binding.musicRecommendationCardView.setOnClickListener {
            startModel(Constant.MUSIC_RECOMMENDATION)
        }
    }

    private fun initVoiceGenClick() {
        binding.voiceGenerationCardView.setOnClickListener {
            startModel(Constant.VOICE_GENERATION)
        }
    }

    private fun initISpeechToTextClick() {
        binding.speechToTextCardView.setOnClickListener {
            startModel(Constant.SPEECH_TO_TEXT)
        }
    }

    private fun startModel(model:String){
        val intent = Intent(activity,ModelsActivity::class.java)
        intent.putExtra(Constant.MODEL,model)
        startActivity(intent)
    }

    override fun getLayoutID(): Int = R.layout.fragment_video_editor
}