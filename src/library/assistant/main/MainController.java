/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import library.assistant.dao.BookDAO;
import library.assistant.dao.IssueInfoDAO;
import library.assistant.dao.MemberDAO;
import library.assistant.model.Book;
import library.assistant.model.IssueInfo;
import library.assistant.model.Member;
import library.assistant.util.MessageBox;

/**
 *
 * @author Zaw Thet
 */
public class MainController implements Initializable {

    @FXML
    private JFXButton addBookbtn;
    @FXML
    private JFXButton homeBtn;
    @FXML
    private StackPane centerPane;
    @FXML
    private TabPane homeView;
    @FXML
    private JFXButton addMemberBtn;
    @FXML
    private JFXButton bookListBtn;
    @FXML
    private JFXButton memberBtn;
    @FXML
    private JFXTextField bookidField;
    @FXML
    private Text titleText;
    @FXML
    private Text authorText;
    @FXML
    private Text publisherText;
    //DAO creating
    private BookDAO bookDAO;
     
    @FXML
    private JFXTextField memberidField;
    @FXML
    private Text nameText;
    @FXML
    private Text emailText;
    @FXML
    private Text mobileText;
    @FXML
    private Text addressText;
    
    private MemberDAO memberDAO;
    @FXML
    private Text availableText;
    @FXML
    private JFXButton issueBtn;
    private IssueInfoDAO issueInfoDAO;
    @FXML
    private JFXTextField issueBookIDField;
    @FXML
    private Text midText;
    @FXML
    private Text mnameText;
    @FXML
    private Text memailText;
    @FXML
    private Text mmobileText;
    @FXML
    private Text maddressText;
    @FXML
    private Text btitleText;
    @FXML
    private Text bauthorText;
    @FXML
    private Text bpublisherText;
    @FXML
    private Text issueDateText;
    @FXML
    private Text renewCountText;
    @FXML
    private JFXButton renewBtn;
    @FXML
    private JFXButton submissionBtn;
    @FXML
    private MenuItem configItem;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bookDAO = new BookDAO();
        
        memberDAO = new MemberDAO();
        
