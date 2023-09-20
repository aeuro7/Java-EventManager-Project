package cs211.project.controllers;

import cs211.project.models.Calendar;
import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.chats.Chat;
import cs211.project.models.chats.ChatList;
import cs211.project.models.chats.Message;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatController {

    @FXML private TableView chatListTableView;
    @FXML private ListView<Label> chatListview;
    @FXML private TextArea messageTextArea;
    @FXML private Label selectLabel;
    @FXML private Button sendButton;

    private DataSource<ChatList> dataSource = new ChatListDataSource("data", "chat.csv");
    private ChatList chatList = new ChatList();
    private Chat selectChat;
    private EventList eventList = new EventList();
    private String account = (String) FXRouter.getData();

    ChatList fullchatList = dataSource.readData();
    @FXML private void initialize() {
        MemberList memberList = (new MemberDataSource("data", "member.csv").readData());
        TeamList teamList = (new TeamDataSource("data", "team.csv").readData());
        eventList = (new EventDataSource("data", "event.csv").readData());

        List<Pair> joinData = new ArrayList<>();

        for(Member member: memberList.getMemberList()) {
            if(member.getUsername().equals(account)) {
                joinData.add(new Pair(member.getEventID(), member.getRole()));
            }
        }
        for(Team team: teamList.getAllTeams()) {
            if(team.isInTeam(account)) {
                joinData.add(new Pair(team.getEventID(), team.getNameTeam()));
            }
        }

        for (Pair data : joinData) {
            for (Chat chat : fullchatList.getChatList()) {

                if(chat.getEventID().equals(data.getKey()) && data.getValue().equals("OWNER")) {
                    chatList.addChat(chat);
                }
                else if (chat.getEventID().equals(data.getKey()) && chat.getFaction().equals(data.getValue())) {
                    chatList.addChat(chat);
                }

            }
        }

        clearChat();
        showTable();
        chatListTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Chat>() {
            @Override
            public void changed(ObservableValue observable, Chat oldValue, Chat newValue) {
                if (newValue != null) {
                    selectChat(newValue);
                }
            }
        });
    }

    private void showTable() {
        TableColumn<Chat, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));

        eventNameColumn.setCellValueFactory(cellDate -> {
            Chat chat = cellDate.getValue();
            if(chat.getFaction().equals("AUDIENCE")) {
                Event event = eventList.findEventByID(chat.getEventID());
                return new ReadOnlyStringWrapper(event.getEventName());
            }
            return new ReadOnlyStringWrapper(chat.getFaction());

        });

        chatListTableView.getColumns().clear();
        chatListTableView.getColumns().add(eventNameColumn);

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
        selectLabel.setText(chat.getFaction());
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
            dataSource.writeData(fullchatList);
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