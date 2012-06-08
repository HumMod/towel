/**
 * @(#)FileFinder.java
 *
 *
 * @author
 * @version 1.00 2012/6/1
 */
import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class FileFinder {

	public static int counter = 0;

    public FileFinder() {
    }

    public static void folderSearch(String desiredVar, String currentFilePath) throws IOException
    {
    	//System.out.println("folderSearch test = " + currentFilePath);
		int lastIndexDES = currentFilePath.lastIndexOf(".DES");
		int lastIndexREF = currentFilePath.lastIndexOf(".REF");
		int lastIndexTXT = currentFilePath.lastIndexOf(".txt");

		if(lastIndexDES != -1)
		{
			FileFinder.DESFound(desiredVar, currentFilePath);					//txt file found, time to search read it
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
				FileFinder.DESFound(desiredVar, filePath);
			//	System.out.println("foldersearch exit test");
			}
		}

    }

    public static String DESFound(String desiredVar, String filePath) throws IOException
    {

    	if(filePath.lastIndexOf(".DES") == -1 && filePath.lastIndexOf(".txt") == -1 && filePath.lastIndexOf(".REF") == -1)	//for some reason folders were being sent here
    	{																													//instead of fundamentally changing my code
    		//System.out.println("DES if statement = " + filePath);														//I just catch them here and send them to where they
    		FileFinder.folderSearch(desiredVar, filePath);																	//should be, folderSearch
    		return "";
    	}

    	if(filePath.lastIndexOf(".DES") != -1)
    	{
    		PathOutput.output(desiredVar, filePath);
    		return "";
    	}
    	return "";

    }
}