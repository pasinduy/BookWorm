package org.example.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.bookworm.bo.custom.AdminBo;
import org.example.bookworm.bo.custom.UserBo;
import org.example.bookworm.dto.UserDto;
import org.example.bookworm.factory.BOFactory;
import org.example.bookworm.tm.UserTm;

import java.io.IOException;
import java.util.List;

public class AdminUserFormController {

    public TableColumn<?, ?> colId;
    public TableView<UserTm> tblMember;
    public Text txtId;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<?> tblBook;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTel;

    AdminBo adminBo = (AdminBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);
    UserBo userBo = (UserBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        getAllUser();
        setCellValues();
        generateNextUserId();
    }

    private void generateNextUserId() {
        try {

            String id = userBo.generateNextUserId();
            txtId.setText(id);

        }catch (Exception e){

        }

    }

    private void setCellValues() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void getAllUser() {

        try{

            List<UserDto> userDto =userBo.getAllUser();

            ObservableList<UserTm> userTm= FXCollections.observableArrayList();

            for(UserDto dto:userDto){
                System.out.println(dto.getPhone());
                userTm.add(new UserTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getEmail(),
                        dto.getPhone()
                ));
            }
            tblMember.setItems(userTm);

        }catch (Exception e){

        }
    }

    @FXML
    void addOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();

        try{
            boolean isSaved = userBo.saveUser(new UserDto(id,name,address,email,tel));

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully").show();
                initialize();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"Save Failed").show();
            }

        }catch (Exception e){

        }

    }

    private void clearFields() {
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtTel.clear();
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();

        try {

            boolean isDelete = userBo.DeleteUser(new UserDto(id,name,address,email,tel));

            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION,"delete Successfully").show();
                initialize();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"delete Failed").show();
            }

        }catch (Exception e){

        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();

        try{

            boolean isDelete = userBo.updateUser(new UserDto(id,name,address,email,tel));

            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION,"update Successfully").show();
                initialize();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"update Failed").show();
            }

        }catch (Exception e){

        }

    }

    public void closeOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) txtAddress.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void mouseClickOnAction(MouseEvent mouseEvent) {
        Integer index = tblMember.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        txtId.setText(colId.getCellData(index).toString());
        txtName.setText(colName.getCellData(index).toString());
        txtAddress.setText(colAddress.getCellData(index).toString());
        txtEmail.setText(colEmail.getCellData(index).toString());
        txtTel.setText(colTel.getCellData(index).toString());
    }
}
