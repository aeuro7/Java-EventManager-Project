package cs211.project.models.chats;

import java.util.ArrayList;

public class Chat {
    private ArrayList<Message> messageList;
    private String eventID;

    public Chat() {
        messageList = new ArrayList<>();
    }
    public void addChat(String sendername, String text) {
        Message newchat = new Message(sendername, text);
        messageList.add(newchat);
    }

    public void addChat(Message message) {
        messageList.add(message);
    }
    public String getEventID() {
        return eventID;
    }

    public ArrayList<Message> getChatlist() {
        return messageList;
    }
}