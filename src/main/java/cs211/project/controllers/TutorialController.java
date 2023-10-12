package cs211.project.controllers;

import cs211.project.services.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class TutorialController {
    @FXML private ImageView mainShow;
    @FXML private Button previousButton;
    @FXML private Button nextButton;

    private int page;
    @FXML public void initialize() {
        page = 1;
        showImage(page);
        check();
    }

    private void check() {
        if(page > 1 && page < 35) {
            previousButton.setVisible(true);
            nextButton.setVisible(true);
        } else if(page <= 1) {
            previousButton.setVisible(false);
            nextButton.setVisible(true);
        } else if(page >= 35) {
            nextButton.setVisible(false);
            previousButton.setVisible(true);
        }
    }
    @FXML public void back() {
        try {
            FXRouter.goTo("login-view");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void next() {
        page++;
        showImage(page);
        check();
    }

    @FXML public void previous() {
        page--;
        showImage(page);
        check();
    }

    private void showImage(int page) {
        Image image = new Image(getClass().getResourceAsStream("/NEBB_tutorial/" + page + ".png"));
        mainShow.setImage(image);
    }

}

