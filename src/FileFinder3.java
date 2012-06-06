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

    public FileFinder() {
    }

    public static void folderSearch(String desiredVar, String currentFilePath) throws IOException
    {
		int lastIndexDES = currentFilePath.lastIndexOf(".DES");
		int lastIndexREF = currentFilePath.lastIndexOf(".REF");
		int lastIndexTXT = currentFilePath.lastIndexOf(".txt");

		if(lastIndexTXT != -1)
			FileFinder.txtFound(desiredVar, currentFilePath);

		if(lastIndexDES == -1 && lastIndexREF == -1 && lastIndexTXT == -1)
			System.out.println("this program has gone far");

    }

    public static void txtFound(String desiredVar, String filePath) throws IOException
    {
    	File finalFile = new File(filePath);
    	Scanner txtReader = new Scanner(finalFile);
    	int maxIndex = -1;
    	String lines[] = new String[1000];

    	while(txtReader.hasNext())
    	{
    		maxIndex++;
    		lines[maxIndex] = txtReader.nextLine();
    	}

    	for(int readLines = 0; readLines < maxIndex; readLines++)
    	{
    		if(lines[readLines].contains(desiredVar.toLowerCase()))
    		{
    			String pathWay = finalFile.getAbsolutePath();
    			int index = pathWay.indexOf("Display");
    			String newPathWay = pathWay.substring(index + 8);
    			String finalPathWay = newPathWay.replace("\\", " -> ");
    			System.out.println(finalPathWay);
    			readLines = maxIndex;
    		}
    	}
    }


}