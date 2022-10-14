package com.example.whenappandroid.ChatScreen.Vertical;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whenappandroid.ChatScreen.MessageList.MessageListAdapter;
import com.example.whenappandroid.ChatScreen.MessageList.MessageViewModel;
import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.Globals;
import com.example.whenappandroid.R;
import com.example.whenappandroid.SettingsScreen.SettingsActivity;
import com.example.whenappandroid.databinding.ActivityVerticalMessagesBinding;

public class VerticalMessagesActivity extends AppCompatActivity {

    private ActivityVerticalMessagesBinding binding;
    private MessageViewModel viewModel;
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerticalMessagesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        currentContact = (Contact) getIntent().getSerializableExtra("contact");

        MessageListAdapter adapter = new MessageListAdapter();
        binding.messagesList.setAdapter(adapter);
        binding.messagesList.setLayoutManager(new LinearLayoutManager(this));
        binding.contactName.setText(currentContact.getName());

        viewModel.getMessages(currentContact).observe(this, list -> {
            adapter.submitList(list);
            binding.messagesList.scrollToPosition(adapter.getItemCount() - 1);
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

        binding.buttonGchatSend.setOnClickListener(v -> {
            sendMessage();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
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
}