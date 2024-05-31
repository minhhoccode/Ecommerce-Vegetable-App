// ChatMessage.java
package com.vungocminh.doanandroid;

public class ChatMessage {
    public static final int USER_MESSAGE = 0;
    public static final int BOT_MESSAGE = 1;

    private String message;
    private int senderType;

    public ChatMessage(String message, int senderType) {
        this.message = message;
        this.senderType = senderType;
    }

    public String getMessage() {
        return message;
    }

    public int getSenderType() {
        return senderType;
    }
}