package com.route.aigeneration.ui.fragments.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.aigeneration.databinding.FragmentMusicGenBinding

class MusicGenFragment(/*private val chooseType: (category: String) -> Unit*/) : Fragment() {
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
        //onTypeClick()
    }

//    private fun onTypeClick() {
//        binding.apply {
//            popMusic.setOnClickListener {
//                chooseType.invoke(it.tag as String)
//            }
//            jazzMusic.setOnClickListener {
//                chooseType.invoke(it.tag as String)
//            }
//            countryMusic.setOnClickListener {
//                chooseType.invoke(it.tag as String)
//            }
//            classicalMusic.setOnClickListener {
//                chooseType.invoke(it.tag as String)
//            }
//            hipHopMusic.setOnClickListener {
//                chooseType.invoke(it.tag as String)
//            }
//            discoMusic.setOnClickListener {
//                chooseType.invoke(it.tag as String)
//            }
//        }
//    }
}