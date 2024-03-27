package com.route.aigeneration.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.route.aigeneration.R
import com.route.aigeneration.base.BaseFragment
import com.route.aigeneration.databinding.FragmentContentCreatorBinding
import com.route.aigeneration.ui.ai_models.ModelsActivity
import com.route.aigeneration.utils.Constant

class ContentCreatorFragment : BaseFragment<FragmentContentCreatorBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initIdeaGenClicks()
        initImageGenClicks()
    }

    private fun initIdeaGenClicks() {
        binding.ideaGenerationCardView.setOnClickListener {
            startModel(Constant.IDEA_GENERATION)
        }
    }

    private fun initImageGenClicks() {
        binding.imageGenerationCardView.setOnClickListener {
            startModel(Constant.IMAGE_GENERATION)
        }
    }

    private fun startModel(model: String) {
        val intent = Intent(activity, ModelsActivity::class.java)
        intent.putExtra(Constant.MODEL, model)
        startActivity(intent)
    }

    override fun getLayoutID(): Int = R.layout.fragment_content_creator
}