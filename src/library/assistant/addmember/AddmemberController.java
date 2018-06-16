/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.addmember;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import library.assistant.dao.MemberDAO;
import library.assistant.model.Member;

/**
 * FXML Controller class
 *
 * @author Zaw Thet
 */
public class AddmemberController implements Initializable {

    @FXML
    private JFXTextField memberidField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField mobileField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXButton saveBtn;
    
    private MemberDAO memberDAO;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        memberDAO = new MemberDAO();
    }    

    @FXML
    private void saveMember(ActionEvent event) {
        
        String id = memberidField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String mobile = mobileField.getText();
        String address = addressField.getText();
        
        System.out.println(id+":"+name+":"+email+":"+mobile+":"+address);
        
        Member member = new Member(id,name,email,mobile,address);
        
        try {
            memberDAO.saveMember(member);
            //System.out.println("Saving member successful");
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert .setTitle("Successful");
            alert.setHeaderText(null);
            alert.setContentText("Member adding success.");
            alert.show();
            
        } catch (SQLException ex) {
           // System.out.println("Saving Failed");//firstTesting
           
            Alert alert = new Alert(AlertType.ERROR);
            alert .setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Member adding failed.");
            alert.show();
            Logger.getLogger(AddmemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
