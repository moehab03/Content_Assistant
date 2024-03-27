package com.route.aigeneration.ui.ai_models

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.aigeneration.R
import com.route.aigeneration.databinding.ActivityModelsBinding
import com.route.aigeneration.ui.ai_models.fragments.idea.IdeaGenerationFragment
import com.route.aigeneration.ui.ai_models.fragments.image.ImageGenerationFragment
import com.route.aigeneration.ui.ai_models.fragments.music.MusicRecommendationFragment
import com.route.aigeneration.ui.ai_models.fragments.speech.SpeechToTextFragment
import com.route.aigeneration.ui.ai_models.fragments.voice.VoiceGenerationFragment
import com.route.aigeneration.utils.Constant

class ModelsActivity : AppCompatActivity() {
    lateinit var binding: ActivityModelsBinding
    private var model: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = intent.getStringExtra(Constant.MODEL)!!
        onBackClick()
        openModelFragment()
    }

    private fun openModelFragment() {
        when (model) {
            Constant.IMAGE_GENERATION -> {
                startFragment(ImageGenerationFragment())
            }

            Constant.IDEA_GENERATION -> {
                startFragment(IdeaGenerationFragment())
            }

            Constant.MUSIC_RECOMMENDATION -> {
                startFragment(MusicRecommendationFragment())
            }

            Constant.SPEECH_TO_TEXT -> {
                startFragment(SpeechToTextFragment())
            }

            Constant.VOICE_GENERATION -> {
                startFragment(VoiceGenerationFragment())
            }
        }
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.modelFragmentContainer, fragment)
            .commit()
    }

    private fun onBackClick() {
        binding.backArrow.setOnClickListener {
            finish()
        }
    }
}