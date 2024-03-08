package com.example.appchat.Model;

public class Message {
    private String content;
    private boolean sentByCurrentUser;

    public Message() {

    }
    public Message(String content, boolean sentByCurrentUser) {
        this.content = content;
        this.sentByCurrentUser = sentByCurrentUser;
    }

    public String getContent() {
        return content;
    }

    public boolean isSentByCurrentUser() {
        return sentByCurrentUser;
    }
}