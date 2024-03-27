package com.route.aigeneration.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.aigeneration.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {
    lateinit var binding: SettingsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}