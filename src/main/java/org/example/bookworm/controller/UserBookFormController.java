package org.example.bookworm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bookworm.bo.custom.BookBo;
import org.example.bookworm.dto.BookDto;
import org.example.bookworm.factory.BOFactory;
import org.example.bookworm.tm.BookTm;

import java.util.List;

public class UserBookFormController {
    public TableView tblBooks;
    public TableColumn colBookId;
    public TableColumn colBookTitle;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    public TableColumn colBranchName;
    public AnchorPane SecRoot;
    public AnchorPane root;

    BookBo bookBo = (BookBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    public void initialize(){
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
            tblBooks.setItems(bookTm);
        }catch (Exception e){
            throw new RuntimeException(e);
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

    public void Back(ActionEvent actionEvent) {

    }
}
