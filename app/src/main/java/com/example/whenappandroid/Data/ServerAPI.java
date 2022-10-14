package com.example.whenappandroid.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ServerAPI {

    class UtilsPayload {
        public String username;
        public String password;

        public UtilsPayload(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    @POST("api/Login")
    Call<String> login(@Body UtilsPayload payload);

    @POST("api/Register")
    Call<String> register(@Body UtilsPayload payload);

    @GET("api/contacts")
    Call<List<Contact>> getContacts();

    class ContactPayload {
        public String id;
        public String name;
        public String server;

        public ContactPayload(String id, String name, String server) {
            this.id = id;
            this.name = name;
            this.server = server;
        }
    }

    @POST("api/contacts")
    Call<Contact> addContact(@Body ContactPayload payload);

    @GET("api/contacts/{id}/messages")
    Call<List<Message>> getMessages(@Path("id") String id);

    class MessagePayload {
        public String content;

        public MessagePayload(String content) {
            this.content = content;
        }
    }

    @POST("api/contacts/{id}/messages")
    Call<Message> addMessage(@Path("id") String id, @Body MessagePayload payload);

    class TransferPayload {
        public String from;
        public String to;
        public String content;

        public TransferPayload(String from, String to, String content) {
            this.from = from;
            this.to = to;
            this.content = content;
        }
    }

    @POST("api/transfer")
    Call<Void> transfer(@Body TransferPayload payload);

    class InvitationsPayload {
        public String from;
        public String to;
        public String server;

        public InvitationsPayload(String from, String to, String server) {
            this.from = from;
            this.to = to;
            this.server = server;
        }
    }

    @POST("api/invitations")
    Call<Void> invitations(@Body InvitationsPayload payload);

    class RegisterTokenPayload {
        public String username;
        public String token;

        public RegisterTokenPayload(String username, String token) {
            this.username = username;
            this.token = token;
        }
    }

    @POST("api/registertoken")
    Call<Void> registerToken(@Body RegisterTokenPayload payload);
}
