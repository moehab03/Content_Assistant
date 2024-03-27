package com.route.aigeneration.ui.ai_models.fragments.music

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import com.route.aigeneration.R
import com.route.aigeneration.base.BaseFragment
import com.route.aigeneration.databinding.FragmentMusicInputBinding
import com.route.aigeneration.utils.Constant

class MusicInputFragment : BaseFragment<FragmentMusicInputBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendBtnClick()
        downloadMusic()
        searchSpecialMusicType()
    }

    override fun getLayoutID(): Int = R.layout.fragment_music_input
    override fun onResume() {
        super.onResume()
        startPage()
    }

    private fun getMusicType(): String? {
        return arguments?.getString(Constant.MUSIC_TYPE)
    }

    private fun searchSpecialMusicType() {
        if (getMusicType() != null) {
            binding.inputET.editText!!.setText(getMusicType())
            sendBtnClick()
        }
    }

    private fun sendBtnClick() {
        binding.apply {
            sendBtn.setOnClickListener {
                if (inputET.isNotEmpty()) {
                    progressBar.isVisible = true
                    sendBtn.text = ""
                    appearPlayView()
                }
            }
        }
    }

    //remove from inner code to end code after add API , customize it to appear the music
    private fun appearPlayView() {
        //inner code
        Handler(Looper.getMainLooper()).postDelayed({
            binding.apply {
                playLayout.isVisible = true
                downloadBtn.isVisible = true
                progressBar.isVisible = false
                sendBtn.isVisible = false
            }
        }, 2000)
        //end code
    }

    private fun startPage() {
        binding.apply {
            progressBar.isVisible = false
            downloadBtn.isVisible = false
            playLayout.isVisible = false
            inputET.editText!!.text = null
            sendBtn.text = getString(R.string.send)
            sendBtn.isVisible = true
        }
    }

    private fun downloadMusic() {
        binding.downloadBtn.setOnClickListener {
            // code to download music
        }
    }
}