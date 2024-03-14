package com.route.aigeneration.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.route.aigeneration.utils.Constant
import com.route.aigeneration.R
import com.route.aigeneration.databinding.ActivityHomeBinding
import com.route.aigeneration.ui.fragments.ContentCreatorFragment
import com.route.aigeneration.ui.fragments.SelectProfileFragment
import com.route.aigeneration.ui.fragments.VideoEditorFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var isGuest: Boolean = false
    private var homeItemState = true
    private val selectProfileFragment = SelectProfileFragment(
        { fragment, itemID, homeState ->
            replaceFragment(fragment)
            changeMenuItemSelected(itemID)
            changeHomeItemState(homeState)
        }, {
            changeBottomBarVisibility(it)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isGuest = intent.getBooleanExtra(Constant.GUEST, false)
        startApp()
        onBackClick()
        onMenuItemSelected()
        isGuest()
    }

    private fun isGuest() {
        if (!isGuest) {
            startFragment(selectProfileFragment)
            binding.startScreen.isVisible = false
            binding.mainScreen.isVisible = true
        }
    }

    private fun onBackClick() {
        binding.backArrow.setOnClickListener {
            supportFragmentManager.popBackStack()
            if (!binding.bottomBar.isVisible)
                changeBottomBarVisibility(true)
            if (!homeItemState)
                changeMenuItemSelected(R.id.homeMenuItem)
        }
    }

    private fun startApp() {
        binding.getStartedBtn.setOnClickListener {
            startFragment(selectProfileFragment)
            binding.startScreen.isVisible = false
            binding.mainScreen.isVisible = true
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack("")
            .commit()
    }

    private fun changeMenuItemSelected(itemID: Int) {
        binding.bottomBar.selectedItemId = itemID
    }

    private fun onMenuItemSelected() {
        binding.bottomBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeMenuItem -> {
                    replaceFragment(selectProfileFragment)
                }

                R.id.creatorMenuItem -> {
                    replaceFragment(ContentCreatorFragment({ fragment ->
                        replaceFragment(fragment)
                    }, { state ->
                        changeBottomBarVisibility(state)
                    }))
                }

                R.id.editorMenuItem -> {
                    replaceFragment(VideoEditorFragment({ fragment ->
                        replaceFragment(fragment)
                    }, { state ->
                        changeBottomBarVisibility(state)
                    }))
                }

                R.id.settingsMenuItem -> {

                }
            }
            true
        }
    }

    private fun changeHomeItemState(state: Boolean) {
        homeItemState = state
    }

    private fun changeBottomBarVisibility(state: Boolean) {
        binding.bottomBar.isVisible = state
    }
}