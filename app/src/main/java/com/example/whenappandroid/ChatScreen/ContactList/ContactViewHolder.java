package com.example.whenappandroid.ChatScreen.ContactList;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.databinding.RecyclerviewContactBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

class ContactViewHolder extends RecyclerView.ViewHolder {
    private final RecyclerviewContactBinding binding;

    public ContactViewHolder(RecyclerviewContactBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Contact contact) {
        binding.lastMessage.setText(contact.getLast());
        binding.contactName.setText(contact.getName());
        String messageISO = contact.getLastdate();

        if(messageISO == null){
            binding.lastDate.setText("");
            return;
        }

        binding.lastDate.setText(messageISO.substring(11, 16));
    }
}