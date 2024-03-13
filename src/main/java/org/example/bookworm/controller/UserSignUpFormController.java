package org.example.bookworm.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import org.example.bookworm.bo.custom.AdminBO;
import org.example.bookworm.bo.custom.UserBO;
import org.example.bookworm.dto.AdminDto;
import org.example.bookworm.dto.UserDto;
import org.example.bookworm.entity.User;
import org.example.bookworm.factory.BOFactory;
import org.example.bookworm.util.RegExFactory;
import org.example.bookworm.util.RegExType;

import java.io.IOException;

public class UserSignUpFormController {
    public AnchorPane root;
    public AnchorPane signUp;
    public JFXTextField username;
    public JFXPasswordField password1;
    public JFXPasswordField password2;
    public JFXTextField email;

    UserBO bo = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void Back(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-signIn.fxml")));
    }

    public void SignBtn(ActionEvent actionEvent) {
        try {
            if (checkRegEx()){
                UserDto dto = new UserDto(username.getText(), email.getText(), password1.getText());
                bo.saveUser(dto);
                System.out.println(username.getText());
                System.out.println(email.getText());
                System.out.println(password1.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registration Success! ");
                alert.showAndWait();
                clear();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Invalid input or password are not match!").showAndWait();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void clear() {
        username.clear();
        email.clear();
        password1.clear();
        password2.clear();
    }

    private boolean checkRegEx() {
        return RegExFactory.getInstance().getPattern(RegExType.USERID).matcher(username.getText()).matches()
                && RegExFactory.getInstance().getPattern(RegExType.EMAIL).matcher(email.getText()).matches()
                && RegExFactory.getInstance().getPattern(RegExType.PASSWORD).matcher(password1.getText()).matches()
                && password1.getText().equals(password2.getText());
    }
}
