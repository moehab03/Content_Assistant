package com.route.aigeneration.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.aigeneration.databinding.ProfileCardDesignBinding
import com.route.aigeneration.models.Profiles

class ProfilesAdapter(
    private val profiles: ArrayList<Profiles>,
    private val onItemSelect: (Profiles) -> Unit
) :
    Adapter<ProfilesAdapter.ProfilesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        val binding =
            ProfileCardDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfilesViewHolder(binding)
    }

    override fun getItemCount(): Int = profiles.size

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
        val profile = profiles[position]
        holder.bind(profile)
    }

    inner class ProfilesViewHolder(private val binding: ProfileCardDesignBinding) :
        ViewHolder(binding.root) {
        fun bind(profile: Profiles) {
            binding.apply {
                profileTitle.text = profile.title
                profileDetails.text = profile.details
                profileImage.setImageResource(profile.background)
                layout.setOnClickListener {
                    onItemSelect.invoke(profile)
                }
            }
        }
    }
}