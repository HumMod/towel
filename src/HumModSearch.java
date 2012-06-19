/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * It should be noted that all code in "//" comments
 * is designated as tests. When these lines are un-commented,
 * they will allow the user to test how far the search is going
 * before failure if there is a bug with the program
 */
package hummodsearch;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        
        stage.setTitle("HumMod Display Search - a search tool for \"the best, most complete, mathematical model of human physiology ever created.\"");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        Sample.fileInputReceived.setText("To begin, use the \"Open File\" button and find your version\nof the HumMod application. Example: \"HumMod.EXE\"");
        FadeTransition searchTransition = new FadeTransition(Duration.millis(1), Sample.searchBox);
        searchTransition.setFromValue(1.0);                                     //helps keep the search tool and the back button hidden because they aren't
        searchTransition.setToValue(0.0);                                       //needed yet
        searchTransition.play();
        FadeTransition backButtonTransition = new FadeTransition(Duration.millis(1), Sample.helpBackButton);
        backButtonTransition.setFromValue(1.0);
        backButtonTransition.setToValue(0.0);
        backButtonTransition.play();
    }    
}
