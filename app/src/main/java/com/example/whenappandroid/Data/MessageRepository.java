package com.example.whenappandroid.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageRepository {
    private ServerAPI api;
    private MessageDao messageDao;
    private ContactDao contactDao;

    public MessageRepository(Application application) {
        AppDB db = AppDB.getDatabase(application);
        messageDao = db.messageDao();
        contactDao = db.contactDao();

        api = RetrofitService.getAPI(Globals.getServerAndroid());
    }

    public LiveData<List<Message>> getMessages(String with) {
        return messageDao.getMessages(with);
    }

    public void addMessage(String from, Contact to, String content) {
        api.addMessage(to.getId(), new ServerAPI.MessagePayload(content)).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.code() == 200) {
                    Message message = response.body();
                    message.setContact(to.getId());
                    messageDao.insert(message);

                    to.setLast(message.getContent());
                    to.setLastdate(message.getCreated());

                    contactDao.update(to);
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                t.printStackTrace();
            }
        });

        String otherServer = to.getServer().replace("localhost", "10.0.2.2");
        ServerAPI otherApi = RetrofitService.getAPI(otherServer);
        otherApi.transfer(new ServerAPI.TransferPayload(from, to.getId(), content)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
