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

    public static String storeInfo(String desiredVar) throws IOException
    {
    	String stuff = "";
    	int maxVal = 3;

    	for(int r = 1; r < maxVal; r++)
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

			String desiredVariable = "<label> " + desiredVar.toLowerCase() + " </label>";

			for(int j = 0; j <= maxIndx; j++)
			{
				if(text[j].contains(desiredVariable))
				{
					stuff = ListCompiler.compile(filePathR.substring(index + 11, endIndex - 5), r);
					if(j == maxIndx && r == maxVal - 1)
						ListCompiler.possibilities = "";
				}
			}

			//for(int j = 0; j <= maxIndx; j++)
			//{
			//	if(text[j].contains(desiredVar.toLowerCase()))
			//	{
			//			return filePathR.substring(index + 11, endIndex - 5);
			//	}
			//}

    	}
    	int l = 1;
    	if(stuff.equals(""))
    		l = 0;
    	if(l != 0)
    		return stuff;
    	return "Your desired variable could not be found.";
    }

}