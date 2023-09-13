package cs211.project.services;

import cs211.project.models.chats.Chat;
import cs211.project.models.chats.ChatList;

public class ChatListHardCode implements DataSource{
    @Override
    public Object readData() {
        Chat chatA = new Chat();
        chatA.addChat("admin", "now i'm gonna get apple");
        chatA.addChat("admin2", "now i'm gonna get aie-pods");
        Chat chatB = new Chat();
        chatB.addChat("Bank", "hello, bro!");
        chatB.addChat("Jane", "Hi, Bank");
        ChatList chatList = new ChatList();
        chatList.addChat(chatA);
        chatList.addChat(chatB);
        return chatList;
    }

    @Override
    public void writeData(Object o) {

    }
}
