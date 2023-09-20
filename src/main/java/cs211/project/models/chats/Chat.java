package cs211.project.models.chats;

import java.util.ArrayList;

public class Chat {
    private ArrayList<Message> messageList;
    private String eventID;
    private String eventName;

    public Chat() {
        messageList = new ArrayList<>();
    }
    public Chat(String eventID, String name) {
        this();
        this.eventID = eventID;
        this.eventName = name;
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
    public String getEventName() {
        return eventName;
    }
    public void setName(String name) {
        this.eventName = name;
    }
    public void setID(String id) {
        this.eventID = id;
    }

    public ArrayList<Message> getChatlist() {
        return messageList;
    }
}