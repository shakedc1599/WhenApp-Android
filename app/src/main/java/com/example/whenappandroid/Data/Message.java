package com.example.whenappandroid.Data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey
    private int id;
    private String contact;
    private String content;
    private String created;
    private boolean sent;

    public Message(int id, String contact, String content, String created, boolean sent) {
        this.id = id;
        this.contact = contact;
        this.content = content;
        this.created = created;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @NonNull
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", contact='" + contact + '\'' +
                ", content='" + content + '\'' +
                ", created='" + created + '\'' +
                ", sent=" + sent +
                '}';
    }
}

