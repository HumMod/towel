/**
 * @(#)VarFinder.java
 *
 *
 * @author
 * @version 1.00 2012/6/1
 */
import java.util.*;
import java.io.*;

public class VarFinder2 {

    public VarFinder2() {
    }

    public static String storeInfo(String desiredVar) throws IOException
    {
    	for(int r = 1; r < 3; r++)
    	{
    		String fileName = "C:\\Users\\GrahamWHusband\\Documents\\Grahams Stuff\\hummod\\HumMod-hummod-standalone-2d02143\\Display\\Physiology\\O2\\Blood\\" + r + ".txt";
			String filePath = fileName;
			String filePathR = filePath.replace("\\", " -> ");	//uses the actual file directory to send back pathway
			int index = filePathR.indexOf("Display");
			int endIndex = filePathR.lastIndexOf(".txt");

    		Scanner sf = new Scanner(new File(fileName));//the scanner that reads the file
			int maxIndx = -1;
			String text[] = new String[1000];

			while(sf.hasNext( ))
			{
				maxIndx++;
				text[maxIndx] = sf.nextLine().toLowerCase(); //stores file text in an array, "text"
			}

			sf.close( ); //closes file

			for(int j = 0; j <= maxIndx; j++)
			{
				if(text[j].contains(desiredVar.toLowerCase()))
				{
					return filePathR.substring(index + 11, endIndex - 5);	//adds potential choices to a list
				}															//eliminates unwanted paths like "C\:"
			}

    	}
    	return "Your desired variable could not be found.";
    }

}