package com.route.aigeneration.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.aigeneration.utils.Constant
import com.route.aigeneration.R
import com.route.aigeneration.adapters.ProfilesAdapter
import com.route.aigeneration.databinding.FragmentSelectProfileBinding
import com.route.aigeneration.models.Profiles


class SelectProfileFragment(
    private val replaceCurrentFragment:
        (
        fragment: Fragment,
        itemID: Int, homeItemState: Boolean
    ) -> Unit,
    private val changeAppBarVisibility: (state: Boolean) -> Unit
) :
    Fragment() {

    private lateinit var binding: FragmentSelectProfileBinding
    private val videoEditorFragment = VideoEditorFragment({ fragment ->
        replaceCurrentFragment.invoke(fragment, R.id.homeMenuItem, true)
    }, {
        changeAppBarVisibility.invoke(it)
    })
    private val contentCreatorFragment = ContentCreatorFragment({ fragment ->
        replaceCurrentFragment.invoke(fragment, R.id.homeMenuItem, true)
    }, {
        changeAppBarVisibility.invoke(it)
    })

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        changeAppBarVisibility.invoke(true)
    }

    private fun initRecyclerView() {
        binding.profileRecyclerView.adapter = adapter
    }

}