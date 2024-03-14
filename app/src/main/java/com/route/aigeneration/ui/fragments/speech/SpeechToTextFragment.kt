package com.route.aigeneration.ui.fragments.speech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.route.aigeneration.R
import com.route.aigeneration.databinding.FragmentSpeechToTextBinding


class SpeechToTextFragment(
    private val changeAppBarVisibility: (state:Boolean) -> Unit
)  : Fragment() {
    private lateinit var binding: FragmentSpeechToTextBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpeechToTextBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeAppBarVisibility.invoke(false)
    }
}