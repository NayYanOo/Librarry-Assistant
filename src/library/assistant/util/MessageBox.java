/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Zaw Thet
 */
public class MessageBox {
    public static void showErrorMessage(String message){
         Alert alert = new Alert(Alert.AlertType.ERROR);          
         alert.setHeaderText(null);
         alert.setContentText(message);
           
         alert.showAndWait();
    }
    public static void showAndWaitErrorMessage(String message){
         Alert alert = new Alert(Alert.AlertType.ERROR);          
         alert.setHeaderText(null);
         alert.setContentText(message);
           
         alert.showAndWait();
    }
    public static Optional<ButtonType> showConfirmMessage(String message){
        Alert alert  = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }
            
}
