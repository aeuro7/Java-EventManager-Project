package cs211.project.models.chats;

import java.util.ArrayList;

public class ChatList {
    private ArrayList<Chat> chatList;

    public ChatList() {
        chatList = new ArrayList<>();
    }

    public void addChat(Chat chat) {
        chatList.add(chat);
    }

    public ArrayList<Chat> getChatList() {return chatList;}
}
