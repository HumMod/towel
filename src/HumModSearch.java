/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hummodsearch;

import java.io.File;
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
        FadeTransition searchTransition = new FadeTransition(Duration.millis(1), Sample.searchBox);
        searchTransition.setFromValue(1.0);
        searchTransition.setToValue(0.0);
        searchTransition.play();
        FadeTransition helpTransition = new FadeTransition(Duration.millis(1), Sample.helpDisplay);
        helpTransition.setFromValue(1.0);
        helpTransition.setToValue(0.0);
        helpTransition.play();
        FadeTransition backButtonTransition = new FadeTransition(Duration.millis(1), Sample.helpBackButton);
        backButtonTransition.setFromValue(1.0);
        backButtonTransition.setToValue(0.0);
        backButtonTransition.play();
    }    
}
