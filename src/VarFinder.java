/**
 * @(#)VarFinder.java
 *
 *
 * @author
 * @version 1.00 2012/6/1
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

public class VarFinder {

    public VarFinder() {
    }

    public static void storeInfo(String desiredVar) throws IOException
    {
		String directoryName = "C:\\Users\\GrahamWHusband\\Documents\\Grahams Stuff\\hummod\\HumMod-hummod-standalone-2d02143\\Display";
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
					FileFinder.folderSearch(desiredVar, newestPath);
				}
			}

			if(lastIndexDES != -1)													//this is just in case changes are made in the future
			{																		//and .txt files are immediately in the display folder
				String filePath = directory.getAbsolutePath() + "\\" + str[folder];
				//System.out.println("VarFinder -> filePath goes to text reader = " + filePath);
				FileFinder.DESFound(desiredVar, filePath);
			}

		}
    }
}

