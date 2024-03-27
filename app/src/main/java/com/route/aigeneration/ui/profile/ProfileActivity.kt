package com.route.aigeneration.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.aigeneration.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}