package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class TutorialController {


    private ImageView mainShow;

    @FXML
    public void back() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void next() {

    }

    @FXML public void previous() {

        }


}

