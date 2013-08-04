import java.io.*;
import java.util.*;

public class Board{

    protected ArrayList<Location> places = new ArrayList<Location>();

    public Board() {
	places.add(new Other("Go", 200));

	places.add(new Property("Mediterranean Ave", 60, 30, 50, 2, 10, 30, 90, 160, 250, "purple"));
	places.add(new Cards("Community Chest"));
	places.add(new Property("Baltic Ave", 60, 30, 50, 4, 20, 60, 180, 320, 450, "purple"));
	places.add(new Other("Income Tax", -200));

	places.add(new Railroad("Reading Railroad"));

	places.add(new Property("Oriental Ave", 100, 50, 50, 6, 30, 90, 270, 400, 550, "light blue"));
	places.add(new Cards("Chance"));
	places.add(new Property("Vermont Ave", 100, 50, 50, 6, 30, 90, 270, 400, 550, "light blue"));
	places.add(new Property("Connecticut Ave", 120, 60, 50, 8, 40, 100, 300, 450, 600, "light blue"));

	places.add(new Other("Jail", 0));

	places.add(new Property("St. Charles Place", 140, 70, 100, 10, 50, 150, 450, 625, 750, "magenta"));
	places.add(new Utility("Electric Company"));
	places.add(new Property("States Ave", 140, 70, 100, 10, 50, 150, 450, 625, 750, "magenta"));
	places.add(new Property("Virginia Ave", 160, 80, 100, 12, 60, 180, 500, 700, 900, "magenta"));

	places.add(new Railroad("Pennsylvania Railroad"));

	places.add(new Property("St. James Place", 180, 90, 100, 14, 70, 200, 550, 750, 950, "orange"));
	places.add(new Cards("Community Chest"));
	places.add(new Property("Tennessee Ave", 180, 90, 100, 14, 70, 200, 550, 750, 950, "orange"));
	places.add(new Property("New York Ave", 200, 100, 100, 16, 80, 220, 600, 800, 1000, "orange"));

	places.add(new Other("Free Parking", 0));

	places.add(new Property("Kentucky Ave", 220, 110, 150, 18, 90, 250, 700, 875, 1050, "red"));
	places.add(new Cards("Chance"));
	places.add(new Property("Indiana Ave", 220, 110, 150, 18, 90, 250, 700, 875, 1050, "red"));
	places.add(new Property("Illinois Ave", 240, 120, 150, 20, 100, 300, 750, 925, 1100, "red"));

	places.add(new Railroad("B&O Railroad"));

	places.add(new Property("Atlantic Ave", 260, 130, 150, 22, 110, 330, 800, 975, 1050, "yellow"));
	places.add(new Property("Ventnor Ave", 260, 130, 150, 22, 110, 330, 800, 975, 1050, "yellow"));
	places.add(new Utility("Water Works"));
	places.add(new Property("Marvin Gardens", 280, 140, 150, 24, 120, 360, 850, 1025, 1200, "yellow"));

	places.add(new Other("Go To Jail", 0));

	places.add(new Property("Pacific Ave", 300, 150, 200, 26, 130, 390, 900, 1100, 1275, "green"));
	places.add(new Property("North Carolina Ave", 300, 150, 200, 26, 130, 390, 900, 1100, 1275, "green"));
	places.add(new Cards("Community Chest"));
	places.add(new Property("Pennsylvania Ave", 320, 160, 200, 28, 150, 450, 1000, 1200, 1400, "green"));

	places.add(new Railroad("Short Line"));

	places.add(new Cards("Chance"));
	places.add(new Property("Park Place", 350, 175, 200, 35, 175, 500, 1100, 1300, 1500, "blue"));
	places.add(new Other("Luxury Tax", -75));
	places.add(new Property("Boardwalk", 400, 200, 200, 50, 200, 600, 1400, 1700, 2000, "blue"));
    }


    
    public String printBoard() {
	String s = "";
	for (int i = 0; i < places.size(); i++) {
	    s += places.get(i);
	    if (places.get(i).isProperty())
		s += '\n';
	}
	return s;

    }
    
    
    public String toString(int d) {
	String s = "";
	s +=  places.get(d);
	return s;
    }
    
    public static void main(String[] args) {

	Board b = new Board();


	System.out.println(b);


    }





}