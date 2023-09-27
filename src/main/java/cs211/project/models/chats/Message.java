package cs211.project.models.chats;

public class Message {
    private String senderName;
    private long sentDateTime;
    private String message;

    public Message(String senderName, String message){
        this.senderName = senderName;
        this.sentDateTime = System.currentTimeMillis();
        this.message = message;
    }
    public String getSenderName() {return senderName;}

    public long getSentDateTime() {return sentDateTime;}
    public void setSentDateTime(long timeStamp) { sentDateTime = timeStamp;}

    public String getMessage() {return message;}

}
