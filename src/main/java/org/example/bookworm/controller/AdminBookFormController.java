package org.example.bookworm.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.bookworm.bo.custom.BookBo;
import org.example.bookworm.bo.custom.BranchBo;
import org.example.bookworm.dto.BookDto;
import org.example.bookworm.dto.BranchDto;
import org.example.bookworm.entity.Branch;
import org.example.bookworm.factory.BOFactory;
import org.example.bookworm.tm.BookTm;

import java.io.IOException;
import java.util.List;

public class AdminBookFormController {

    public Text txtId;
    public TableColumn<?,?> colId;
    @FXML
    private JFXComboBox cmbBranch;

    @FXML
    private JFXComboBox cmbStatus;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colGenare;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<BookTm> tblBook;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtGenare;

    @FXML
    private JFXTextField txtTitle;

    BookBo bookBo = (BookBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    BranchBo branchBo = (BranchBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    Branch branch = new Branch();

    public void initialize(){
        generateNextId();
        setStatus();
        setBranch();
        setCellValues();
        getAllBooks();
    }

    private void getAllBooks() {
        try{
            List<BookDto> books = bookBo.getAllBooks();

            ObservableList<BookTm> bookTm = FXCollections.observableArrayList();

            for(BookDto dto :books){
                bookTm.add(new BookTm(
                        dto.getId(),
                        dto.getTitle(),
                        dto.getAuthor(),
                        dto.getGenre(),
                        dto.getStatus(),
                        dto.getBranch().getId()
                ));
            }
            tblBook.setItems(bookTm);

        }catch (Exception e){

        }

    }

    private void setCellValues() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenare.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
    }

    private void setBranch() {

        ObservableList<String> branchId = FXCollections.observableArrayList();

        try {
            List<BranchDto> branch = branchBo.getAllBranch();
            for(BranchDto branchDto :branch){
                branchId.add(branchDto.getId());
            }

            cmbBranch.setItems(branchId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setStatus() {
        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("Available");
        status.add("Unavailable");

        cmbStatus.setItems(status);
    }

    private void generateNextId() {

        try{
            String id = bookBo.generateNextBookId();
            txtId.setText(id);

        }catch (Exception e){

        }
    }

    @FXML
    void addOnAction(ActionEvent event) {
        String id = txtId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String Genre = txtGenare.getText();
        String Status = (String) cmbStatus.getValue();
        String Branch = (String) cmbBranch.getValue();

        System.out.println(Status);

        branch.setId(Branch);
        try{
            boolean isSaved = bookBo.saveBook(new BookDto(id,title,author,Genre,Status,branch));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Save Successfully").show();
                initialize();
                clearFeilds();
            }else{
                new Alert(Alert.AlertType.ERROR,"Save Failed").show();
            }
        }catch (Exception e){

        }


    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String Genre = txtGenare.getText();
        String Status = (String) cmbStatus.getValue();
        String Branch = (String) cmbBranch.getValue();

        branch.setId(Branch);

        try{
            boolean idDelete = bookBo.deleteBook(new BookDto(id,title,author,Genre,Status,branch));

            if (idDelete) {
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Successfully").show();
                initialize();
                clearFeilds();
            }else{
                new Alert(Alert.AlertType.ERROR,"Delete Failed").show();
            }

        }catch (Exception e){

        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String Genre = txtGenare.getText();
        String Status = (String) cmbStatus.getValue();
        String Branch = (String) cmbBranch.getValue();

        branch.setId(Branch);

        try{
            boolean isUpdate = bookBo.updateBook(new BookDto(id,title,author,Genre,Status,branch));

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION,"Update Successfully").show();
                initialize();
                clearFeilds();
            }else{
                new Alert(Alert.AlertType.ERROR,"Update Failed").show();
            }

        }catch (Exception e){

        }



    }

    private void clearFeilds() {
        txtTitle.clear();
        txtAuthor.clear();
        txtGenare.clear();
        cmbStatus.setValue("");
        cmbBranch.setValue("");
    }

    public void closeOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) txtAuthor.getScene().getWindow();
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
        txtTitle.setText(colTitle.getCellData(index).toString());
        txtAuthor.setText(colAuthor.getCellData(index).toString());
        txtGenare.setText(colGenare.getCellData(index).toString());
        cmbStatus.setValue(colStatus.getCellData(index).toString());
        cmbBranch.setValue(colBranch.getCellData(index).toString());
    }

}
