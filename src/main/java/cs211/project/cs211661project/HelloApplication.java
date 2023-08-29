package cs211.project.cs211661project;

import javafx.application.Application;
import javafx.stage.Stage;
import cs211.project.services.FXRouter;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        configRoute();

        FXRouter.bind(this, stage, "CS211 661 Project");
        FXRouter.goTo("login-view");

    }

    private static void configRoute() {
        String resourcesPath = "cs211/project/views/";
        FXRouter.when("main-menu", resourcesPath + "main-menu.fxml");
        FXRouter.when("login-view", resourcesPath + "login-view.fxml");
        FXRouter.when("reg-view", resourcesPath + "register-view.fxml");
        FXRouter.when("developer-view", resourcesPath + "developer-view.fxml");
        FXRouter.when("profile-view", resourcesPath + "profile-view.fxml");
        FXRouter.when("admin-view", resourcesPath + "admin-view.fxml");
        FXRouter.when("book-view", resourcesPath + "bookinghistory-view.fxml");
        FXRouter.when("create-event", resourcesPath + "create-event.fxml");
        FXRouter.when("admin-edit", resourcesPath + "admin-edit-view.fxml");
        FXRouter.when("event-view", resourcesPath + "event-view.fxml");

    }


    public static void main(String[] args) {
        launch();
    }
}