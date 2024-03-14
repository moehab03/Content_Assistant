package com.route.aigeneration.api
import com.route.aigeneration.api.models.ApiResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("generate_image/sendText")
    suspend fun sendText(@Body requestBody: RequestBody): Call<ApiResponse>

    @GET("generate_image/getImage")
    suspend fun getImage(): Call<ResponseBody>
}
