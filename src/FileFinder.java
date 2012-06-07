/**
 * @(#)FileFinder.java
 *
 *
 * @author
 * @version 1.00 2012/6/1
 */
 import java.io.*;
 import java.util.*;

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

		if(lastIndexTXT != -1)
		{
			FileFinder.txtFound(desiredVar, currentFilePath);
			//System.out.println("folderSearch first if = " + currentFilePath);
		}

		if(lastIndexDES == -1 && lastIndexREF == -1 && lastIndexTXT == -1)
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
				FileFinder.txtFound(desiredVar, filePath);
			//	System.out.println("foldersearch exit test");
			}
		}

    }

    public static String txtFound(String desiredVar, String filePath) throws IOException
    {
    	File finalFile = new File(filePath);
    	//System.out.println("FileFinder -> txtFound -> finalFile name = " + finalFile.getAbsolutePath());
    	String fileName = finalFile.getAbsolutePath();
    	if(fileName.lastIndexOf(".txt") == -1)
    	{
    	//	System.out.println("reached if statement = " + fileName);
    		FileFinder.folderSearch(desiredVar, fileName);
    		return "";
    	}
    	Scanner txtReader = new Scanner(finalFile);
    	int maxIndex = -1;
    	String lines[] = new String[1000];

    	while(txtReader.hasNext())
    	{
    		maxIndex++;
    		lines[maxIndex] = txtReader.nextLine();
    		//if(maxIndex == 0)
    		//	System.out.println(lines[maxIndex]);
    		PathOutput.output(desiredVar, fileName, lines[maxIndex]);
    	}
    	return "";
    }
    }