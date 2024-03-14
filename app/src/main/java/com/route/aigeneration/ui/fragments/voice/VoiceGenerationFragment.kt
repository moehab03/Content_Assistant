package com.route.aigeneration.ui.fragments.voice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.route.aigeneration.databinding.FragmentVoiceGenerationBinding


class VoiceGenerationFragment(
    private val changeAppBarVisibility: (state: Boolean) -> Unit
) : Fragment() {

    private lateinit var binding: FragmentVoiceGenerationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoiceGenerationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeAppBarVisibility.invoke(false)
        binding.generateBtn.setOnClickListener {
            binding.mainLayout.isVisible = false
            binding.loadingLayout.root.isVisible = true
        }
    }

}