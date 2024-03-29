package com.route.aigeneration.ui.fragments.idea

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import com.route.aigeneration.utils.Constant
import com.route.aigeneration.adapters.MessagesAdapter
import com.route.aigeneration.databinding.FragmentIdeaGenerationBinding
import com.route.aigeneration.models.Messages
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class IdeaGenerationFragment(
    private val changeAppBarVisibility: (state: Boolean) -> Unit
) : Fragment() {
    private lateinit var binding: FragmentIdeaGenerationBinding
    private val adapter = MessagesAdapter(arrayListOf())
    private val messages = arrayListOf<Messages>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIdeaGenerationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        sendNewMessage()
        changeAppBarVisibility.invoke(false)
    }

    private fun initRecyclerView() {
        binding.messagesRecyclerView.adapter = adapter
    }

    private fun sendNewMessage() {
        binding.apply {
            sendBtn.setOnClickListener {
                if (messageETLayout.isEmpty()) {
                    messageETLayout.error = "Enter message"
                } else {
                    val message = Messages("${messageETLayout.editText!!.text}", Constant.SEND_ID)
                    messageETLayout.editText!!.setText("")
                    messages.add(message)
                    Log.println(Log.WARN, "", message.message)

                    adapter.updateMessages(messages)
                    sendTextToApi(message.message)
                }
            }

        }
    }

    private fun botNewMessage(text: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            val botMessage = Messages(text, Constant.RECEIVE_ID)
            adapter.insertMessage(botMessage)
        }, 1000)
    }

    private fun sendTextToApi(text: String) {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val requestBody = FormBody.Builder()
            .add("text", text)
            .build()

        val request = Request.Builder()
            .url("http://192.168.1.6:5000/generate_response")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body
                    val responseData = responseBody?.string()
                    val result = responseData.toString()
                    Log.println(Log.WARN, "aqz", result)
                    botNewMessage(result)
                } else {
                    // Handle unsuccessful response
                    Log.e("MainActivity", "Error: ${response.code}")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
                Log.e("MainActivity", "API call failed : $e")
            }
        })
    }
}
