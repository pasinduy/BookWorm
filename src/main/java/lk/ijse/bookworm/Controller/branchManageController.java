package lk.ijse.bookworm.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.bookworm.Bo.BoFactory;
import lk.ijse.bookworm.Bo.Custom.AdminBo;
import lk.ijse.bookworm.Bo.Custom.BranchBo;
import lk.ijse.bookworm.Bo.Custom.impl.AdminBoImpl;
import lk.ijse.bookworm.Bo.Custom.impl.BranchBoImpl;
import lk.ijse.bookworm.Dto.AdminDto;
import lk.ijse.bookworm.Dto.BranchDto;
import lk.ijse.bookworm.Dto.Tm.BranchTm;
import lk.ijse.bookworm.Entity.Admin;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class branchManageController {

    public Text txtId;
    public TableColumn<?, ?>colId;
    @FXML
    private JFXComboBox cmbAdmin;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAdmin;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<BranchTm> tblBook;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtLocation;

    @FXML
    private JFXTextField txtName;

    BranchBo branchBo = (BranchBo) BoFactory.getBoFactory().getBo(BoFactory.BOTypes.BRANCH);
    AdminBo adminBo = (AdminBo) BoFactory.getBoFactory().getBo(BoFactory.BOTypes.ADMIN);

    Admin admin = new Admin();

    public void initialize(){
        setAdmin();
        generateNextBranchId();
        setCellValues();
        getAllBranchs();
    }

    private void setCellValues() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("Admin"));
    }

    private void getAllBranchs() {
        List<BranchDto> branchDto = null;
        try {
            branchDto = branchBo.getAllBranch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ObservableList<BranchTm> branches = FXCollections.observableArrayList();

        for(BranchDto dto : branchDto){
            branches.add(new BranchTm(
                    dto.getId(),
                    dto.getName(),
                    dto.getLocation(),
                    dto.getAddress(),
                    dto.getAdmin().getId()
            ));
        }

        tblBook.setItems(branches);
    }

    private void generateNextBranchId() {
        try{
            String id = branchBo.generateNextId();
            txtId.setText(id);
        }catch (Exception e){

        }

    }

    private void setAdmin() {

        try{
            List<AdminDto> adminDtoList = adminBo.getAllAdmin();

            ObservableList<String> admins = FXCollections.observableArrayList();

            for(AdminDto adminDto : adminDtoList){
                admins.add(adminDto.getId());
                System.out.println(adminDto.getId());
            }
            cmbAdmin.setItems(admins);

        }catch (Exception e){

        }

    }

    @FXML
    void addOnAction(ActionEvent event) {

        if(validateBranch()) {

            String id = txtId.getText();
            String name = txtName.getText();
            String location = txtLocation.getText();
            String address = txtAddress.getText();
            String adminId = (String) cmbAdmin.getValue();

            admin.setId(adminId);

            try {

                boolean isSaved = branchBo.save(new BranchDto(id, name, location, address, admin));

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Save SuccessFully").show();
                    initialize();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Save Failed").show();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Save Failed").show();
            }
        }

    }

    private boolean validateBranch() {
        String name = txtName.getText();

        boolean nameMatch = Pattern.matches("[A-za-z\\\\s]{1,}",name);
        if (!nameMatch) {
            new Alert(Alert.AlertType.ERROR,"invalid Name").show();
            return false;
        }

        String location = txtLocation.getText();

        boolean locMatch = Pattern.matches("[A-za-z\\\\s]{1,}",location);
        if (!locMatch) {
            new Alert(Alert.AlertType.ERROR,"invalid Location").show();
            return false;
        }

        String address = txtAddress.getText();

        boolean addressMatch = Pattern.matches("[A-za-z\\\\s]{1,}",address);
        if (!addressMatch) {
            new Alert(Alert.AlertType.ERROR,"invalid Address").show();
            return false;
        }

        String adminId = (String) cmbAdmin.getValue();

        if (adminId==null) {
            new Alert(Alert.AlertType.ERROR,"adminId is Null").show();
            return false;
        }
        return true;

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        if(validateBranch()) {
            String id = txtId.getText();
            String name = txtName.getText();
            String location = txtLocation.getText();
            String address = txtAddress.getText();
            String adminId = (String) cmbAdmin.getValue();

            admin.setId(adminId);

            try {

                boolean isRemoved = branchBo.deleteBranch(new BranchDto(id, name, location, address, admin));

                if (isRemoved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "delete Successfully").show();
                    initialize();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "delete Failed").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.CONFIRMATION, "delete Failed").show();
            }
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {

        if(validateBranch()) {

            String id = txtId.getText();
            String name = txtName.getText();
            String location = txtLocation.getText();
            String address = txtAddress.getText();
            String adminId = (String) cmbAdmin.getValue();

            admin.setId(adminId);

            try {

                boolean isRemoved = branchBo.updateBranch(new BranchDto(id, name, location, address, admin));

                if (isRemoved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully").show();
                    initialize();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Update Failed").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Update Failed").show();
            }
        }
    }

    private void clearFields() {
        txtName.clear();
        txtAddress.clear();
        txtLocation.clear();
        cmbAdmin.setValue("");
    }

    public void closeOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) txtLocation.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void mouseClickOnAction(MouseEvent mouseEvent) {
        Integer index = tblBook.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        txtId.setText(colId.getCellData(index).toString());
        txtName.setText(colName.getCellData(index).toString());
        txtLocation.setText(colLocation.getCellData(index).toString());
        txtAddress.setText(colAddress.getCellData(index).toString());
        cmbAdmin.setValue(colAdmin.getCellData(index).toString());
    }

}
