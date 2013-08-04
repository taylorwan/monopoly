import java.io.*;
import java.util.*;

public class Cards extends Location {

 

    public Cards(String s) {
	name = s;
	rent = -100 + (int)( Math.random() * 200);
    }
  
    public String toString() {
	String s = "";
	s += name + '\t';;
	if (rent >= 0)
	    s += "You gain: " + rent + '\n';
	else
	    s += "You lose: " + (-1 * rent) + '\n';

	return s;

    }

 


}











