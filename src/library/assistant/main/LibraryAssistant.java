/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.main;


import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import library.assistant.database.Database;
import library.assistant.util.MessageBox;

/**
 *
 * @author Zaw Thet
 */
public class LibraryAssistant extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
       
        
        try{
            Database db = Database.getInstance();
        }
        catch(SQLException e){
            
           // System.out.println("Cannot connect to database...");
          
           MessageBox.showAndWaitErrorMessage("Cannot connected to Database");
            e.printStackTrace();
      ///before making MessageBox class//             
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert .setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Cannot connect to database.");
//            //alert.show();
//            alert.showAndWait();
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("/library/assistant/icon/book.png"));
        stage.setTitle("Library Assistant");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
