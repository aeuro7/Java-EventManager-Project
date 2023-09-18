package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

public class EditEventController {
    @FXML private TextField eventnameTextField;
    @FXML private DatePicker startDatePicker;
    @FXML private TextField startTimeTextField;
    @FXML private DatePicker dueDatePicker;
    @FXML private TextField dueTimeTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField audienceTextField;
    @FXML private TextField limitStaffTextField;
    @FXML private TextField descriptionTextField;
    @FXML private Circle eventPicCircle;
    @FXML private ChoiceBox hourStartChoice;
    @FXML private ChoiceBox minStartChoice;
    @FXML private ChoiceBox hourDueChoice;
    @FXML private ChoiceBox minDueChoice;
}
