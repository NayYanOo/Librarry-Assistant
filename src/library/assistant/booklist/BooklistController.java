/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.booklist;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import library.assistant.dao.BookDAO;
import library.assistant.editbook.EditbookController;
import library.assistant.model.Book;
import library.assistant.util.MessageBox;

/**
 * FXML Controller class
 *
 * @author Zaw Thet
 */
public class BooklistController implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> publisherColumn;
    private BookDAO bookDAO;
    @FXML
    private MenuItem deleteItem;
    @FXML
    private MenuItem editItem;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        bookDAO = new BookDAO();
        initColumn();
        
        loadTableData();
        
      /////frist testing//
        
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
//        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
//        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
//        
//        Book book1 = new Book("B009","who you are","Jane","MoMo Publisher");
//        Book book2= new Book("B008","who you","John","MoMo ");
//        Book book3 = new Book("B007","who ","Mg Mg","MoMo Lay");
//        
//       // List<Book> bookList = new ArrayList<>();
//       //or//
//        ObservableList<Book> bookList = FXCollections.observableArrayList();
//        bookList.add(book1);
//        bookList.add(book2);
//        bookList.add(book3);
//        ////right writing/////
//        ObservableList<Book> oldList = bookTable.getItems();
//        oldList.setAll(bookList);
//        //////right//////
//        
//        //bookTable.getItems().setAll(bookList);

///////////////
    }    

    private void initColumn() {
     
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    }

    private void loadTableData() {
        ///working first testing copying first testin command/////
        try {
            ObservableList<Book> bookList = bookDAO.getBooks();
            bookTable.getItems().setAll(bookList);
        } catch (SQLException ex) {
            Logger.getLogger(BooklistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteBook(ActionEvent event) {
        
        Book  selectedBook = bookTable.getSelectionModel().getSelectedItem();
        
       // System.out.println(selectedBook.getId());firstTesting
//        bookTable.getItems().remove(selectedBook);
//        bookTable.getItems().add(selectedBook);Seeing 

       if(selectedBook != null){
           // before making MessageBox class// 
//         Alert alert  = new Alert(AlertType.CONFIRMATION);
//           alert.setHeaderText(null);
//           alert.setContentText("Are you sure you want to delete this book");
 //          Optional<ButtonType> selectedOption = alert.showAndWait();
           
           Optional<ButtonType> selectedOption = MessageBox.showConfirmMessage("Are you sure you want to delete this book");
       
          if(selectedOption.get() == ButtonType.OK){
             
            try {
                bookDAO.deleteBook(selectedBook.getId());
                bookTable.getItems().remove(selectedBook);
            } catch (SQLException ex) {
                Logger.getLogger(BooklistController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          }
//           /////first testing
//            try {
//                bookDAO.deleteBook(selectedBook.getId());
//                bookTable.getItems().remove(selectedBook);
//                System.out.println(selectedBook.getId());
//            } catch (SQLException ex) {
//                Logger.getLogger(BooklistController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            ////
       }
    }

    @FXML
    private void editBook(ActionEvent event) throws IOException {
        
       // System.out.println("Edit Book");
       //firstTesting //Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/editbook/editbook.fxml"));
       Book  selectedBook = bookTable.getSelectionModel().getSelectedItem();
       if(selectedBook != null){
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/assistant/editbook/editbook.fxml"));
       Parent root = loader.load();
       EditbookController controller = (EditbookController) loader.getController();
       controller.setBookInfo(selectedBook);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(bookTable.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Edit Book");
        //stage.show();
        stage.showAndWait();
        loadTableData();
    }
    
}
}
