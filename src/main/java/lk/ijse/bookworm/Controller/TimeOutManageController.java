package lk.ijse.bookworm.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.bookworm.Bo.Custom.impl.QueryBoImpl;
import lk.ijse.bookworm.Dto.TimeOutDto;
import lk.ijse.bookworm.Dto.Tm.TimeOutTm;

import java.io.IOException;
import java.util.List;

public class TimeOutManageController {

    public TableColumn<?, ?> colUser;
    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableView<TimeOutTm> tblTimeOut;

    QueryBoImpl queryBo = new QueryBoImpl();
    public void initialize(){
        setCellValues();
        setTimeOutDetails();
    }

    private void setTimeOutDetails() {
        try{
            List<TimeOutDto> dtoList = queryBo.setAllTimeOut();
            ObservableList<TimeOutTm> tm = FXCollections.observableArrayList();

            for(TimeOutDto dto :dtoList){
                tm.add(new TimeOutTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getBookId(),
                        dto.getBorrowDate(),
                        dto.getReturnDate()
                ));
            }
            tblTimeOut.setItems(tm);

        }catch (Exception e){

        }
    }

    private void setCellValues() {
        colUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

    }

    @FXML
    void closeOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) tblTimeOut.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void mouseClickOnAction(MouseEvent event) {

    }

}
