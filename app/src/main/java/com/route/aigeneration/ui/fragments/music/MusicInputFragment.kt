package com.route.aigeneration.ui.fragments.music

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.route.aigeneration.R
import com.route.aigeneration.databinding.FragmentMusicInputBinding
import com.route.aigeneration.utils.Constant

class MusicInputFragment : Fragment() {
    private lateinit var binding: FragmentMusicInputBinding
    private var musicType: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendBtnClick()
        downloadMusic()
        musicType = arguments?.getString(Constant.MUSIC_TYPE)
        Log.e("onViewCreated","musicType = $musicType")
        if(musicType!=null){
            binding.inputET.editText!!.setText(musicType)
            sendBtnClick()
        }
    }

    override fun onResume() {
        super.onResume()
        startPage()
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