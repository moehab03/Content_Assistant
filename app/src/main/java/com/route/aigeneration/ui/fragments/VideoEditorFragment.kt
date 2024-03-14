package com.route.aigeneration.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.aigeneration.databinding.FragmentVideoEditorBinding
import com.route.aigeneration.ui.fragments.music.MusicRecommendationFragment
import com.route.aigeneration.ui.fragments.speech.SpeechToTextFragment
import com.route.aigeneration.ui.fragments.voice.VoiceGenerationFragment

class VideoEditorFragment(
    private val replaceFragment: (fragment: Fragment) -> Unit,
    private val changeAppBarVisibility: (state: Boolean) -> Unit
) : Fragment() {

    private lateinit var binding: FragmentVideoEditorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMusicClick()
        initVoiceGenClick()
        initISpeechToTextClick()
    }

    override fun onResume() {
        super.onResume()
        changeAppBarVisibility.invoke(true)
    }

    private fun initMusicClick() {
        binding.musicRecommendationCardView.setOnClickListener {
            replaceFragment.invoke(MusicRecommendationFragment {
                changeAppBarVisibility(it)
            })
        }
    }

    private fun initVoiceGenClick() {
        binding.voiceGenerationCardView.setOnClickListener {
            replaceFragment.invoke(VoiceGenerationFragment {
                changeAppBarVisibility(it)
            })
        }
    }

    private fun initISpeechToTextClick() {
        binding.speechToTextCardView.setOnClickListener {
            replaceFragment.invoke(SpeechToTextFragment {
                changeAppBarVisibility(it)
            })
        }
    }
}