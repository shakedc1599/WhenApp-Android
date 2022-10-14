package com.example.whenappandroid.ChatScreen.MessageList;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.databinding.RecycleMessagesByMeBinding;
import com.example.whenappandroid.databinding.RecycleMessagesByOtherBinding;

public class MessageOutViewHolder extends RecyclerView.ViewHolder{
    private RecycleMessagesByOtherBinding binding;

    public MessageOutViewHolder(RecycleMessagesByOtherBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void bind(Message message) {
//        binding.setText(message.getContent());
//        binding.textGchatDateOther.setText(message.getCreated());
        binding.textGchatMessageMe.setText(message.getContent());
        binding.textGchatTimestampMe.setText(message.getCreated().substring(11, 16));
    }
}
