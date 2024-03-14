package com.route.aigeneration.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.route.aigeneration.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signIn()
        signUp()
        signUpWithGoogle()
    }

    private fun signUpWithGoogle() {
        //TODO("Not yet implemented")
    }

    private fun signUp() {
        binding.registerBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun signIn() {
        binding.logInBtn.setOnClickListener {
            if (!validation())
                return@setOnClickListener

            try {
                auth.signInWithEmailAndPassword(
                    binding.emailET.editText!!.text.toString(),
                    binding.passwordET.editText!!.text.toString()
                )
                finish()
                startActivity(Intent(this, HomeActivity::class.java))
            } catch (e: Exception) {
                throw e
            }
        }
    }

    private fun validation(): Boolean {
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

        }
        return isValid
    }
}