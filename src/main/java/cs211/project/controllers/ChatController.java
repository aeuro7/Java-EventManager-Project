package cs211.project.controllers;

import cs211.project.models.chats.Chat;
import cs211.project.models.chats.ChatList;
import cs211.project.models.chats.Message;
import cs211.project.services.ChatListDataSource;
import cs211.project.services.DataSource;
import cs211.project.services.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ChatController {

    @FXML private TableView chatListTableView;
    @FXML private ListView<Label> chatListview;
    @FXML private TextArea messageTextArea;
    @FXML private Label selectLabel;
    @FXML private Button sendButton;

    private DataSource<ChatList> dataSource = new ChatListDataSource("data", "chat.csv");
    private ChatList chatList;
    private Chat selectChat;
    private Message message;
    private String account = (String) FXRouter.getData();
    @FXML private void initialize() {
        chatList = dataSource.readData();
        clearChat();
        showTable(chatList);
        chatListTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Chat>() {
            @Override
            public void changed(ObservableValue observable, Chat oldValue, Chat newValue) {
                if (newValue != null) {
                    selectChat(newValue);
                }
            }
        });
    }

    private void showTable(ChatList chatList) {
        TableColumn<Chat, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        chatListTableView.getColumns().clear();
        chatListTableView.getColumns().add(eventNameColumn);
        eventNameColumn.setMinWidth(198);

        chatListTableView.getItems().clear();

        for (Chat chat: chatList.getChatList()) {
            chatListTableView.getItems().add(chat);
        }
    }

    private void clearChat() {
        selectLabel.setText("Chat List");
        chatListview.setVisible(false);
        messageTextArea.setVisible(false);
        sendButton.setVisible(false);
    }

    private void selectChat(Chat chat) {
        selectChat = chat;
        selectLabel.setText(chat.getEventName());
        chatListview.getItems().clear();
        chatListview.setVisible(true);
        messageTextArea.setVisible(true);
        sendButton.setVisible(true);
        setTextOnListView();
    }

    private void setTextOnListView() {
        chatListview.getItems().clear();

        for (Message message : selectChat.getChatlist()) {
            String sender = message.getSenderName();
            String text = message.getMessage();
            Label label = new Label(sender + ":\n" + text);

            if (account.equals(sender)) {
                label.setAlignment(Pos.CENTER_RIGHT);
                label.setStyle("-fx-background-color: #D3E1E1; -fx-padding: 5px;");
            } else {
                label.setAlignment(Pos.CENTER_LEFT);
                label.setStyle("-fx-background-color: #E1D3D3; -fx-padding: 5px;");
            }

            chatListview.getItems().add(label);
        }
    }

    public void sendButton() {
        String messageText = messageTextArea.getText();
        if (!messageText.isEmpty()) {
            Message newMessage = new Message(account, messageText);
            selectChat.addChat(newMessage);
            setTextOnListView();
            messageTextArea.clear();
            dataSource.writeData(chatList);
        }
    }
    public void goLogout() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goProflie() {
        try {
            FXRouter.goTo("profile-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goCalendar() {
        try {
            FXRouter.goTo("calendar-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goMain() {
        try {
            FXRouter.goTo("main-menu", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goChat() {
        try {
            FXRouter.goTo("chat-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
