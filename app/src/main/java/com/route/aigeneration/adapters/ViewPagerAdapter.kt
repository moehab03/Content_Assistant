package com.route.aigeneration.adapters

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.route.aigeneration.ui.fragments.music.MusicGenFragment
import com.route.aigeneration.ui.fragments.music.MusicInputFragment

class ViewPagerAdapter(fragment: Fragment, lifeCycle: Lifecycle) :
    FragmentStateAdapter(fragment.childFragmentManager, lifeCycle) {

    private val musicGenFragment = MusicGenFragment()

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {

            0 -> return musicGenFragment

            1 -> return MusicInputFragment()

        }
        return musicGenFragment
    }
}