        issueInfoDAO = new IssueInfoDAO();
    }    

    @FXML
    private void loadAddBookView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/addbook/addbook.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
        
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.initOwner(addBookbtn.getScene().getWindow());
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    private void loadHomeView(ActionEvent event) {
        
        centerPane.getChildren().clear();
        centerPane.getChildren().add(homeView);
    }

    @FXML
    private void loadAddMemberView(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/addmember/addmember.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
    }

    @FXML
    private void loadBookListView(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/booklist/booklist.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
    }

    @FXML
    private void loadMemberView(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/memberlist/memberlist.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
    }

    @FXML
    private void searchBookInfo(ActionEvent event) {
        clearBookCache();
        String id = bookidField.getText();
        if(id.isEmpty()){
            System.out.println("Please enter book id first....");
            return;
        }
        
        try {
            Book book = bookDAO.getBook(id);
            
            if(book!=null){
                titleText.setText(book.getTitle());
                authorText.setText(book.getAuthor());
                publisherText.setText(book.getPublisher());
                boolean available = book.isAvailable();
                if(available){
                    availableText.setText("Available");
                }
                else{
                    availableText.setText("Not Available");
                }
            }
            else{
               // System.out.println("Cannot find any book..");
               MessageBox.showErrorMessage("Cannot find any book id ...");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchMemberInfo(ActionEvent event) {
        
        clearMemberCache();
        String id = memberidField.getText();
        if(id.isEmpty()){
            System.out.println("Please enter member id first....");
            return;
        }
        //System.out.println("Searching member info....");
        //searcing
        try {
            Member member = memberDAO.getMember(id);
            
            if(member!=null){
                nameText.setText(member.getName());
                emailText.setText(member.getEmail());
                mobileText.setText(member.getMobile());
                addressText.setText(member.getAddress());
            }
            else{
               // System.out.println("Cannot find any member..");
                MessageBox.showErrorMessage("Cannot find any member for this id ...");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void clearBookCache() {
        titleText.setText("-");
        authorText.setText("-");
        publisherText.setText("-");
        availableText.setText("-");
    }

    private void clearMemberCache() {
        
        nameText.setText("-");
        mobileText.setText("-");
        emailText.setText("-");
        addressText.setText("-");
    }

    @FXML
    private void issueBook(ActionEvent event) {
        
        String bookId = bookidField.getText();
        String memberId = memberidField.getText();
        
        if(bookId.isEmpty() || memberId.isEmpty()){
            System.out.println("Enter book id and member id");
            return;
        }
        //System.out.println("Book ID:"+bookId+",Member ID:"+memberId);
        try {
            Book book =  bookDAO.getBook(bookId);
            
            if(book.isAvailable()){
               // System.out.println("Saving issue Info..."); 
               
               issueInfoDAO.saveIssueInfo(new IssueInfo(memberId,bookId));
               bookDAO.updateAvailability(bookId, false);
                System.out.println("Book Issue Successful");
               
            }else{
                System.out.println("This book id already issued.");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchIssueBookInfo(ActionEvent event) {
        
        String bookId = issueBookIDField.getText();
         
        if(bookId.isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert .setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter book id first.");
            alert.show();
            return;
        }
        try {
            IssueInfo issueInfo = issueInfoDAO.getIssueInfo(bookId);
            
            if(issueInfo != null){
               Book book = bookDAO.getBook(bookId);
               
               btitleText.setText(book.getTitle());
               bauthorText.setText(book.getAuthor());
               bpublisherText.setText(book.getPublisher());
               
               Member member = memberDAO.getMember(issueInfo.getMemberId());
               mnameText.setText(member.getName());
               memailText.setText(member.getEmail());
               mmobileText.setText(member.getMobile());
               maddressText.setText(member.getAddress());
               
                 
               midText.setText(issueInfo.getMemberId()); 
               issueDateText.setText("Issue Date: "+issueInfo.getIssueDate().toString());
               renewCountText.setText("Renew Count: "+ issueInfo.getRenewCount());
            }
            else{
                 System.out.println("Cannot find any issue info for this book id");
            }
        } catch (SQLException ex) {
           
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void renewBook(ActionEvent event) {
        
        String bookId = issueBookIDField.getText();
        if(bookId.isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert .setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter book id first.");
            alert.show();
            return;
        }
        
        try {
            IssueInfo issueInfo = issueInfoDAO.getIssueInfo(bookId);
            if(issueInfo != null){
                
                issueInfoDAO.updateRenewCount(bookId);
          
            }
        } catch (SQLException ex) {
            System.out.println("Cannot find any book");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void submitBook(ActionEvent event) {
        clearIssueInfo();
        String bookId = issueBookIDField.getText();
        if(bookId.isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert .setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter book id first.");
            alert.show();
            return;
        }
        
        try {
            IssueInfo issueInfo = issueInfoDAO.getIssueInfo(bookId);
            if(issueInfo != null){
                issueInfoDAO.deleteissueInfo(bookId);
                
                bookDAO.updateAvailability(bookId,true);
            }
        } catch (SQLException ex) {
            System.out.println("Cannot find any book");
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearIssueInfo() {
        
        midText.setText("-");
        mnameText.setText("-");
        memailText.setText("-");
        mmobileText.setText("-");
        maddressText.setText("-");
        
        btitleText.setText("-");
        bauthorText.setText("-");
        bpublisherText.setText("-");
        
        issueDateText.setText("-");
        renewCountText.setText("-");
    }

    @FXML
    private void loadDatabaseConfigView(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/dbconfig/databaseconfig.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Database Configuration");
        stage.initOwner(centerPane.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
//        stage.show();
        stage.showAndWait();
        MessageBox.showAndWaitErrorMessage("Please restart your app");
        Stage currentStage = (Stage)centerPane.getScene().getWindow();
        currentStage.close();
        
        
    }
    
}
