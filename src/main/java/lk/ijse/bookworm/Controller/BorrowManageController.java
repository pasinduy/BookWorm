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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.bookworm.Bo.BoFactory;
import lk.ijse.bookworm.Bo.Custom.BookBo;
import lk.ijse.bookworm.Bo.Custom.BorrowBo;
import lk.ijse.bookworm.Bo.Custom.UserBo;
import lk.ijse.bookworm.Bo.Custom.impl.BookBoImpl;
import lk.ijse.bookworm.Bo.Custom.impl.BorrowBoImpl;
import lk.ijse.bookworm.Bo.Custom.impl.UserBoImpl;
import lk.ijse.bookworm.Config.FactoryConfiguration;
import lk.ijse.bookworm.Dto.BookDto;
import lk.ijse.bookworm.Dto.Tm.BorrowTm;
import lk.ijse.bookworm.Dto.UserDto;
import lk.ijse.bookworm.Entity.Book;
import lk.ijse.bookworm.Entity.BookDetails;
import lk.ijse.bookworm.Entity.Borrow;
import lk.ijse.bookworm.Entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowManageController {

    @FXML
    public Text txtBorrowId;

    @FXML
    private DatePicker borrowdDate;

    @FXML
    private JFXComboBox cmbBook;

    @FXML
    private JFXComboBox cmbMember;

    @FXML
    private JFXComboBox cmbStstus;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowdDate;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private DatePicker returnDate;

    @FXML
    private TableView<BorrowTm> tblBorrow;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtName;

    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBo(BoFactory.BOTypes.USER);

    BookBo bookBo = (BookBo) BoFactory.getBoFactory().getBo(BoFactory.BOTypes.BOOK);

    BorrowBo borrowBo = (BorrowBo) BoFactory.getBoFactory().getBo(BoFactory.BOTypes.BORROW);

    ObservableList<BorrowTm> borrowTmList = FXCollections.observableArrayList();

    User user = new User();

    private ArrayList<String> detailId = new ArrayList<>();
    private String orderDetailId;

    public void initialize(){
        borrowdDate.setValue(LocalDate.now());
        setMember();
        setBook();
        setStatus();
        setCellvalues();
        generateBorrowId();
    }

    private String generateOrderDetailId() {
        try {
            return  borrowBo.generateNextOrderDetailId();
        } catch (Exception e) {

        }
        return null;
    }

    private void generateBorrowId() {

        try{
            String borrowId = borrowBo.genarateNextBorrowId();
            txtBorrowId.setText(borrowId);

        }catch (Exception e){

        }

    }

    private void setCellvalues() {

        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colBorrowdDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

    }

    private void setStatus() {
        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("Borrow");
        status.add("Return");

        cmbStstus.setItems(status);
    }

    private void setBook() {

        try{
            List<BookDto> bookDto = bookBo.getAllBooks();
            ObservableList<String> bookLIST = FXCollections.observableArrayList();

            for(BookDto dto: bookDto){
                bookLIST.add(dto.getId());
            }
            cmbBook.setItems(bookLIST);

        }catch (Exception e){

        }
    }

    private void setMember() {

        try {
            List<UserDto> user = userBo.getAllUser();

            ObservableList<String> userDto = FXCollections.observableArrayList();

            for(UserDto dto : user){
                userDto.add(
                        dto.getId()
                );
            }
            cmbMember.setItems(userDto);

        }catch (Exception e){

        }

    }


    @FXML
    void addOnAction(ActionEvent event) {

        if (ValidateBorrow()) {

            String memberId = (String) cmbMember.getValue();
            String bookId = (String) cmbBook.getValue();
            String status = (String) cmbStstus.getValue();
            LocalDate borrowDate = borrowdDate.getValue();
            LocalDate returndDate = returnDate.getValue();

            try {
                if (bookBo.searchBook(bookId).getStatus().equals("Unavailable") && status.equals("Borrow")) {
                    new Alert(Alert.AlertType.ERROR, "Book Unavailable").show();
                    return;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            BorrowTm borrowTms = new BorrowTm(memberId, bookId, status, borrowDate, returndDate);

            if (borrowTmList.size() >= 3) {
                new Alert(Alert.AlertType.INFORMATION, "Out of limit").show();
                return;
            }

            int index = 0;

            for (BorrowTm borrowTm : borrowTmList) {
                if (borrowTm.getBookId().equals(bookId) && borrowTm.getMemberId().equals(memberId)) {
                    borrowTmList.set(index, borrowTms);
                    return;
                }
                index++;
            }

            borrowTmList.add(new BorrowTm(
                    memberId,
                    bookId,
                    status,
                    borrowDate,
                    returndDate
            ));

            tblBorrow.setItems(borrowTmList);

            genarateNext();
        }

    }

    private boolean ValidateBorrow() {
        String memberId = (String) cmbMember.getValue();

        if(memberId == null){
            new Alert(Alert.AlertType.ERROR,"member is empty").show();
            return false;
        }

        String bookId = (String) cmbBook.getValue();
        if(bookId == null){
            new Alert(Alert.AlertType.ERROR,"Book is empty").show();
            return false;
        }

        String status = (String) cmbStstus.getValue();

        if(status == null){
            new Alert(Alert.AlertType.ERROR,"status is empty").show();
            return false;
        }

        LocalDate borrowDate = borrowdDate.getValue();
        if(borrowDate == null){
            new Alert(Alert.AlertType.ERROR,"borrowDate is empty").show();
            return false;
        }

        LocalDate returndDate = returnDate.getValue();
        if(returndDate == null){
            new Alert(Alert.AlertType.ERROR,"returnDate is empty").show();
            return false;
        }
        return true;
    }

    private void genarateNext() {

        String id = generateOrderDetailId();

        if(detailId.size()==0){
            detailId.add(generateOrderDetailId());
        }else{
            detailId.add(splitID(detailId.get(detailId.size()-1)));
        }

    }

    private String splitID(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("OD0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "OD00" + id;
            } else {
                if (length < 3) {
                    return "OD0" + id;
                } else {
                    return "OD" + id;
                }
            }
        }
        return "OD001";
    }


    @FXML
    void borrowOnAction(ActionEvent event) {

        if(borrowTmList.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"borrow table is Empty").show();
            return;
        }

            Session session = null;
            Transaction transaction = null;

            try {
                String memberId = (String) cmbMember.getValue();
                String bookId = (String) cmbBook.getValue();
                String status = (String) cmbStstus.getValue();
                LocalDate borrowDate = borrowdDate.getValue();
                LocalDate returndDate = returnDate.getValue();


                session = FactoryConfiguration.getInstance().getSession();
                transaction = session.beginTransaction();

                Borrow borrow = new Borrow();
                user.setId(memberId);

                borrow.setBorrowId(txtBorrowId.getText());
                borrow.setDate(borrowDate);
                borrow.setUser(user);

                session.save(borrow);


                int i = 0;
                for (BorrowTm borrowTm : borrowTmList) {
                    session.save(new BookDetails(
                            detailId.get(i),
                            new Book(borrowTm.getBookId()),
                            new Borrow(txtBorrowId.getText()),
                            borrowTm.getStatus(),
                            borrowTm.getBorrowDate(),
                            borrowTm.getReturnDate()

                    ));
                    i++;
                }

                for (BorrowTm borrowTm : borrowTmList) {
                    String id = borrowTm.getBookId();
                    Book book = session.get(Book.class, id);
                    book.setStatus("Unavailable");
                    session.update(book);

                }

                transaction.commit();

                new Alert(Alert.AlertType.CONFIRMATION, "Borrowed Successfully").show();

            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException(e);
            }finally {
                session.close();
            }
            borrowTmList.clear();
            initialize();
            clearFelds();
    }

    private void clearFelds() {
        cmbMember.setValue("");
        cmbBook.setValue("");
        cmbStstus.setValue("");
        txtBookName.clear();
        txtName.clear();
        detailId.clear();
    }


    @FXML
    void closeOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void returnOnAction(ActionEvent event) {
        if(borrowTmList.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"borrow table is Empty").show();
            return;
        }

        Session session = null;
        Transaction transaction = null;

        try {
            String memberId = (String) cmbMember.getValue();
            String bookId = (String) cmbBook.getValue();
            String status = (String) cmbStstus.getValue();
            LocalDate borrowDate = borrowdDate.getValue();
            LocalDate returndDate = returnDate.getValue();


            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Borrow borrow = new Borrow();
            user.setId(memberId);

            borrow.setBorrowId(txtBorrowId.getText());
            borrow.setDate(borrowDate);
            borrow.setUser(user);

            session.save(borrow);


            int i = 0;
            for (BorrowTm borrowTm : borrowTmList) {
                session.save(new BookDetails(
                        detailId.get(i),
                        new Book(borrowTm.getBookId()),
                        new Borrow(txtBorrowId.getText()),
                        borrowTm.getStatus(),
                        borrowTm.getBorrowDate(),
                        borrowTm.getReturnDate()

                ));
                i++;
            }

            for (BorrowTm borrowTm : borrowTmList) {
                String id = borrowTm.getBookId();
                Book book = session.get(Book.class, id);
                book.setStatus("Available");
                session.update(book);

            }

            transaction.commit();

            new Alert(Alert.AlertType.CONFIRMATION, "Return Successfully").show();

        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);

        }finally {
            session.close();
        }
        borrowTmList.clear();
        initialize();
        clearFelds();

    }

    public void memberOnAction(ActionEvent actionEvent) {
        String id  = (String) cmbMember.getValue();

        try{
            UserDto dto = userBo.SearchUser(id);

            txtName.setText(dto.getName());

        }catch (Exception e){

        }

    }

    public void bookOnAction(ActionEvent actionEvent) {
        String id = (String) cmbBook.getValue();

        try{

            BookDto bookDto = bookBo.searchBook(id);
            txtBookName.setText(bookDto.getTitle());

        }catch (Exception e){

        }


    }

    public void deleteOnAction(ActionEvent actionEvent) {
        int focusedIndex = tblBorrow.getSelectionModel().getSelectedIndex();

        borrowTmList.remove(focusedIndex);
        tblBorrow.refresh();
    }

    public void tblOnAction(MouseEvent mouseEvent) {

    }
}
