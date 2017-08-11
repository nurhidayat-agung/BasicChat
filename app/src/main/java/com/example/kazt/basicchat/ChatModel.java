package com.example.kazt.basicchat;

import java.io.Serializable;

/**
 * Created by kazt on 12/08/17.
 */

public class ChatModel implements Serializable {
    private String username;
    private String content;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
