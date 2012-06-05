/**
 * @(#)HumModVars.java
 *
 * HumModVars application
 *
 * @author
 * @version 1.00 2012/6/1
 */
 import java.util.*;
 import java.io.*;

public class HumModVars2 {

    public static void main(String[] args) throws IOException{

    	for(int r = 0; r < 7; r--)
    	{
    		Scanner question = new Scanner(System.in);
    		System.out.println("What are you looking for? Enter \"exit\" to terminate program.");
    		String variable = question.nextLine();
    		String varFound;

    		if(variable.equalsIgnoreCase("exit"))
    			r = 9;
    		else
    			System.out.println(VarFinder2.storeInfo(variable));

    	}
    	System.out.println("Thank you for using the HumMod Variable Finder.");
    }
}
