import java.io.*;
import java.util.*;


public class Utility extends Place{

    protected ArrayList<Integer> utilities;
 

    public Utility(String names){
	name = names;
	cost = 150;
	mortgage = 75;
	owner = null;
	mColor = "utility";

	utilities = new ArrayList<Integer>();
	utilities.add(0);
	utilities.add(getRoll() * 4);
	utilities.add(getRoll() * 10);
	rent = utilities.get(0);
    }

    public boolean isUtility() {
	return true;
    }


}