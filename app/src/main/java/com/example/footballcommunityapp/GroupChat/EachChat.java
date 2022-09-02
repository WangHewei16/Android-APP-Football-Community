package com.example.footballcommunityapp.GroupChat;

public class EachChat {
    private String chatContent;
    private int chatId;
    private String chatUsername;
    private String time;

    public EachChat(String chatContent, int chatId, String chatUsername,String time){
        this.chatContent = chatContent;
        this.chatId = chatId;
        this.chatUsername = chatUsername;
        this.time = time;
    }

    public String getChatContent() {
        return chatContent;
    }

    public int getChatId() {
        return chatId;
    }

    public String getChatUsername() {
        return chatUsername;
    }

    public String getTime(){
        return time;
    }
}
