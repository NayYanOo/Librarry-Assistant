/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.dbconfig;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import library.assistant.util.DatabaseConfigManager;
import library.assistant.util.DatabaseProperty;

/**
 * FXML Controller class
 *
 * @author Zaw Thet
 */
public class DatabaseconfigController implements Initializable {

    
    @FXML
    private TextField hostField;
    @FXML
    private TextField passwordField;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private TextField nameField;
    @FXML
    private Spinner<Integer> portSpinner;
    private DatabaseConfigManager dbManager;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         dbManager = new DatabaseConfigManager();
         
         DatabaseProperty dbProp = dbManager.getDatabaseProperties();
         hostField.setText(dbProp.getHost());
         nameField.setText(dbProp.getUser());
         passwordField.setText(dbProp.getPassword());
        //SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3300,3320,3306);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3300,3320,Integer.parseInt(dbProp.getPort()));
        portSpinner.setValueFactory(valueFactory);
       
    }    

    @FXML
    private void saveDatabaseConfig(ActionEvent event) {
        
        String host = hostField.getText();
        String port = portSpinner.getValue().toString();
        String user = nameField.getText();
        String password = passwordField.getText();
        
        DatabaseProperty dbProperty = new DatabaseProperty(host,port,user,password);
        dbManager.saveDatabaseProperties(dbProperty);
        Stage stage = (Stage)saveBtn.getScene().getWindow();
        stage.close();
        System.out.println("successful");
    }
    
}
