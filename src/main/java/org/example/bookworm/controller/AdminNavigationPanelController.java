package org.example.bookworm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNavigationPanelController {
    public AnchorPane dashboardNav;
    public AnchorPane root;
    public AnchorPane ThirdRoot;
    public AnchorPane SecRoot;

    public void initialize() throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/admin-dashboard.fxml")));
    }
    public void history(ActionEvent actionEvent) throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/admin-duedates.fxml")));
    }

    public void users(ActionEvent actionEvent) throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/admin-users.fxml")));
    }

    public void dashboard(ActionEvent actionEvent) throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/admin-dashboard.fxml")));
    }

    public void books(ActionEvent actionEvent) throws IOException {
        this.SecRoot.getChildren().clear();
        this.SecRoot.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/admin-books.fxml")));
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.close();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/org/example/bookworm/view/admin-login.fxml")).load()));
        newStage.show();
    }
}
