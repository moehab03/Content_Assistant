package com.route.aigeneration.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.aigeneration.utils.Constant
import com.route.aigeneration.databinding.ActivityStartBinding


class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginBtnClick()
        guestBtnClick()
    }

    private fun loginBtnClick() {
        binding.loginAndSubscribeBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun guestBtnClick() {
        binding.guestBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra(Constant.GUEST, true)
            startActivity(intent)
            auth.signInAnonymously()
        }
    }

}