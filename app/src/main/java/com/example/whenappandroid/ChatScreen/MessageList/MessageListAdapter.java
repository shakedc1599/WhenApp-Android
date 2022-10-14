package com.example.whenappandroid.ChatScreen.MessageList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.databinding.RecycleMessagesByMeBinding;
import com.example.whenappandroid.databinding.RecycleMessagesByOtherBinding;
import com.example.whenappandroid.databinding.RecyclerviewContactBinding;

import java.util.ArrayList;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messageList = new ArrayList<>();
    public static final int MESSAGE_TYPE_IN = 1;
    public static final int MESSAGE_TYPE_OUT = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MESSAGE_TYPE_IN) {
            @NonNull RecycleMessagesByMeBinding binding = RecycleMessagesByMeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new MessageInViewHolder(binding);
        }
        @NonNull RecycleMessagesByOtherBinding binding = RecycleMessagesByOtherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MessageOutViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (messageList.get(position).isSent()) {
            ((MessageInViewHolder) holder).bind(messageList.get(position));
        } else {
            ((MessageOutViewHolder) holder).bind(messageList.get(position));
        }
    }

    public void submitList(List<Message> m) {
        messageList = m;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).isSent()) {
            return MESSAGE_TYPE_IN;
        }
        return MESSAGE_TYPE_OUT;
    }

    static public class MessageDiff extends DiffUtil.ItemCallback<Message> {

        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    }
}
