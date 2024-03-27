package com.route.aigeneration.ui.ai_models.fragments.music

import android.os.Bundle
import android.view.View
import com.route.aigeneration.R
import com.route.aigeneration.base.BaseFragment
import com.route.aigeneration.databinding.FragmentMusicGenBinding

class MusicGenFragment(private val chooseType: (musicType: String) -> Unit) :
    BaseFragment<FragmentMusicGenBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onTypeClick()
    }

    override fun getLayoutID(): Int = R.layout.fragment_music_gen
    private fun onTypeClick() {
        binding.apply {
            popMusic.setOnClickListener {
                chooseType.invoke("pop")
            }
            discoMusic.setOnClickListener {
                chooseType.invoke("disco")
            }
            jazzMusic.setOnClickListener {
                chooseType.invoke("jazz")
            }
            hipHopMusic.setOnClickListener {
                chooseType.invoke("hiphop")
            }
            classicalMusic.setOnClickListener {
                chooseType.invoke("classical")
            }
            countryMusic.setOnClickListener {
                chooseType.invoke("country")
            }
        }
    }
}