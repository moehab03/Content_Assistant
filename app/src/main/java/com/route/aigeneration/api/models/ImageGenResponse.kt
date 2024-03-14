package com.route.aigeneration.api.models

import com.google.gson.annotations.SerializedName

data class ImageGenResponse(
    @SerializedName("image_data")
    var imageData: String? = null
)
