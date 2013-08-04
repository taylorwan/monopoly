import java.io.*;
import java.util.*;

/*
  This code was written by Mike Zamansky
  and forced into the project using the threat of embarrasing the
  actual project authors even more than usual
*/


public class Intro {
    
    ArrayList<String> text=new ArrayList<String>();

    public void loadFile(String filename)
    {
	String line;
	try {
	    Scanner sc = new Scanner(new File(filename));
	    while (sc.hasNextLine())
		{
		    line = sc.nextLine();
		    text.add(line);
		}
	}
	catch (Exception e) {}
    }

    public Intro() 
    {
	loadFile("text");
    }

    public Intro(String filename)
    {
	loadFile(filename);
    }



    public void pause(int delay)
    {
	try {
	    Thread.sleep(delay);
	}
	catch (Exception e) {}
    }
    
    public void clearScreen()
    {
	System.out.println("[0;9H");
	System.out.println("[2J");
    }


    public void play(int delay)
    {
	int i;
        for (String line : text)
	    {
		pause(delay);
		System.out.println(line);
	    }
    }

    public String toString()
    {
	return ""+text;
    }

}



