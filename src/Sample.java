/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//If you delete this, you have made it to best save 1
//If you delete this, you have made it to best save 2
//If you delete this, you have made it to best save 3
//If you delete this, you have made it to best save 4
//If you delete this, you have made it to best save 5, which will display a pathway in the window
package hummodsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author GrahamWHusband
 */
public class Sample implements Initializable {
    
    @FXML
    private static Label label;
    
    @FXML
    private static TextField searchBar;
    
    @FXML
    private static Button searchButton;
    
    private static int counter = 0;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
        CharSequence textFieldInput = searchBar.getCharacters();
        String lineInput = textFieldInput.toString();
        //System.out.println(lineInput);
        storeInfo(lineInput);
    }
    
    public static void storeInfo(String desiredVar) throws IOException
    {
	String directoryName = "C:\\Users\\GrahamWHusband\\Documents\\Grahams Stuff\\hummod\\HumMod-hummod-standalone-2d02143\\Display";
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
    		output(desiredVar, filePath);
    		return "";
    	}
    	return "";

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
    						label.setText(theOutput.substring(0, repeatedInfo));
    					else
    						label.setText(theOutput);
    					counter = 1;										//this tells "main" that I have found a file that contains

				}
			}
		}
	} catch (FileNotFoundException e)
	{
	}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
