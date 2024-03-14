package com.route.aigeneration.ui.fragments.image

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.route.aigeneration.api.models.ImageGenResponse
import com.route.aigeneration.databinding.FragmentGeneratedImageBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


class GeneratedImageFragment : Fragment() {
    private lateinit var binding: FragmentGeneratedImageBinding
    private lateinit var bitmap: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeneratedImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showImage(getImageData())
        bitmap = binding.generatedImage.drawable.toBitmap()
        regenerateImage()
        downloadImageToDevice()
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        @Suppress("DEPRECATION")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with saving the image
                saveImageToGallery()
            } else {
                // Handle permission denied case (e.g., show a toast)
            }
        }
    }

    private fun getImageData(): String? {
        return arguments?.getString("Image_Data")
    }


    private fun regenerateImage() {
        binding.regenerateBtn.setOnClickListener {
            if (textValidation())
                sendTextToApi(binding.regenerateTextET.editText!!.text.toString())
        }
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
                        showImage(result.imageData)
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

    private fun showImage(imageData: String?) {
        val decodedBytes = imageData?.let {
            try {
                Base64.decode(it, Base64.DEFAULT)
            } catch (e: IllegalArgumentException) {
                Log.e("test", "Decoding error: ${e.message}")
                null
            }
        }

        decodedBytes?.let {
            Log.d("test", "Decoded bytes size: ${it.size}")
            val bitmap: Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            Log.d("test", "Bitmap dimensions: ${bitmap.width} x ${bitmap.height}")
            binding.generatedImage.setImageBitmap(bitmap)
        } ?: run {
            Log.e("test", "Null or decoding error in imageData: $imageData")
            // Handle null or decoding error
        }
    }

    private fun downloadImageToDevice() {
        binding.downloadBtn.root.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            } else {
                // Permission already granted, proceed with saving the image
                saveImageToGallery()
            }
        }
    }

    private fun saveImageToGallery() {
        val filename = "${System.currentTimeMillis()}.jpg"  // Generate unique filename
        val resolver = requireActivity().contentResolver

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (imageUri != null) {
            try {
                val outputStream = resolver.openOutputStream(imageUri)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream!!)
                outputStream.close()
                Toast.makeText(requireContext(), "Image saved successfully!", Toast.LENGTH_SHORT)
                    .show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Failed to save image!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun textValidation(): Boolean {
        editTextChange()
        return if (binding.regenerateTextET.editText!!.text.isNotEmpty())
            true
        else {
            binding.regenerateTextET.error = "Enter your imagination about image to generate it"
            false
        }
    }

    private fun editTextChange() {
        binding.regenerateTextET.editText!!.addTextChangedListener {
            binding.regenerateTextET.error = null
        }
    }

}