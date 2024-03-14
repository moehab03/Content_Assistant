package com.route.aigeneration.ui.fragments.image

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.route.aigeneration.R
import com.route.aigeneration.api.models.ImageGenResponse
import com.route.aigeneration.databinding.FragmentImageGenerationBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class ImageGenerationFragment(
    private val changeAppBarVisibility: (state: Boolean) -> Unit
) : Fragment() {
    private lateinit var binding: FragmentImageGenerationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageGenerationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeAppBarVisibility.invoke(false)
        generateClick()
        editTextChange()
    }

    private fun sendTextToApi(text: String) {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val requestBody = FormBody.Builder()
            .add("text", text)
            .build()

        val request = Request.Builder()
            .url("http://192.168.1.10:5000/generate_image")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body
                    val responseData = responseBody?.string()
                    activity?.runOnUiThread {
                        val result =
                            Gson().fromJson(responseData, ImageGenResponse::class.java)
                        startFragment(result.imageData)
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("MainActivity", "Error: ${response.code}")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
                Log.e("MainActivity", "API call failed", e)
            }
        })
    }

    private fun startFragment(imageData: String?) {
        val fragment = GeneratedImageFragment()
        val bundle = Bundle()
        bundle.putString("Image_Data", imageData)
        fragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.fragmentContainer,
            fragment
        ) // Replace R.id.fragment_container with your actual container ID
        transaction.addToBackStack(null) // If you want to add the transaction to the back stack
        transaction.commit()

        Log.println(Log.WARN, "test", imageData.toString())
    }

    private fun generateClick() {
        binding.btnSendText.setOnClickListener {
            if (editTextValidation()) {
                sendTextToApi(binding.generateTextET.editText!!.text.toString())
            }
        }
    }

    private fun editTextValidation(): Boolean {
        return if (binding.generateTextET.editText!!.text.isNotEmpty())
            true
        else {
            binding.generateTextET.error = "Enter your imagination about image to generate it"
            false
        }
    }

    private fun editTextChange() {
        binding.generateTextET.editText!!.addTextChangedListener {
            binding.generateTextET.error = null
        }
    }

}
