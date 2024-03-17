package org.example.bookworm.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import org.example.bookworm.bo.custom.UserBo;
import org.example.bookworm.dto.UserDto;
import org.example.bookworm.factory.BOFactory;

import java.io.IOException;

public class UserSignUpFormController {
    public JFXDatePicker DOB;
    public AnchorPane root;
    public JFXTextField username;
    public JFXPasswordField password1;
    public JFXPasswordField password2;
    public JFXTextField email;
    public JFXTextField name;
    public JFXTextField contact;
    public JFXTextField address;
    public AnchorPane circle2;
    public AnchorPane circle1;
    public AnchorPane circle3;
    public AnchorPane circle4;

    UserBo bo = (UserBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void Back(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-login.fxml")));
    }

    public void SignBtn(ActionEvent actionEvent) {
        try {
            if (checkRegEx()){
                UserDto dto = new UserDto(username.getText(), email.getText(), name.getText(), address.getText(), contact.getText(), DOB.getValue(),password1.getText());
                bo.saveUser(dto);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registration Success! ");
                alert.showAndWait();
                alert.close();
                clear();
                this.root.getChildren().clear();
                this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/org/example/bookworm/view/user-login.fxml")));
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
        name.clear();
        contact.clear();
        address.clear();
        DOB.setValue(null);
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
