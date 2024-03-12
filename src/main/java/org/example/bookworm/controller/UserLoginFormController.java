package org.example.bookworm.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginFormController {
    public AnchorPane root;
    public AnchorPane LoginPanel;
    public JFXComboBox username;
    public JFXTextField password;

    public void SignInPage(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-signIn.fxml")));
    }

    public void LoginBtn(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/org/example/bookworm/view/user-nav.fxml")).load()));
        newStage.show();
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/org/example/bookworm/view/choose.fxml")).load()));
        newStage.show();
    }
}
