package com.route.aigeneration.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.route.aigeneration.databinding.ActivitySignUpBinding
import com.route.aigeneration.ui.home.HomeActivity

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private var auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signUp()
    }

    private fun signUp() {
        binding.logInBtn.setOnClickListener {
            if (!isValid())
                return@setOnClickListener

            try {
                auth.createUserWithEmailAndPassword(
                    binding.emailET.editText!!.text.toString(),
                    binding.passwordET.editText!!.text.toString()
                )
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun isValid(): Boolean {
        var isValid = true
        binding.apply {
            if (emailET.editText!!.text.isEmpty()) {
                emailET.error = "Enter your email please"
                isValid = false
            } else
                emailET.error = null

            if (passwordET.editText!!.text.isEmpty()) {
                passwordET.error = "Enter password"
                isValid = false
            } else
                passwordET.error = null

            if (userNameET.editText!!.text.isEmpty()) {
                userNameET.error = "Enter password"
                isValid = false
            } else
                userNameET.error = null
        }
        return isValid
    }
}