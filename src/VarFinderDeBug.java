/**
 * @(#)VarFinder.java
 *
 *
 * @author
 * @version 1.00 2012/6/1
 */
import java.util.*;
import java.io.*;

public class VarFinder {

    public VarFinder() {
    }

    public static void storeInfo(String desiredVar) throws IOException
    {
		String directoryName = "C:\\Users\\GrahamWHusband\\Documents\\Grahams Stuff\\hummod\\HumMod-hummod-standalone-2d02143\\Display";
		File directory = new File(directoryName);
		String str[] = directory.list(); //just going into the directory of all files inside "display"

		for(int folder = 0; folder < str.length; folder++)
		{
			int lastIndexDES = str[folder].lastIndexOf(".DES");
			int lastIndexREF = str[folder].lastIndexOf(".REF");
			int lastIndexTXT = str[folder].lastIndexOf(".txt");

			if(lastIndexDES == -1 && lastIndexREF == -1 && lastIndexTXT == -1)
			{
				String filePath = directory.getAbsolutePath() + "\\" + str[folder];
				System.out.println("VarFinder -> filePath continues searching = " + filePath);
				File myFolder = new File(filePath);
				String subFiles[] = myFolder.list();

				for(int numFiles = 0; numFiles < subFiles.length; numFiles++)
				{
					String newestPath = filePath + "\\" + subFiles[numFiles];
					System.out.println("VarFinder -> newestPath = " + newestPath);
					FileFinder.folderSearch(desiredVar, newestPath);
				}
			}

			if(lastIndexTXT != -1)
			{
				String filePath = directory.getAbsolutePath() + "\\" + str[folder];
				System.out.println("VarFinder -> filePath goes to text reader = " + filePath);
				FileFinder.txtFound(desiredVar, filePath);
			}

		}
    }
}

