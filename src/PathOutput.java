/**
 * @(#)PathOutput.java
 *
 *
 * @author
 * @version 1.00 2012/6/7
 */
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;


public class PathOutput {

    public PathOutput() {
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
    						System.out.println(theOutput.substring(0, repeatedInfo));
    					else
    						System.out.println(theOutput);
    					FileFinder.counter = 1;										//this tells "main" that I have found a file that contains

					}
				}
			}
		} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
    }

}