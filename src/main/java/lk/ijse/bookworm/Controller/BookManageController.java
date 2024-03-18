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
import lk.ijse.bookworm.Bo.Custom.BookBo;
import lk.ijse.bookworm.Bo.Custom.BranchBo;
import lk.ijse.bookworm.Bo.Custom.impl.BookBoImpl;
import lk.ijse.bookworm.Bo.Custom.impl.BranchBoImpl;
import lk.ijse.bookworm.Dto.BookDto;
import lk.ijse.bookworm.Dto.BranchDto;
import lk.ijse.bookworm.Dto.Tm.BookTm;
import lk.ijse.bookworm.Entity.Branch;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class BookManageController {

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
    
    BookBo bookBo = (BookBo) BoFactory.getBoFactory().getBo(BoFactory.BOTypes.BOOK);
    BranchBo branchBo = (BranchBo) BoFactory.getBoFactory().getBo(BoFactory.BOTypes.BRANCH);

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

        if(validation()) {

            String id = txtId.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String Genre = txtGenare.getText();
            String Status = (String) cmbStatus.getValue();
            String Branch = (String) cmbBranch.getValue();

            System.out.println(Status);

            branch.setId(Branch);
            try {
                boolean isSaved = bookBo.saveBook(new BookDto(id, title, author, Genre, Status, branch));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Save Successfully").show();
                    initialize();
                    clearFeilds();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Save Failed").show();
                }
            } catch (Exception e) {

            }
        }


    }

    private boolean validation() {

        String title = txtTitle.getText();
        boolean idMatch = Pattern.matches("[A-za-z\\\\s]{1,}",title);

        if(!idMatch){
            new Alert(Alert.AlertType.ERROR,"invalid Title").show();
            return false;
        }

        String author = txtAuthor.getText();
        boolean authorMatch = Pattern.matches("[A-za-z\\\\s]{1,}",author);

        if(!authorMatch){
            new Alert(Alert.AlertType.ERROR,"invalid Author").show();
            return false;
        }

        String Genre = txtGenare.getText();
        boolean genreMatch = Pattern.matches("[A-za-z\\\\s]{1,}",Genre);

        if(!genreMatch){
            new Alert(Alert.AlertType.ERROR,"invalid Genare").show();
            return false;
        }

        String Status = (String) cmbStatus.getValue();
        if(Status==null){
            new Alert(Alert.AlertType.ERROR,"status is empty").show();
            return false;
        }

        String Branch = (String) cmbBranch.getValue();
        if(Branch==null){
            new Alert(Alert.AlertType.ERROR,"Branch is empty").show();
            return false;
        }

        return true;
    }

    @FXML
    void deleteOnAction(ActionEvent event) {

        if(validation()) {

            String id = txtId.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String Genre = txtGenare.getText();
            String Status = (String) cmbStatus.getValue();
            String Branch = (String) cmbBranch.getValue();

            branch.setId(Branch);

            try {
                boolean idDelete = bookBo.deleteBook(new BookDto(id, title, author, Genre, Status, branch));

                if (idDelete) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Delete Successfully").show();
                    initialize();
                    clearFeilds();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Delete Failed").show();
                }

            } catch (Exception e) {

            }
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {

        if(validation()) {

            String id = txtId.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String Genre = txtGenare.getText();
            String Status = (String) cmbStatus.getValue();
            String Branch = (String) cmbBranch.getValue();

            branch.setId(Branch);

            try {
                boolean isUpdate = bookBo.updateBook(new BookDto(id, title, author, Genre, Status, branch));

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully").show();
                    initialize();
                    clearFeilds();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Update Failed").show();
                }

            } catch (Exception e) {

            }
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
