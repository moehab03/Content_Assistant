package com.route.aigeneration.models

import com.route.aigeneration.utils.Constant
import com.route.aigeneration.R

data class Profiles(
    val background: Int,
    val title: String,
    val details: String
) {
    companion object {
        val profiles = arrayListOf(
            Profiles(
                R.drawable.creator_img, Constant.CONTENT_CREATOR,
                "Chat bot,Visual feed"
            ),
            Profiles(
                R.drawable.editor_img, Constant.VIDEO_EDITOR,
                "Music recommendation , Text to speech , Speech to text"
            )
        )
    }
}
