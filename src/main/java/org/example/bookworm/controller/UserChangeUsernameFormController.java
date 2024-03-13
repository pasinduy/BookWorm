package org.example.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserChangeUsernameFormController {
    public JFXTextField username;
    public JFXTextField username2;
    public JFXButton changeButton;
    public JFXButton clearButton;
    public Separator separator1;
    public Separator separator2;
    public AnchorPane root;

    public void Back(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-setting.fxml")));
    }

    public void Clear(ActionEvent actionEvent) {
    }

    public void Change(ActionEvent actionEvent) {
    }
}
