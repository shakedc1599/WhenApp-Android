package com.example.whenappandroid.ChatScreen.ContactList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whenappandroid.Data.Contact;
import com.example.whenappandroid.Data.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private String currentUser = "noam";
    private ContactRepository repository;

    private final LiveData<List<Contact>> allContacts;

    public ContactViewModel(Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    // null if ok, error text if there is an error
    public String addContact(String username, String nickname, String server) {
        if(username.equals(currentUser)){
            return "cant add yourself";
        }

        if(allContacts.getValue() != null){
            for(Contact c : allContacts.getValue()){
                if(c.getId().equals(username)) return "already in";
            }
        }

        repository.addContact(currentUser, username, nickname, server);
        return null;
    }
}
