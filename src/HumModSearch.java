/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hummodsearch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author GrahamWHusband
 */
public class HumModSearch extends Application {
    
    public static void main(String[] args) {
        Application.launch(HumModSearch.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Display.fxml"));
        
        stage.setScene(new Scene(root));
        stage.show();
    }
}
