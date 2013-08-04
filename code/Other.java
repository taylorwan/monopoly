import java.io.*;
import java.util.*;


/*
Go
Free Parking
Jail
Income Tax
Luxury Tax

 */

public class Other extends Location {

 

    public Other(String s, int rents) {
	name = s;
	rent = rents;

    }

    public String toString() {
	String s = "";
	s +=  name + '\t';
	if (rent > 0)
	    s += "You gain: " + rent  + '\n';
	else
	    s += "You lose: " + (rent * -1) + '\n';

	return s;

    }




}