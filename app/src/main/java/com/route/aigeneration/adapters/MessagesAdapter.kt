package com.route.aigeneration.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.aigeneration.utils.Constant
import com.route.aigeneration.databinding.MessageLayoutBinding
import com.route.aigeneration.models.Messages


class MessagesAdapter(private var messages: ArrayList<Messages>) :
    Adapter<MessagesAdapter.MessagesViewHolder>() {

    fun updateMessages(messages: ArrayList<Messages>) {
        this.messages = messages
        notifyItemChanged(this.messages.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessagesAdapter.MessagesViewHolder {
        val binding =
            MessageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessagesAdapter.MessagesViewHolder, position: Int) {
        val message = messages[position]
        when (message.id) {
            Constant.SEND_ID -> {
                holder.binding.messageText.apply {
                    text = message.message
                    visibility = View.VISIBLE
                }
                holder.binding.botMessage.visibility = View.GONE
            }

            Constant.RECEIVE_ID -> {
                holder.binding.botMessage.apply {
                    text = message.message
                    visibility = View.VISIBLE
                }
                holder.binding.messageText.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = messages.size

    inner class MessagesViewHolder(val binding: MessageLayoutBinding) : ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {

                // Remove message on the item clicked
                messages.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    fun insertMessage(message: Messages) {
        messages.add(message)
        notifyItemInserted(itemCount - 1)

    }
}

