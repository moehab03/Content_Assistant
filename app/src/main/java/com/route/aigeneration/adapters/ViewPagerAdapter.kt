package com.route.aigeneration.adapters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.route.aigeneration.ui.ai_models.fragments.music.MusicGenFragment
import com.route.aigeneration.ui.ai_models.fragments.music.MusicInputFragment
import com.route.aigeneration.utils.Constant

class ViewPagerAdapter(
    fragment: Fragment, lifeCycle: Lifecycle,
    changeView: () -> Unit
) :
    FragmentStateAdapter(fragment.childFragmentManager, lifeCycle) {
    private val inputFragment = MusicInputFragment()
    val bundle = Bundle()

    private val musicGenFragment = MusicGenFragment { type ->
        bundle.putString(Constant.MUSIC_TYPE, type)
        Log.e("ViewPagerAdapter", "bundle = $type")
        inputFragment.arguments = bundle
        changeView.invoke()
    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {

            0 -> return musicGenFragment

            1 -> return MusicInputFragment()

        }
        return musicGenFragment
    }
}