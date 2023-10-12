module cs211.project {
    requires javafx.controls;
    requires javafx.fxml;


    opens cs211.project.cs211661project to javafx.fxml;
    exports cs211.project.cs211661project;
    exports cs211.project.controllers;
    opens cs211.project.controllers to javafx.fxml;
    exports cs211.project.models.users;
    opens cs211.project.models.users to javafx.base, javafx.fxml;
    exports cs211.project.models.chats;
    opens cs211.project.models.chats to javafx.base, javafx.fxml;
    exports cs211.project.models.team;
    opens cs211.project.models.team to javafx.base, javafx.fxml;
    exports cs211.project.models.eventHub;
    opens cs211.project.models.eventHub to javafx.base, javafx.fxml;
}