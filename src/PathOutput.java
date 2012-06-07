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
    		int displayIndex = filePathway.indexOf("Display\\");
    		int txtIndex = filePathway.lastIndexOf(".txt");
    		String shortenedFile = filePathway.substring(displayIndex + 8, txtIndex);
    		String theOutput = shortenedFile.replace("\\", " -> ");
    		System.out.println(theOutput);
    		FileFinder.counter = 1;
    	}
    }

}