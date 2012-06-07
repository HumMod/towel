/**
 * @(#)PathOutput.java
 *
 *
 * @author
 * @version 1.00 2012/6/7
 */
 import java.util.*;
 import java.io.*;


public class PathOutput {

    public PathOutput() {
    }

    public static void output(String desiredVar, String filePathway, String currentLine) throws IOException
    {
    	String lowerCaseLine = currentLine.toLowerCase();
    	if(lowerCaseLine.contains(desiredVar.toLowerCase()))
    	{
    		int displayIndex = filePathway.indexOf("Display\\");		//this code just changes the file name into
    		int txtIndex = filePathway.lastIndexOf(".txt");				//usable directions
    		String shortenedFile = filePathway.substring(displayIndex + 8, txtIndex - 1);
    		String theOutput = shortenedFile.replace("\\", " -> ");

    		int repeatedInfo = theOutput.lastIndexOf(" -> ");			//I noticed that the final tab was repeating itself
    		String tabNames = theOutput.substring(repeatedInfo + 4);	//so I had to correct for it here
    		int repeatLength = tabNames.length();						//ex: cell -> cell becomes cell
    		String compareTabs = theOutput.substring(repeatedInfo - repeatLength, repeatedInfo);
    		if(compareTabs.equalsIgnoreCase(tabNames))
    			System.out.println(theOutput.substring(0, repeatedInfo));
    		else
    			System.out.println(theOutput);
    		FileFinder.counter = 1;										//this tells "main" that I have found a file that contains
    	}																//the variable, so I don't tell the user their variable
    }																	//wasn't found

}