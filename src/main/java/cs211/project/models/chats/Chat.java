package cs211.project.models.chats;

import java.util.ArrayList;

public class Chat {
    private ArrayList<Message> messageList;
    private String eventID;
    private String faction;

    public Chat() {
        messageList = new ArrayList<>();
    }
    public Chat(String eventID, String faction) {
        this();
        this.eventID = eventID;
        this.faction = faction;
    }

    public void addChat(Message message) {
        messageList.add(message);
    }
    public String getEventID() {
        return eventID;
    }
    public String getFaction() {
        return faction;
    }
    public ArrayList<Message> getChatlist() {
        return messageList;
    }
}