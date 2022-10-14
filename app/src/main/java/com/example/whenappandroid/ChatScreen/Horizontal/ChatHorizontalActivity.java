package com.example.whenappandroid.ChatScreen.Horizontal;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whenappandroid.ChatScreen.ContactList.ContactListAdapter;
import com.example.whenappandroid.ChatScreen.ContactList.ContactViewModel;
import com.example.whenappandroid.ChatScreen.MessageList.MessageListAdapter;
import com.example.whenappandroid.ChatScreen.MessageList.MessageViewModel;
import com.example.whenappandroid.ChatScreen.Vertical.VerticalContactsActivity;
import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.Globals;
import com.example.whenappandroid.databinding.ActivityChatHorizontalBinding;

import java.util.List;

public class ChatHorizontalActivity extends AppCompatActivity {
    private ActivityChatHorizontalBinding binding;

    private MessageViewModel viewModel;
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatHorizontalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);


        ContactListAdapter adapter = new ContactListAdapter(new ContactListAdapter.ContactDiff());
        MessageListAdapter messageAdapter = new MessageListAdapter();
        binding.messagesList.setAdapter(messageAdapter);
        binding.messagesList.setLayoutManager(new LinearLayoutManager(this));

        binding.contactList.setAdapter(adapter);
        binding.contactList.setLayoutManager(new LinearLayoutManager(this));

        adapter.setItemClickListener((v, contact) -> {
            currentContact = contact;

            binding.contactName.setText(contact.getName());


            viewModel.getMessages(contact).observe(this, list -> {
                messageAdapter.submitList(list);
                binding.messagesList.scrollToPosition(messageAdapter.getItemCount() - 1);
                Log.d("custom", "observed");
            });

            binding.textInputMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || actionId == EditorInfo.IME_ACTION_GO) {
                        sendMessage();
                        return true;
                    }
                    return false;
                }
            });
        });

        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactViewModel.getAllContacts().observe(this, (List<Contact> list) -> {
            adapter.submitList(list);
            adapter.notifyDataSetChanged();
        });


    }

    private void sendMessage() {
        String from = Globals.currentUser;
        String content = binding.textInputMessage.getText().toString();
        if (content.isEmpty()) {
            return;
        }
        viewModel.addMessage(from, currentContact, content);

        binding.textInputMessage.setText("");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            startActivity(new Intent(this, VerticalContactsActivity.class));
        }
    }
}