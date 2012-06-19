/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hummodsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author GrahamWHusband
 */
public class Sample implements Initializable {
    
    @FXML
    private static Label label;
    
    @FXML
    private static Button OpenFileButton;
    
    @FXML
    public static HBox searchBox;
    
    @FXML
    public static Label fileInputReceived;
    
    @FXML
    public static TextField searchBar;
    
    @FXML
    private static Button searchButton;
                
    @FXML
    private static Button helpButton;
    
    @FXML
    private static Button aboutButton;
        
    @FXML
    public static Label helpDisplay;
    
    @FXML
    private static Label potentialLine;
    
    @FXML
    public static Button helpBackButton;
    
    @FXML
    private static Button aboutBackButton;
    
    private static int counter = 0;
    
    private static String fileInput = "";
    
    public static ArrayList displayableList = new ArrayList();
    
    public static File file;
    
    @FXML
    private void handleFileOpener(ActionEvent event){                   //This method allows the "File Open" button to work

        OpenFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
 
                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Application Files (*.EXE)", "*.EXE"); //I only want users looking for the HumMod app
                fileChooser.getExtensionFilters().add(extFilter);
             
                //Show open file dialog
                file = fileChooser.showOpenDialog(null);
             
                designateFileLocation(file.getAbsolutePath());              
            }
            });
    }
    
    public static void designateFileLocation(String prelimFileLoc){
        int humModIndex = prelimFileLoc.indexOf("HumMod-hummod-standalone-2d02143\\");      //This method tests to see if the designated
        int folderName = "HumMod-hummod-standalone-2d02143\\".length();                     //file is actually the HumMod app
        String fileLocation = prelimFileLoc.substring(0, humModIndex + folderName - 1);
        File testFile = new File(fileLocation);
        String folderFiles[] = testFile.list();
        boolean properInputFound = false;
        
        for(int folderIndex = 0; folderIndex < folderFiles.length; folderIndex++)           //cycle through the folder that the HumMod app is
        {                                                                                   //in to find if it has the necessary files for my app
            if(folderFiles[folderIndex].equalsIgnoreCase("HumMod.EXE") || folderFiles[folderIndex].equalsIgnoreCase("Display"))
            {
                folderIndex = folderFiles.length + 1;
                properInputFound = true;
                fileInput = fileLocation;
                fileInputReceived.setText("Your file input has been successfully received.");   //This tells the user that the app is ready for use
                FadeTransition fileInputTransition = new FadeTransition(Duration.millis(4000), fileInputReceived);
                fileInputTransition.setFromValue(1.0);
                fileInputTransition.setToValue(0.0);
                fileInputTransition.play();
                FadeTransition openButtonTransition = new FadeTransition(Duration.millis(1), OpenFileButton);
                openButtonTransition.setFromValue(1.0);
                openButtonTransition.setToValue(0.0);
                openButtonTransition.play();
                FadeTransition searchButtonTransition = new FadeTransition(Duration.millis(1), searchBox);
                searchButtonTransition.setFromValue(0.0);
                searchButtonTransition.setToValue(1.0);
                searchButtonTransition.play();
            }
            if(folderIndex == folderFiles.length - 1 && !properInputFound)                  //If I have cycled through the entire folder and I couldn't
            {                                                                               //find the files, I tell them to search again
                fileInputReceived.setText("Your file input is invalid.\nPlease give the location of your HumMod application.");
            }
        }
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {     //This method handles all search action
        fileInputReceived.setText("");                                          //by getting characters from the search text field
        //System.out.println("You clicked me!");
        //label.setText("Hello World!");
        CharSequence textFieldInput = searchBar.getCharacters();
        String lineInput = textFieldInput.toString();
        //System.out.println(lineInput);
        storeInfo(lineInput);
        displayPaths();
        
    }
    
    @FXML
    private void handleHelpButton(ActionEvent event) throws IOException {       //This method smoothly displays my help information when
        fileInputReceived.setText("");                                          //the "Help" button is clicked.
        String startup = "When opening the program, use the \"Open File\" button and find your edition of HumMod. It should be in the same folder as its associated files for this tool to work properly.\n\n";
        String generic = "TheHumMod variable Finder will allow you to search through HumMod for various variables. For instance, the Variable Finder can search for \"blood volume\" or \"nerve activity.\" It cannot search specifically for something like \"areterial blood volume.\" While this may seem slightly problematic, the variables displayed in the HumMod application are never used more than a few times, so results displayed for a generic search should not be lengthy.";
        String specific = " Additionally, you can search for specific global names. These are the names that HumMod uses when making calculations. For instance, \"CellProtein.Degradation.\" These are the most specific names used in HumMod and will yeild the most concise results.";
        helpDisplay.setText(startup + generic + specific);
        FadeTransition potentialTransition = new FadeTransition(Duration.millis(1), potentialLine);
        potentialTransition.setFromValue(1.0);
        potentialTransition.setToValue(0.0);
        potentialTransition.play();
        FadeTransition fileInputTransition = new FadeTransition(Duration.millis(1), fileInputReceived);
        fileInputTransition.setFromValue(1.0);
        fileInputTransition.setToValue(0.0);
        fileInputTransition.play();
        FadeTransition helpTransition = new FadeTransition(Duration.millis(1000), helpDisplay);
        helpTransition.setFromValue(0.0);
        helpTransition.setToValue(1.0);
        helpTransition.play();
        FadeTransition backButtonTransition = new FadeTransition(Duration.millis(1), helpBackButton);
        backButtonTransition.setFromValue(0.0);
        backButtonTransition.setToValue(1.0);
        backButtonTransition.play();
        FadeTransition labelTransition = new FadeTransition(Duration.millis(1), label);
        labelTransition.setFromValue(1.0);
        labelTransition.setToValue(0.0);
        labelTransition.play();
    }
    
    @FXML
    private void handleHelpBackButton(ActionEvent event) throws IOException {   //This method handles the back button for both the "About" and
        fileInputReceived.setText("");                                          //"Help" buttons
        FadeTransition potentialTransition = new FadeTransition(Duration.millis(1000), potentialLine);
        potentialTransition.setFromValue(0.0);
        potentialTransition.setToValue(1.0);
        potentialTransition.play();
        FadeTransition fileInputTransition = new FadeTransition(Duration.millis(1000), fileInputReceived);
        fileInputTransition.setFromValue(0.0);
        fileInputTransition.setToValue(1.0);
        fileInputTransition.play();
        FadeTransition helpTransition = new FadeTransition(Duration.millis(1), helpDisplay);
        helpTransition.setFromValue(1.0);
        helpTransition.setToValue(0.0);
        helpTransition.play();
        FadeTransition backButtonTransition = new FadeTransition(Duration.millis(1), helpBackButton);
        backButtonTransition.setFromValue(1.0);
        backButtonTransition.setToValue(0.0);
        backButtonTransition.play();
        FadeTransition labelTransition = new FadeTransition(Duration.millis(1000), label);
        labelTransition.setFromValue(0.0);
        labelTransition.setToValue(1.0);
        labelTransition.play();
    }
    
    @FXML
    private void handleAboutButton(ActionEvent event) throws IOException{       //This method handles action from the "About" button
        fileInputReceived.setText("");
        helpDisplay.setText("I can't exactly say anything about HumMod right now because I don't know what they want me to say, but the Variable Finder is by me, Graham Husband");
        FadeTransition potentialTransition = new FadeTransition(Duration.millis(1), potentialLine);
        potentialTransition.setFromValue(1.0);
        potentialTransition.setToValue(0.0);
        potentialTransition.play();
        FadeTransition fileInputTransition = new FadeTransition(Duration.millis(1), fileInputReceived);
        fileInputTransition.setFromValue(1.0);
        fileInputTransition.setToValue(0.0);
        fileInputTransition.play();
        FadeTransition helpTransition = new FadeTransition(Duration.millis(1000), helpDisplay);
        helpTransition.setFromValue(0.0);
        helpTransition.setToValue(1.0);
        helpTransition.play();
        FadeTransition backButtonTransition = new FadeTransition(Duration.millis(1), helpBackButton);
        backButtonTransition.setFromValue(0.0);
        backButtonTransition.setToValue(1.0);
        backButtonTransition.play();
        FadeTransition labelTransition = new FadeTransition(Duration.millis(1), label);
        labelTransition.setFromValue(1.0);
        labelTransition.setToValue(0.0);
        labelTransition.play();
    }
        
    public static void storeInfo(String desiredVar) throws IOException          //This is where my test starts
    {
        String directoryName = fileInput + "\\Display";
        //System.out.println("made it inside of the storeInfo method");
        File directory = new File(directoryName);
        String str[] = directory.list();                                        //storing all files and folders of "display"

        for(int folder = 0; folder < str.length; folder++)		//allows me to search through all str[]
        {
            int lastIndexDES = str[folder].lastIndexOf(".DES");                 //allows me to ignore DES and REF files
            int lastIndexREF = str[folder].lastIndexOf(".REF");                 //because java can't read them
            int lastIndexTXT = str[folder].lastIndexOf(".txt");

            if(lastIndexDES == -1 && lastIndexREF == -1 && lastIndexTXT == -1)	//searches through folders
            {
		String filePath = directory.getAbsolutePath() + "\\" + str[folder];
		//System.out.println("VarFinder -> filePath continues searching = " + filePath);
		File myFolder = new File(filePath);
		String subFiles[] = myFolder.list();				//creates an array to store info from the folders

		for(int numFiles = 0; numFiles < subFiles.length; numFiles++)
		{
                	String newestPath = filePath + "\\" + subFiles[numFiles];	//allows me to get into the actual file searcher
		//	System.out.println("VarFinder -> newestPath = " + newestPath);
			folderSearch(desiredVar, newestPath);
		}
            }

            if(lastIndexDES != -1)                                              //this is just in case changes are made in the future
            {									//and .DES files are immediately in the display folder
                String filePath = directory.getAbsolutePath() + "\\" + str[folder];
                //System.out.println("VarFinder -> filePath goes to text reader = " + filePath);
                DESFound(desiredVar, filePath);
            }

        }
    }
    
    public static void folderSearch(String desiredVar, String currentFilePath) throws IOException
    {
    	//System.out.println("folderSearch test = " + currentFilePath);
	int lastIndexDES = currentFilePath.lastIndexOf(".DES");
	int lastIndexREF = currentFilePath.lastIndexOf(".REF");
	int lastIndexTXT = currentFilePath.lastIndexOf(".txt");

	if(lastIndexDES != -1)
	{
		DESFound(desiredVar, currentFilePath);					//DES file found, time to read it
	//	System.out.println("folderSearch first if = " + currentFilePath);
	}

	if(lastIndexDES == -1 && lastIndexREF == -1 && lastIndexTXT == -1)		//another folder, so I need to search through it again
	{                                                                               //This if statement also filters out any TXT and REF files
		File folderFile = new File(currentFilePath);
		//System.out.println("second if test = " + currentFilePath);
		String subFiles[] = folderFile.list();

		//for(int r = 0; r < subFiles.length; r++)
		//	System.out.println("second if subFiles = " +subFiles[r]);

		for(int subFilesNum = 0; subFilesNum < subFiles.length; subFilesNum++)
		{
			String filePath = currentFilePath + "\\" + subFiles[subFilesNum];
		//	System.out.println("subFilesNum test = " + filePath);
			DESFound(desiredVar, filePath);
		//	System.out.println("foldersearch exit test");
		}
	}
    }

    public static String DESFound(String desiredVar, String filePath) throws IOException
    {

    	if(filePath.lastIndexOf(".DES") == -1 && filePath.lastIndexOf(".txt") == -1 && filePath.lastIndexOf(".REF") == -1)
    	{
    		//System.out.println("DES if statement = " + filePath);			//for some reason folders were being sent here
    		folderSearch(desiredVar, filePath);                                     //instead of fundamentally changing my code
    		return "";                                                              //I just catch them here and send them to where they shoud be, folderSearch
    	}

    	if(filePath.lastIndexOf(".DES") != -1)
    	{
            if(desiredVar.indexOf(".") == -1)               //This if-else statement tests whether the desired variable is a global or generic name
    		output(desiredVar, filePath);               //they are two different tests so I need to search appropriately
            else
            {   
                String structureName = desiredVar.substring(0, desiredVar.indexOf("."));
                //System.out.println(structureName);
                String variableName = desiredVar.substring(desiredVar.indexOf(".") + 1);
                //System.out.println(variableName);
                structureFind(structureName.toLowerCase(), variableName.toLowerCase(), filePath);
            }
            return "";
    	}
    	return "";

    }
    
    public static void structureFind(String structureName, String variableName, String filePathway) throws FileNotFoundException, IOException
    {
        //System.out.println(structureName + "///////" + variableName);
        boolean structureFound = false;
        boolean variableFound = false;
        try (BufferedReader stream = new BufferedReader(new FileReader(filePathway))) {
            String inFile[] = new String[200];
            //System.out.println("inside buffered reader");
            
            for(int r = 0; r < inFile.length; r++)                              //I read through the file here. The booleans ensure that I find
            {                                                                   //the structure name and the variable name because they are often
                inFile[r] = stream.readLine();                                  //on different lines in the code. ex: "<structurename> CellProtein"
                if(inFile[r] != null && r >= 9)                                 //"<variablename> Degradation"
                {
                    if(inFile[r].toLowerCase().indexOf(structureName) != -1)
                        structureFound = true;
                    if(inFile[r].toLowerCase().indexOf(variableName) != -1)
                        variableFound = true;
                }
                if(structureFound && variableFound)
                {
                    r = inFile.length + 1;
                    int displayIndex = filePathway.indexOf("Display\\");		//this code just changes the file name into
                    int DESIndex = filePathway.lastIndexOf(".DES");			//usable directions
                        String shortenedFile = filePathway.substring(displayIndex + 8, DESIndex);
                        String theOutput = shortenedFile.replace("\\", " / ");

                        displayableList.add(theOutput);
                }
            }
            stream.close();
        }
    }
        
    public static void output(String desiredVar, String filePathway) throws IOException
    {
    	String lowerCaseVar = desiredVar.toLowerCase();                         //this keeps my code from being case-sensitive
    	String inFile[] = new String[200];
	BufferedReader stream;

	try {
            stream = new BufferedReader (new FileReader(filePathway));

            for(int r = 0; r < inFile.length; r++)
            {
                inFile[r] = stream.readLine();
                if(inFile[r] != null && r >= 9)
                {
                    if(inFile[r].contains("<label>") && inFile[r].toLowerCase().contains(lowerCaseVar))
                    {
                        int displayIndex = filePathway.indexOf("Display\\");		//this code just changes the file name into
                        int DESIndex = filePathway.lastIndexOf(".DES");			//usable directions
                        String shortenedFile = filePathway.substring(displayIndex + 8, DESIndex);
                        String theOutput = shortenedFile.replace("\\", " / ");
                        
                        if(theOutput.indexOf("Tree / ") == -1)
                            displayableList.add(theOutput);
                    }
                }
            }
            stream.close();
	} catch (FileNotFoundException e)
	{
	}
    }
    
    public static void displayPaths()
    {
        String display = "";
        if(displayableList.isEmpty())           //Just in case the variable isn't found.
            display = "Your desired variable could not be found. Check your spelling and try again.";
        else
        {
            for(int r = 0; r < displayableList.size(); r++)                     //I add all of the elements in the list together
                display = display + "•  " + displayableList.get(r) + "\n";      //to create my output of potential locations
        }
        label.setText(display);
        displayableList.clear();        //I have to clear the list or else it will continue to add to itself
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
