package org.example.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AdminBookFormController {
    public AnchorPane root;
    public TextField txtName;
    public TextField txtContact;
    public ComboBox branchName;
    public JFXButton deleteBtn;
    public JFXButton saveBtn;
    public TextField txtAddress;
    public TextField txtSearch;
    public JFXButton btnSearch;
    public TextField txtId;
    public AnchorPane SecRoot;
    public TableView tblTenants;
    public TableView tblBooks;
    public TableColumn colBranchName;
    public TableColumn colGenre;
    public TableColumn colAuthor;
    public TableColumn colBookTitle;
    public TableColumn colBookId;

    public void onActionDelete(ActionEvent actionEvent) {
    }

    public void onActionSave(ActionEvent actionEvent) {
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
