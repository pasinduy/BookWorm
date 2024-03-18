package lk.ijse.bookworm.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.bookworm.Bo.BoFactory;
import lk.ijse.bookworm.Bo.Custom.AdminBo;
import lk.ijse.bookworm.Bo.Custom.impl.AdminBoImpl;
import lk.ijse.bookworm.Dto.AdminDto;

import java.io.IOException;

public class CreateAccountController {

    public Text txtId;
    public AnchorPane root;
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    private AdminBo adminBo = (AdminBo) BoFactory.getBoFactory().getBo(BoFactory.BOTypes.ADMIN);

    public void initialize(){
        genarateNextAdminId();
    }

    private void genarateNextAdminId() {
        try{
            String id = adminBo.genarateNextAdminId();
            txtId.setText(id);

        }catch (Exception e){

        }
    }

    @FXML
    void loginOnAction(ActionEvent event) {
        String name = txtName.getText();
        String password = txtPassword.getText();
        String id = txtId.getText();

        System.out.println(name);

        if(name.equals("") || password.equals("")){
            new Alert(Alert.AlertType.ERROR,"fields are empty").show();
            return;
        }

        try{

            boolean isSaved = adminBo.saveCustomer(new AdminDto(id,name,password));

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Saved successfully").show();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(new FXMLLoader(getClass().getResource("view/loginPage.fxml")).load()));
                newStage.show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Saved failed").show();
            }

        }catch (Exception e){
        }


    }

    @FXML
    void nameOnAction(ActionEvent event) {
        txtPassword.requestFocus();

    }

    @FXML
    void passwordOnAction(ActionEvent event) {
        loginOnAction(event);
    }

    public void closeOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }

}
