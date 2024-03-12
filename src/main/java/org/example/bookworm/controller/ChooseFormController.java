package org.example.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseFormController {
    public Label anotherLabel;
    public JFXButton User;
    public JFXButton Admin;
    public Label otherLabel;
    public AnchorPane root;

    public void btnAdmin(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/org/example/bookworm/view/admin-login.fxml")).load()));
        newStage.show();
    }

    public void btnUser(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/org/example/bookworm/view/user-login.fxml")).load()));
        newStage.show();
    }
}
