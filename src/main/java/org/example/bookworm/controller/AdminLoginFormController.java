package org.example.bookworm.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.bookworm.bo.custom.AdminBo;
import org.example.bookworm.dto.AdminDto;
import org.example.bookworm.factory.BOFactory;

import java.io.IOException;

public class AdminLoginFormController {

    public AnchorPane AnchorPane;
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;
    public AnchorPane LoginPanel;
    public JFXComboBox username;
    public JFXPasswordField password1;
    public static String pw=null;
    public JFXTextField password2;
    public Label hidePassword;
    public Label showPassword;

    AdminBo adminBo = (AdminBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    @FXML
    void createOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/createAccount.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void initialize(){
        password1.setVisible(true);
        password2.setVisible(false);
        hidePassword.setVisible(false);
        showPassword.setVisible(true);
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

        password1.setVisible(true);
        password2.setVisible(false);

        hidePassword.setVisible(false);
        showPassword.setVisible(true);
    }

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {

        String name = txtName.getText();
        String password = txtPassword.getText();


        if (name.isEmpty() || password.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"fields are empty").show();
            return;
        }


        boolean isTrue = false;
        try {
            isTrue = adminBo.check(new AdminDto("",name,password));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Check UserName or Password").show();
            throw new RuntimeException(e);
        }

        if (isTrue) {
            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Check USerName or Password").show();
        }


    }

    @FXML
    void nameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void passwordOnAction(ActionEvent event) throws IOException {
        loginOnAction(event);
    }

    public void closeOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
}
