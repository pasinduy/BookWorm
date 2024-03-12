package org.example.bookworm.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginFormController {
    public AnchorPane root;
    public AnchorPane LoginPanel;
    public JFXComboBox username;
    public JFXPasswordField password1;
    public static String pw=null;
    public JFXTextField password2;
    public Label hidePassword;
    public Label showPassword;

    public void initialize(){
        password1.setVisible(true);
        password2.setVisible(false);
        hidePassword.setVisible(false);
        showPassword.setVisible(true);
    }

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

    public void ClickOpenEyes(MouseEvent mouseEvent) {
        pw = password1.getText();
        password2.setText(pw);

        password1.setVisible(false);
        password2.setVisible(true);

        hidePassword.setVisible(true);
        showPassword.setVisible(false);
    }

    public void ClickCloseEye(MouseEvent mouseEvent) {
        pw = password2.getText();
        password1.setText(pw);

        password1.setVisible(false);
        password2.setVisible(true);

        hidePassword.setVisible(true);
        showPassword.setVisible(false);
    }
}
