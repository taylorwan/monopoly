import java.io.*;
import java.util.*;

public class Railroad extends Place{

    protected ArrayList<Integer> rails;


    public Railroad(String names) {
	name = names;
	cost = 200;
	mortgage = 100;
	owner = null;
	mColor = "railroad";


	rails = new ArrayList<Integer>();
	rails.add(0);
	rails.add(25);
	rails.add(50);
	rails.add(100);
	rails.add(200);

	rent = rails.get(0);


    }

    public boolean isRailroad() {
	return true;
    }






}