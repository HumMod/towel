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

		for(int z = 0; z < str.length; z++)
			System.out.println(str[z]);

		for(int folders = 0; folders < str.length; folders++)
		{
			if(str[folders].contains(".txt") == false || str[folders].contains(".DES") ==  false || str[folders].contains(".REF") == false)
			{
				File subdirectory = new File(directory, str[folders]);
				String filePath = subdirectory.getAbsolutePath();
				String foldersList[] = subdirectory.list();

				for(int files = 0; files < foldersList.length; files++)
				{
					String newFile = filePath + "\\" + foldersList[folders];
					System.out.println(newFile);
					//VarFinder.reachingFurther(desiredVar, newFile);
				}
			}
			else
			{
				File finalFile = new File(directory, str[folders]);
				String filePath = finalFile.getAbsolutePath();
				System.out.println(filePath);
			}
		}
	}

	public static void reachingFurther(String desiredVar, String pathWay)
	{
		File folder = new File(pathWay);
		String str[] = folder.list();

		for(int folders = 0; folders < str.length; folders++)
		{
			if(str[folders].contains(".txt") == false || str[folders].contains(".DES") == false || str[folders].contains(".REF") == false)
			{
				File subdirectory = new File(folder, str[folders]);
				String filePath = subdirectory.getAbsolutePath();
				String foldersList[] = subdirectory.list();

				for(int files = 0; files < foldersList.length; files++)
				{
					System.out.println(foldersList[files]);
					String newFile = filePath + "\\" + foldersList[folders];
					System.out.println(newFile);
					reachingFurther(desiredVar, newFile);
				}
			}
			else
			{
				File finalFile = new File(folder, str[folders]);
				String filePath = finalFile.getAbsolutePath();
				System.out.println(filePath);
			}
		}
	}
}

