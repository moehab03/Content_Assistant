package com.route.aigeneration.ui.fragments.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.aigeneration.databinding.FragmentMusicGenBinding

class MusicGenFragment(private val chooseType: (category: String) -> Unit) : Fragment() {
    private lateinit var binding: FragmentMusicGenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicGenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onTypeClick()
    }

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