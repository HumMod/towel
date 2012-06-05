/**
 * @(#)ListCompiler.java
 *
 *
 * @author
 * @version 1.00 2012/6/1
 */
import java.util.*;
import java.io.*;

public class ListCompiler {

	public static String possibilities = "";

    public ListCompiler() {
    }

    public static String compile(String choices, int currentVal)
    {
    	if(currentVal == 1)
    	{
    		possibilities = "";
    	}
    	if(possibilities.equals(""))
    	{
			possibilities = possibilities + choices;
    	}
		else
		{
			possibilities = possibilities + "\n" + choices;
		}
		return possibilities;
    }


}