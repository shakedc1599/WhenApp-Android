package com.example.whenappandroid.ChatScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.whenappandroid.ChatScreen.ContactList.ContactViewModel;
import com.example.whenappandroid.R;
import com.example.whenappandroid.SettingsScreen.SettingsActivity;
import com.example.whenappandroid.databinding.ActivityAddContactBinding;

public class AddContactActivity extends AppCompatActivity {
    private ActivityAddContactBinding binding;
    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        binding.addBtn.setOnClickListener(v -> {
            String username = binding.usernameInputBox.getText().toString();
            String nickname = binding.nicknameInputBox.getText().toString();
            String server = binding.serverInputBox.getText().toString();

            String added = viewModel.addContact(username, nickname, server);

            if (added == null) {
                finish();
            }

            binding.errorText.setText(added);
        });

        binding.cancelBtn.setOnClickListener(v -> finish());
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
}