package com.example.project_htetwaiyan_2.Controller;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import com.example.project_htetwaiyan_2.Model.Settings;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Button QuitBtn;
    @FXML
    void QuitAction(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    private Slider SpeedSlider;
    @FXML
    private TextField SpeedField;
    @FXML
    private ToggleButton FocusToggle;
    @FXML
    private Button InputBtn;
    @FXML
    private Button ExitBtn;
    public void initialize() {
        //System.out.println("Init to " + Settings.chosenSpeed);
        SpeedSlider.setValue(Settings.chosenSpeed);
        if (Settings.IsFocusOff) {
            FocusToggle.setStyle("-fx-background-color: #f05454");
            FocusToggle.setText("Off");
        }
        else {
            FocusToggle.setStyle("-fx-background-color: #71f0ae");
            FocusToggle.setText("On");
        }

        SpeedSlider.valueProperty().addListener(e -> {
            Settings.chosenSpeed = SpeedSlider.getValue();
            //System.out.println("Updated to " + Settings.chosenSpeed);
        });
    }
    @FXML
    void FocusAction(ActionEvent event) {
        if (Settings.IsFocusOff) {
            FocusToggle.setStyle("-fx-background-color: #71f0ae");
            FocusToggle.setText("On");
            Settings.IsFocusOff = !Settings.IsFocusOff;
        }
        else {
            FocusToggle.setStyle("-fx-background-color: #f05454");
            FocusToggle.setText("Off");
            Settings.IsFocusOff = !Settings.IsFocusOff;
        }
    }

    @FXML
    void HandleSpeedField(ActionEvent event) {
        String str = SpeedField.getText();
        if (str.matches("[0-9]+(\\.[0-9]*)?")) {

        }
    }
    @FXML
    void InputSpeed(ActionEvent event) {
        String str = SpeedField.getText();
        if (!str.matches("[0-9]+(\\.[0-9]*)?")) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Invalid Input!");
            a.setContentText("Please enter a valid number");
            a.show();
            SpeedField.clear();
        }
        else {
            double d = Double.parseDouble(str);
            if (d < 0.25 || d > 1.25) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Invalid Input!");
                a.setContentText("Please enter a double value between 0.25 and 1.25");
                a.show();
                SpeedField.clear();
            }
            else {
                SpeedSlider.setValue(d);
                Settings.chosenSpeed = d;
                SpeedField.clear();
            }
        }
    }
    @FXML
    void ExitAction(ActionEvent event) {
        Stage stage = (Stage) ExitBtn.getScene().getWindow();
        stage.close();
    }
}
