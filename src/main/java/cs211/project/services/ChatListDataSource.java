package cs211.project.services;

import cs211.project.models.chats.Chat;
import cs211.project.models.chats.ChatList;
import cs211.project.models.chats.Message;

import java.io.*;

public class ChatListDataSource implements DataSource<ChatList> {
    private String fileDirectoryName;
    private String fileName;

    public ChatListDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    @Override
    public ChatList readData() {
        ChatList chatList = new ChatList();
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            Chat currentChat = null;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    // New chat entry
                    currentChat = new Chat();
                    currentChat.setID(data[0]);
                    currentChat.setName(data[1]);
                    chatList.addChat(currentChat);
                } else if (data.length == 4 && currentChat != null) {
                    // Message entry
                    Message message = new Message(data[1], data[3]);
                    message.setSentDateTime(Long.parseLong(data[2]));
                    currentChat.addChat(message);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chatList;
    }

    @Override
    public void writeData(ChatList chatList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Chat chat : chatList.getChatList()) {
                String chatInfo = chat.getEventID() + ","
                        + chat.getEventName();
                bufferedWriter.write(chatInfo);
                bufferedWriter.newLine();
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
