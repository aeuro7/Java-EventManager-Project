package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.chats.Chat;
import cs211.project.models.chats.ChatList;
import cs211.project.models.eventHub.Member;
import cs211.project.models.eventHub.MemberList;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.services.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ManageEventController {
    private Event selectEvent;
    private String account;
    private DataSource<MemberList> memberDatasource;
    private DataSource<TeamList> teamDatasource;
    @FXML private Circle profilepicCircle;
    @FXML private TableView<Object> listTableView;
    @FXML private Label eventnameLabel;
    @FXML private Button addTeamPopup;
    @FXML private Button banButton;
    @FXML private AnchorPane teamPopup;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker dueDatePicker;
    @FXML private ChoiceBox<String> hourStartChoice;
    @FXML private ChoiceBox<String> minStartChoice;
    @FXML private ChoiceBox<String> hourDueChoice;
    @FXML private ChoiceBox<String> minDueChoice;
    @FXML private Label errorLabel;


    @FXML private TextField teamnameTextfield;
    @FXML private TextField amountTextfield;

    DataSource<ChatList> chatListDataSource;
    private ChatList chatList;
    private MemberList memberList;
    private Member selecteUser;


    @FXML public void initialize() {
        selectEvent = (Event) FXRouter.getData();
        eventnameLabel.setText(selectEvent.getEventName());
        account = selectEvent.getEventOwner();
        profilepicCircle.setFill(new ImagePattern(new Image("file:" + selectEvent.getEventPicture())));
        chatListDataSource = new ChatListDataSource("data", "chat.csv");
        memberDatasource = new MemberDataSource("data", "member.csv");
        teamDatasource = new TeamDataSource("data", "team.csv");
        memberList = memberDatasource.readData();
        chatList = chatListDataSource.readData();
        addTeamPopup.setVisible(false);
        hourStartChoice.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23"
        );
        minStartChoice.getItems().addAll("00", "15", "30", "45");
        hourDueChoice.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23"
        );
        minDueChoice.getItems().addAll("00", "15", "30", "45");
        hourStartChoice.setValue("00");
        minStartChoice.setValue("00");
        hourDueChoice.setValue("00");
        minDueChoice.setValue("00");
        popupClosed();
        showAudienceOnTable();
        hideErrorLabel();

        TextFilter.allowAlphanumericOnly(teamnameTextfield);
        TextFilter.allowOnlyNumber(amountTextfield);
    }

    private void hideErrorLabel() {
        errorLabel.setText("");
    }

    private void showErrorLabel(String text) {
        errorLabel.setText(text);
    }

    public void goEdit() {
        try {
            FXRouter.goTo("edit-event", selectEvent.getEventID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAudienceOnTable() {
        addTeamPopup.setVisible(false);
        banButton.setVisible(false);
        memberList = memberDatasource.readData();

        listTableView.getItems().clear();

        TableColumn<Object, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(param -> {
            Object obj = param.getValue();
            if (obj instanceof Member) {
                Member member = (Member) obj;
                return new SimpleStringProperty(member.getUsername());
            } else if (obj instanceof Team) {
                Team team = (Team) obj;
                return new SimpleStringProperty(team.getNameTeam());
            } else {
                return new SimpleStringProperty("");
            }
        });

        TableColumn<Object, String> roleColumn = new TableColumn<>("role");
        roleColumn.setCellValueFactory(param -> {
            Object obj = param.getValue();
            if (obj instanceof Member) {
                Member member = (Member) obj;
                if(member.getBanStatus()) {
                    return new SimpleStringProperty("BAN");
                }
                return new SimpleStringProperty(member.getRole());
            } else {
                return new SimpleStringProperty("");
            }
        });

        listTableView.getColumns().clear();

        setCenterAlignment(nameColumn);
        setCenterAlignment(roleColumn);

        nameColumn.setMinWidth(148);
        roleColumn.setMinWidth(150);

        listTableView.getColumns().add(nameColumn);
        listTableView.getColumns().add(roleColumn);

        for (Member member: memberList.getMemberList()) {
            if(member.getEventID().equals(selectEvent.getEventID())) {
                if(member.isAudience()) {
                    listTableView.getItems().add(member);
                }
            }
        }
        listTableView.getSelectionModel().selectedItemProperty().removeListener(teamListener);
        listTableView.getSelectionModel().selectedItemProperty().addListener(memberListener);
    }
    private void setCenterAlignment(TableColumn<Object, String> column) {
        column.setCellFactory(tc -> new TableCell<Object, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(null);
                } else {
                    setText(item);
                    setAlignment(javafx.geometry.Pos.CENTER);
                }
            }
        });
    }

    public void showTeamOnTable() {
        banButton.setVisible(false);
        addTeamPopup.setVisible(true);
        listTableView.getItems().clear();

        TeamList teamList = teamDatasource.readData();

        TableColumn<Object, String> nameColumn = new TableColumn<>("Team Name");
        nameColumn.setCellValueFactory(param -> {
            Object obj = param.getValue();
            if (obj instanceof Member) {
                Member member = (Member) obj;
                return new SimpleStringProperty(member.getUsername());
            } else if (obj instanceof Team) {
                Team team = (Team) obj;
                return new SimpleStringProperty(team.getNameTeam());
            } else {
                return new SimpleStringProperty("");
            }
        });

        listTableView.getColumns().clear();

        setCenterAlignment(nameColumn);
        nameColumn.setMinWidth(298);

        listTableView.getColumns().add(nameColumn);

        for (Team team: teamList.getAllTeams()) {
            if(team.getEventID().equals(selectEvent.getEventID())) {
                listTableView.getItems().add(team);
            }
        }
        listTableView.getSelectionModel().selectedItemProperty().removeListener(memberListener);
        listTableView.getSelectionModel().selectedItemProperty().addListener(teamListener);
    }

    public void popupClosed() {
        teamPopup.setVisible(false);
    }
    public void addTeamPopup() {
        teamPopup.setVisible(true);
    }
    public void addTeamButton() {
        String teamName =  teamnameTextfield.getText();
        TeamList teamList = teamDatasource.readData();
        if(!teamName.equals("")) {
            hideErrorLabel();
            boolean itSame = false;
            for(Team team: teamList.getAllTeams()) {
                if(teamName.equals(team.getNameTeam())) {
                    itSame = false;
                }
            }
            if(itSame) {
                showErrorLabel("This has been Used!");
            } else {
                if(amountTextfield.getText().equals("")) {
                    showErrorLabel("Fill the Amount");
                } else {
                    long amount =  Long.parseLong(amountTextfield.getText());
                    if(amount <= 0) {
                        showErrorLabel("Amount must be more than 0!");
                    } else {
                        if(startDatePicker.getValue() == null && dueDatePicker.getValue() == null) {
                            showErrorLabel("Time must be chosen!");
                        } else {
                            String startTimeStr = hourStartChoice.getValue() + ":" + minStartChoice.getValue();
                            String dueTimeStr = hourDueChoice.getValue() + ":" + minDueChoice.getValue();

                            LocalTime startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
                            LocalTime dueTime = LocalTime.parse(dueTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

                            LocalDateTime startDateTime = startDatePicker.getValue().atTime(startTime);
                            LocalDateTime dueDateTime = dueDatePicker.getValue().atTime(dueTime);

                            ZoneId systemZone = ZoneId.systemDefault();
                            long startTimeMillis = startDateTime.atZone(systemZone).toInstant().toEpochMilli();
                            long dueTimeMillis = dueDateTime.atZone(systemZone).toInstant().toEpochMilli();

                            if (dueTimeMillis > startTimeMillis) {
                                hideErrorLabel();
                                Team newTeam = new Team(teamName, selectEvent.getEventID(), amount, startTimeMillis, dueTimeMillis);
                                Chat newChat = new Chat(selectEvent.getEventID(), teamName);
                                teamList.addTeam(newTeam);
                                chatList.addChat(newChat);

                                teamDatasource.writeData(teamList);
                                chatListDataSource.writeData(chatList);

                                teamnameTextfield.clear();
                                amountTextfield.clear();
                                showTeamOnTable();
                                popupClosed();
                            } else {
                                showErrorLabel("Start time must be before due time.");
                            }
                        }
                    }
                }
            }
        } else {
            showErrorLabel("Name is Required!");
        }
    }

    private final ChangeListener<Object> teamListener = (observable, oldValue, newValue) -> {
        if (newValue != null) {
            try {
                Pair<String , Team> sender = new Pair<String, Team>(account, (Team) newValue);
                FXRouter.goTo("manage-team-view", sender);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    };
    private final ChangeListener<Object> memberListener = (observable, oldValue, newValue) -> {
        if (newValue != null) {
            selecteUser = (Member) newValue;
            banButton.setVisible(true);
        }
    };
    public void goeditCalendar() {
        try {
            FXRouter.goTo("calendar-event", selectEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML private void banMember() {
        if(selecteUser != null) {
            selecteUser.ban();
            memberDatasource.writeData(memberList);
            showAudienceOnTable();
        }
    }


    @FXML public void goProflie() {
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
    public void goChat() {
        try {
            FXRouter.goTo("chat-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goLogout() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void gotoMainMenu() {
        try {
            FXRouter.goTo("main-menu", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoBook() {
        try {
            FXRouter.goTo("book-view", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageTeam() {
        try {
            FXRouter.goTo("manage-team", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML private void gotoManageEvent() {
        try {
            FXRouter.goTo("manage-event", account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
