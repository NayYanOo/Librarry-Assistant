/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.addbook;

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
import library.assistant.dao.BookDAO;
import library.assistant.model.Book;

/**
 * FXML Controller class
 *
 * @author Zaw Thet
 */
public class AddbookControllerController implements Initializable {

    @FXML
    private JFXTextField bookidfield;
    @FXML
    private JFXTextField titlefield;
    @FXML
    private JFXTextField authorfield;
    @FXML
    private JFXTextField publisherfield;
    @FXML
    private JFXButton saveBtn;

    private BookDAO bookDAO;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       bookDAO = new BookDAO(); 
       
    }    

//    private void loaderView(ActionEvent event) {
//        System.out.println("Save Book");
//    }

    @FXML
    private void saveView(ActionEvent event) {
        
        String id = bookidfield.getText();
        String title = titlefield.getText();
        String author = authorfield.getText();
        String publisher = publisherfield.getText();
        
        System.out.println("ID:"+id+"Title:"+title+"Author:"+author+"Publisher:"+publisher);
        
        Book book = new Book(id,title,author,publisher);
        
        try {
            bookDAO.saveBook(book);
            System.out.println("Book Saving Success");
            
        } catch (SQLException ex) {
            System.out.println("Failed");
            Logger.getLogger(AddbookControllerController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
    }
    
}
