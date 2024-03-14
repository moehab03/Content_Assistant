package com.route.aigeneration.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.aigeneration.databinding.FragmentContentCreatorBinding
import com.route.aigeneration.ui.fragments.idea.IdeaGenerationFragment
import com.route.aigeneration.ui.fragments.image.ImageGenerationFragment

class ContentCreatorFragment(
    private val replaceFragment: (fragment: Fragment) -> Unit,
    private val changeAppBarVisibility: (state: Boolean) -> Unit
) : Fragment() {

    private lateinit var binding: FragmentContentCreatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentCreatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initIdeaGenClicks()
        initImageGenClicks()
    }

    override fun onResume() {
        super.onResume()
        changeAppBarVisibility.invoke(true)
    }

    private fun initIdeaGenClicks() {
        binding.ideaGenerationCardView.setOnClickListener {
            replaceFragment.invoke(IdeaGenerationFragment {
                changeAppBarVisibility.invoke(it)
            })
        }
    }

    private fun initImageGenClicks() {
        binding.imageGenerationCardView.setOnClickListener {
            replaceFragment.invoke(ImageGenerationFragment
            {
                changeAppBarVisibility.invoke(it)
            })
        }
    }
}