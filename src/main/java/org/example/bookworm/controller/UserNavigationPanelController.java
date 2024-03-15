package org.example.bookworm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserNavigationPanelController {
    public AnchorPane SecRoot;
    public AnchorPane dashboardNav;

    public AnchorPane root;

    public void initialize() throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-dashboard.fxml")));
    }

    public void book(ActionEvent actionEvent) throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-book.fxml")));
    }

    public void dashboard(ActionEvent actionEvent) throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-dashboard.fxml")));
    }

    public void transaction(ActionEvent actionEvent) throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-borrow.fxml")));
    }

    public void history(ActionEvent actionEvent) throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-history.fxml")));
    }

    public void settings(ActionEvent actionEvent) throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-setting.fxml")));
    }

    public void LogOut(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/org/example/bookworm/view/user-login.fxml")).load()));
        newStage.show();
    }
}
