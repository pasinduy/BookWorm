package org.example.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserSettingFormController {

    public AnchorPane root;

    public void Back(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-dashboard.fxml")));
    }

    public void Password(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-change-password.fxml")));
    }

    public void Username(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-change-username.fxml")));
    }
}
