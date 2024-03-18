package org.example.bookworm.Controller;

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
import org.example.bookworm.Bo.custom.*;
import org.example.bookworm.Dto.*;
import org.example.bookworm.Bo.BoFactory;

import java.io.IOException;

public class LoginPageController {

    public AnchorPane AnchorPane;
    public Label hidePassword;
    public Label showPassword;
    @FXML
    public JFXPasswordField password1;
    public JFXTextField password2;
    @FXML
    private JFXTextField txtName;
    public static String pw=null;
    AdminBo adminBo = (AdminBo) BoFactory.getBoFactory().getBo(BoFactory.BOTypes.ADMIN);

    public  void initialize(){
        password1.setVisible(true);
        password2.setVisible(false);
        hidePassword.setVisible(false);
        showPassword.setVisible(true);
    }

    @FXML
    void createOnAction(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) AnchorPane.getScene().getWindow();
        currentStage.close();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("/view/createAccount.fxml")).load()));
        newStage.show();
    }

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {

        String name = txtName.getText();
        String password = password1.getText();


        if (name.isEmpty() || password.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"fields are empty").show();
            return;
        }


        boolean isTrue = false;
        try {
            isTrue = adminBo.check(new AdminDto("",name,password));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Check Username or Password").show();
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
            new Alert(Alert.AlertType.ERROR,"Check UserName or Password").show();
        }


    }

    @FXML
    void nameOnAction(ActionEvent event) {
        password1.requestFocus();
    }

    @FXML
    void passwordOnAction(ActionEvent event) throws IOException {
        loginOnAction(event);
    }

    public void closeOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }

    public void CloseEyes(MouseEvent mouseEvent) {
        pw = password1.getText();
        password2.setText(pw);

        password1.setVisible(false);
        password2.setVisible(true);

        hidePassword.setVisible(true);
        showPassword.setVisible(false);
    }

    public void OpenEyes(MouseEvent mouseEvent) {
        pw = password2.getText();
        password1.setText(pw);

        password1.setVisible(true);
        password2.setVisible(false);

        hidePassword.setVisible(false);
        showPassword.setVisible(true);
    }
}
