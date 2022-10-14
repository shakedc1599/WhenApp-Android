package com.example.whenappandroid.ChatScreen.MessageList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.Message;
import com.example.whenappandroid.Data.MessageRepository;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    public MessageRepository repository;
    // LiveData<List<Message>> messages;

    public MessageViewModel(Application application) {
        super(application);
        repository = new MessageRepository(application);
    }

    public LiveData<List<Message>> getMessages(Contact currentContact) {
        return repository.getMessages(currentContact.getId());
    }

    public void addMessage(String from, Contact to, String content) {
        repository.addMessage(from, to, content);
    }
}
