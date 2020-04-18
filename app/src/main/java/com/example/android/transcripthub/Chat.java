package com.example.android.transcripthub;

public class  Chat {

    public String timestamp;
    public String chat;
    public String type;
    public String number;


    public Chat() {
    }

    public Chat(String timestamp, String chat, String type, String number) {
        this.timestamp = timestamp;
        this.chat = chat;
        this.type = type;
        this.number = number;
    }
}