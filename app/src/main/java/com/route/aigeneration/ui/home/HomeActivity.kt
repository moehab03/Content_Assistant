package com.route.aigeneration.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.route.aigeneration.R
import com.route.aigeneration.databinding.ActivityHomeBinding
import com.route.aigeneration.ui.home.fragments.ContentCreatorFragment
import com.route.aigeneration.ui.home.fragments.SelectProfileFragment
import com.route.aigeneration.ui.home.fragments.VideoEditorFragment
import com.route.aigeneration.ui.profile.ProfileActivity
import com.route.aigeneration.ui.settings.SettingsActivity
import com.route.aigeneration.utils.Constant

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var isGuest: Boolean = false
    private var homeItemState = true
    private val selectProfileFragment = SelectProfileFragment { fragment, itemID, homeState ->
        replaceFragment(fragment)
        changeMenuItemSelected(itemID)
        changeHomeItemState(homeState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isGuest = intent.getBooleanExtra(Constant.GUEST, false)
        startApp()
        onMenuItemSelected()
        isGuest()
        openMenu()
        fabClick()
    }

    private fun isGuest() {
        if (!isGuest) {
            replaceFragment(selectProfileFragment)
            binding.welcomeScreen.root.isVisible = false
            binding.homeLayout.root.isVisible = true
        }
    }

    private fun openMenu() {
        binding.homeLayout.menuIcon.setOnClickListener {
            binding.drawerLayout.open()
        }
    }

    private fun startApp() {
        binding.welcomeScreen.getStartedBtn.setOnClickListener {
            replaceFragment(selectProfileFragment)
            binding.welcomeScreen.root.isVisible = false
            binding.homeLayout.root.isVisible = true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack("")
            .commit()
    }

    private fun changeMenuItemSelected(itemID: Int) {
        binding.homeLayout.bottomNavigation.selectedItemId = itemID
    }

    private fun onMenuItemSelected() {
        binding.homeLayout.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeMenuItem -> {
                    replaceFragment(selectProfileFragment)
                }

                R.id.creatorMenuItem -> {
                    replaceFragment(ContentCreatorFragment())
                }

                R.id.editorMenuItem -> {
                    replaceFragment(VideoEditorFragment())
                }

                R.id.settingsMenuItem -> {
                    openSettingsPage()
                }
            }
            true
        }
    }

    private fun openSettingsPage() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun changeHomeItemState(state: Boolean) {
        homeItemState = state
    }

    private fun fabClick() {
        binding.homeLayout.fab.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}