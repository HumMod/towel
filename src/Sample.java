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
    private static Label fileInputReceived;
    
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
    private void handleFileOpener(ActionEvent event){  

        OpenFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
 
                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Application Files (*.EXE)", "*.EXE");
                fileChooser.getExtensionFilters().add(extFilter);
             
                //Show open file dialog
                file = fileChooser.showOpenDialog(null);
             
                designateFileLocation(file.getAbsolutePath());              
            }
            });
    }
    
    public static void designateFileLocation(String prelimFileLoc){
        int humModIndex = prelimFileLoc.indexOf("HumMod-hummod-standalone-2d02143\\");
        int folderName = "HumMod-hummod-standalone-2d02143\\".length();
        String fileLocation = prelimFileLoc.substring(0, humModIndex + folderName - 1);
        File testFile = new File(fileLocation);
        String folderFiles[] = testFile.list();
        boolean properInputFound = false;
        
        for(int folderIndex = 0; folderIndex < folderFiles.length; folderIndex++)
        {
            if(folderFiles[folderIndex].equalsIgnoreCase("HumMod.EXE") || folderFiles[folderIndex].equalsIgnoreCase("Display"))
            {
                folderIndex = folderFiles.length + 1;
                properInputFound = true;
                fileInput = fileLocation;
                fileInputReceived.setText("Your file input has been successfully received.");
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
            if(folderIndex == folderFiles.length - 1 && !properInputFound)
            {
                fileInputReceived.setText("Your file input is invalid.\nPlease give the location of your HumMod application.");
            }
        }
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        fileInputReceived.setText("");
        //System.out.println("You clicked me!");
        //label.setText("Hello World!");
        CharSequence textFieldInput = searchBar.getCharacters();
        String lineInput = textFieldInput.toString();
        //System.out.println(lineInput);
        storeInfo(lineInput);
        displayPaths();
        
    }
    
    @FXML
    private void handleHelpButton(ActionEvent event) throws IOException {
        fileInputReceived.setText("");
        helpDisplay.setText("TheHumMod variable Finder will allow you to search through HumMod for various variables. For instance, the Variable Finder can search for \"blood volume\" or \"nerve activity.\" It cannot search specifically for something like \"areterial blood volume.\" While this may seem slightly problematic, the variables displayed in the HumMod application are never used more than a few times, so results displayed for a generic search should not be lengthy.");
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
        FadeTransition backButtonTransition = new FadeTransition(Duration.millis(1000), helpBackButton);
        backButtonTransition.setFromValue(0.0);
        backButtonTransition.setToValue(1.0);
        backButtonTransition.play();
        FadeTransition labelTransition = new FadeTransition(Duration.millis(1), label);
        labelTransition.setFromValue(1.0);
        labelTransition.setToValue(0.0);
        labelTransition.play();
    }
    
    @FXML
    private void handleHelpBackButton(ActionEvent event) throws IOException {
        fileInputReceived.setText("");
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
    private void handleAboutButton(ActionEvent event) throws IOException{
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
        FadeTransition backButtonTransition = new FadeTransition(Duration.millis(1000), helpBackButton);
        backButtonTransition.setFromValue(0.0);
        backButtonTransition.setToValue(1.0);
        backButtonTransition.play();
        FadeTransition labelTransition = new FadeTransition(Duration.millis(1), label);
        labelTransition.setFromValue(1.0);
        labelTransition.setToValue(0.0);
        labelTransition.play();
    }
        
    public static void storeInfo(String desiredVar) throws IOException
    {
        if(new File(fileInput).exists() == false)
            fileInputReceived.setText("You cannot begin searching until you have offered a valid file location for HumMod.");
	else
        {
            String directoryName = fileInput + "\\Display";
            //System.out.println("made it inside of the storeInfo method");
            File directory = new File(directoryName);
            String str[] = directory.list(); 		//storing all files and folders of "display"

            for(int folder = 0; folder < str.length; folder++)		//allows me to search through all str[]
            {
                int lastIndexDES = str[folder].lastIndexOf(".DES");	//allows me to ignore DES and REF files
                int lastIndexREF = str[folder].lastIndexOf(".REF");	//because java can't read them
		int lastIndexTXT = str[folder].lastIndexOf(".txt");

		if(lastIndexDES == -1 && lastIndexREF == -1 && lastIndexTXT == -1)	//searches through folders
		{
			String filePath = directory.getAbsolutePath() + "\\" + str[folder];
			//System.out.println("VarFinder -> filePath continues searching = " + filePath);
			File myFolder = new File(filePath);
			String subFiles[] = myFolder.list();							//creates an array to store info from the folders

			for(int numFiles = 0; numFiles < subFiles.length; numFiles++)
			{
				String newestPath = filePath + "\\" + subFiles[numFiles];	//allows me to get into the actual file searcher
			//	System.out.println("VarFinder -> newestPath = " + newestPath);
				folderSearch(desiredVar, newestPath);
			}
		}

		if(lastIndexDES != -1)													//this is just in case changes are made in the future
		{																		//and .txt files are immediately in the display folder
			String filePath = directory.getAbsolutePath() + "\\" + str[folder];
			//System.out.println("VarFinder -> filePath goes to text reader = " + filePath);
			DESFound(desiredVar, filePath);
		}

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
		DESFound(desiredVar, currentFilePath);					//txt file found, time to search read it
	//	System.out.println("folderSearch first if = " + currentFilePath);
	}

	if(lastIndexDES == -1 && lastIndexREF == -1 && lastIndexTXT == -1)		//another folder, so I need to search through it again
	{
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

    	if(filePath.lastIndexOf(".DES") == -1 && filePath.lastIndexOf(".txt") == -1 && filePath.lastIndexOf(".REF") == -1)	//for some reason folders were being sent here
    	{																													//instead of fundamentally changing my code
    		//System.out.println("DES if statement = " + filePath);														//I just catch them here and send them to where they
    		folderSearch(desiredVar, filePath);																	//should be, folderSearch
    		return "";
    	}

    	if(filePath.lastIndexOf(".DES") != -1)
    	{
            if(desiredVar.indexOf(".") == -1)
    		output(desiredVar, filePath);
            else
            {   
                String structureName = desiredVar.substring(0, desiredVar.indexOf("."));
                String variableName = desiredVar.substring(desiredVar.indexOf(".") + 1);
                System.out.println(structureName + "//////" + variableName);
                structureFind(structureName.toLowerCase(), variableName.toLowerCase(), filePath);
            }
            return "";
    	}
    	return "";

    }
    
    public static void structureFind(String structureName, String variableName, String filePathway) throws FileNotFoundException, IOException
    {
        boolean structureFound = false;
        boolean variableFound = false;
        try (BufferedReader stream = new BufferedReader(new FileReader(filePathway))) {
            String inFile[] = new String[200];
            //System.out.println("made it to structure find method");
            
            for(int r = 0; r < inFile.length; r++)
            {
                inFile[r] = stream.readLine();
                if(inFile[r] != null && r >= 9)
                {
                    //System.out.println("made it inside first if statement");
                    if(inFile[r].toLowerCase().indexOf(structureName) != -1)
                        structureFound = true;
                    if(inFile[r].toLowerCase().indexOf(variableName) != -1)
                        variableFound = true;
                }
                if(structureFound && variableFound)
                {
                    r = inFile.length + 1;
                    int displayIndex = filePathway.indexOf("Display\\");		//this code just changes the file name into
                    int DESIndex = filePathway.lastIndexOf(".DES");				//usable directions
                        String shortenedFile = filePathway.substring(displayIndex + 8, DESIndex);
                        String theOutput = shortenedFile.replace("\\", " / ");

                        int repeatedInfo = theOutput.lastIndexOf(" / ");			//I noticed that the final tab was repeating itself
                        String tabNames = theOutput.substring(repeatedInfo + 4);	//so I had to correct for it here
                        int repeatLength = tabNames.length();						//ex: cell -> cell becomes cell
                        String compareTabs = theOutput.substring(repeatedInfo - repeatLength, repeatedInfo);
                        if(compareTabs.equalsIgnoreCase(tabNames))
                    {
                        //System.out.println(theOutput);
                        displayableList.add(theOutput.substring(0, repeatedInfo));
                    }
                        else
                    {
                        //System.out.println(theOutput);
                        displayableList.add(theOutput);
                    }
                        counter = 1;
                }
            }
        }
    }
    
    public static void output(String desiredVar, String filePathway) throws IOException
    {
    	String lowerCaseVar = desiredVar.toLowerCase();
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
    					int DESIndex = filePathway.lastIndexOf(".DES");				//usable directions
    					String shortenedFile = filePathway.substring(displayIndex + 8, DESIndex);
    					String theOutput = shortenedFile.replace("\\", " / ");

    					int repeatedInfo = theOutput.lastIndexOf(" / ");			//I noticed that the final tab was repeating itself
    					String tabNames = theOutput.substring(repeatedInfo + 4);	//so I had to correct for it here
    					int repeatLength = tabNames.length();						//ex: cell -> cell becomes cell
    					String compareTabs = theOutput.substring(repeatedInfo - repeatLength, repeatedInfo);
    					if(compareTabs.equalsIgnoreCase(tabNames))
                                        {
                                            //System.out.println(theOutput);
                                            displayableList.add(theOutput.substring(0, repeatedInfo));
                                        }
    					else
                                        {
                                            //System.out.println(theOutput);
                                            displayableList.add(theOutput);
                                        }
    					counter = 1;										//this tells "main" that I have found a file that contains

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
        int madeupstuff;
        if(new File(fileInput).exists() == false)
            madeupstuff = 0;
        else
        {
            String display = "";
            for(int r = 0; r < displayableList.size(); r++)
            {
                display = display + (r + 1) + ". " + displayableList.get(r) + "\n";
            }
            if(display.equals(""))
                display = "Your desired variable could not be found. Check your spelling and try again.";
            label.setText(display);
            displayableList.clear();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
