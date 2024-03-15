package com.route.aigeneration.ui.fragments.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.route.aigeneration.R
import com.route.aigeneration.adapters.ViewPagerAdapter
import com.route.aigeneration.databinding.FragmentMusicRecommendationBinding


class MusicRecommendationFragment(
    private val changeAppBarVisibility: (state: Boolean) -> Unit
) : Fragment() {
    private lateinit var adapter: ViewPagerAdapter
    private val arrayList = arrayListOf("Music Gen", "Your input")
    private lateinit var binding: FragmentMusicRecommendationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicRecommendationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeAppBarVisibility.invoke(false)
        initViewPager()
        tabMargin()
    }

    private fun initViewPager() {
        adapter = ViewPagerAdapter(this, lifecycle) {
            changeActivePage()
        }
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPager.adapter = adapter
        attachTabToViewPager()
    }

    private fun attachTabToViewPager() {
        val tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = arrayList[position]
            }
        tabLayoutMediator.attach()
    }

    private fun tabMargin() {
        val tabs = binding.tabLayout.getChildAt(0) as ViewGroup
        tabs.forEach {
            val layoutParams = it.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginStart = 30
            layoutParams.marginEnd = 30
            it.layoutParams = layoutParams
            binding.tabLayout.requestLayout()
        }
    }

    private fun changeActivePage() {
        binding.viewPager.currentItem = R.layout.fragment_music_input
    }
}