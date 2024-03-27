package com.route.aigeneration.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.aigeneration.R
import com.route.aigeneration.adapters.ProfilesAdapter
import com.route.aigeneration.base.BaseFragment
import com.route.aigeneration.databinding.FragmentSelectProfileBinding
import com.route.aigeneration.models.Profiles
import com.route.aigeneration.utils.Constant


class SelectProfileFragment(
    private val replaceCurrentFragment:
        (
        fragment: Fragment,
        itemID: Int, homeItemState: Boolean
    ) -> Unit
) : BaseFragment<FragmentSelectProfileBinding>() {
    private val videoEditorFragment = VideoEditorFragment()
    private val contentCreatorFragment = ContentCreatorFragment()
    private var adapter = ProfilesAdapter(Profiles.profiles) {
        when (it.title) {
            Constant.CONTENT_CREATOR -> {
                replaceCurrentFragment.invoke(
                    contentCreatorFragment,
                    R.id.creatorMenuItem,
                    false
                )
            }
            Constant.VIDEO_EDITOR -> {
                replaceCurrentFragment.invoke(videoEditorFragment, R.id.editorMenuItem, false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.profileRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        replaceCurrentFragment.invoke(this,R.id.homeMenuItem,true)
    }
    override fun getLayoutID(): Int = R.layout.fragment_select_profile
}