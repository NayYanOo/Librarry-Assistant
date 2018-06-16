/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.editbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import library.assistant.dao.BookDAO;
import library.assistant.model.Book;

/**
 * FXML Controller class
 *
 * @author Zaw Thet
 */
public class EditbookController implements Initializable {

    @FXML
    private JFXTextField bookidfield;
    @FXML
    private JFXTextField titlefield;
    @FXML
    private JFXTextField authorfield;
    @FXML
    private JFXTextField publisherfield;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXButton cancleBtn;
    private BookDAO bookDAO;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       bookidfield.setDisable(true);
       bookDAO = new BookDAO();
    }    

    @FXML
    private void updateBook(ActionEvent event) {
        
      String id = bookidfield.getText();
      String title = titlefield.getText();
      String author = authorfield.getText();
      String publisher = publisherfield.getText();
      if(id.isEmpty()||title.isEmpty()||author.isEmpty()||publisher.isEmpty()){
          System.out.println("Please fill in all fields");
          return;
      }
      Book book = new Book(id,title,author,publisher);
        try {
            bookDAO.updateBook(book);
            Stage stage = (Stage)updateBtn.getScene().getWindow();
            stage.close();
            System.out.println("Update successful");
        } catch (SQLException ex) {
            Logger.getLogger(EditbookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        
        Stage stage = (Stage)updateBtn.getScene().getWindow();
        stage.close();
    }

    public void setBookInfo(Book selectedBook) {
        
        bookidfield.setText(selectedBook.getId());
        titlefield.setText(selectedBook.getTitle());
        authorfield.setText(selectedBook.getAuthor());
        publisherfield.setText(selectedBook.getPublisher());
    }
    
}
