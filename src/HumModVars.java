/**
 * @(#)HumModVars.java
 *
 * HumModVars application
 *
 * @author
 * @version 1.00 2012/6/1
 */

 /**
  * all code that has been made into a comment
  * are testing codes to allow the user to see what they are doing
  * and where they are in the progression of the program
  * simply remove the comment marks to test the file
  */
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class HumModVars {

    public static void main(String[] args) throws IOException{

    	for(int r = 0; r < 7; r--)								//creates an infinite loop so the user doesn't have to restart for
    	{														//each new variable
    		Scanner question = new Scanner(System.in);			//Asking the user what they want
    		System.out.println("What are you looking for? Enter \"exit\" to terminate program.");
    		String variable = question.nextLine();

    		if(variable.equalsIgnoreCase("exit"))
    		{
    			r = 9;											//exits the loop
    			FileFinder.counter = 1;
    		}
    		else
    			VarFinder.storeInfo(variable);					//accesses the search function
    		if(FileFinder.counter == 0)
    			System.out.println("Sorry, your desired variable could not be found.");

    	}
    	System.out.println("Thank you for using the HumMod Variable Finder.");
    }
}
