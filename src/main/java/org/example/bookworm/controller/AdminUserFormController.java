package org.example.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AdminUserFormController {
    public TableColumn colDOB;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colName;
    public TableColumn colUserID;
    public TableView tblUsers;
    public AnchorPane SecRoot;
    public ComboBox UserID;
    public JFXButton deleteBtn;

    public void Back(ActionEvent actionEvent) {

    }

    public void onActionDelete(ActionEvent actionEvent) {
    }
}
