package com.route.aigeneration.ui.ai_models.fragments.voice

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.route.aigeneration.R
import com.route.aigeneration.base.BaseFragment
import com.route.aigeneration.databinding.FragmentVoiceGenerationBinding


class VoiceGenerationFragment : BaseFragment<FragmentVoiceGenerationBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateBtnClick()
    }

    private fun generateBtnClick() {
        binding.generateBtn.setOnClickListener {
            binding.mainLayout.isVisible = false
            binding.loadingLayout.root.isVisible = true
        }
    }

    override fun getLayoutID(): Int = R.layout.fragment_voice_generation

}