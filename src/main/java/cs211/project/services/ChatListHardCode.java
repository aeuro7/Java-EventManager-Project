package cs211.project.services;

import cs211.project.models.chats.Chat;
import cs211.project.models.chats.ChatList;
import cs211.project.models.chats.Message;

import java.io.*;
import java.util.List;

public class ChatListHardCode implements DataSource<ChatList> {
    private String fileDirectoryName;
    private String fileName;

    public ChatListHardCode(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
    }

    @Override
    public ChatList readData() {
        Chat chatA = new Chat();
        chatA.addChat("admin", "now I'm gonna get apple");
        chatA.addChat("admin2", "now I'm gonna get air-pods");
        Chat chatB = new Chat();
        chatB.addChat("Bank", "hello, bro!");
        chatB.addChat("Jane", "Hi, Bank");
        ChatList chatList = new ChatList();
        chatA.setName("Test1");
        chatB.setName("Test2");
        chatList.addChat(chatA);
        chatList.addChat(chatB);
        return chatList;
    }

    @Override
    public void writeData(ChatList chatList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Chat chat : chatList.getChatList()) {
                // Write chat information
                String chatInfo = chat.getEventID() + ","
                        + chat.getEventName();
                bufferedWriter.write(chatInfo);
                bufferedWriter.newLine();

                // Write messages for the chat
                for (Message message : chat.getChatlist()) {
                    String line = chat.getEventID() + ","
                            + message.getSenderName() + ","
                            + message.getSentDateTime() + ","
                            + message.getMessage();
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
