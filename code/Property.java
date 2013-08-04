import java.io.*;
import java.util.*;

public class Property extends Place{

    protected int houseCost;
    protected int numHouses;
    protected ArrayList<Integer> rents;





    public Property(String names, int costs, int mortgages, int houses, int rent1, int house1, int house2, int house3, int house4, int hotel, String mcolor) {

	name = names;
	cost = costs;
	mortgage = mortgages;
	houseCost = houses;
	numHouses = 0;
       	owner = null;
	mColor = mcolor;
       


	rents = new ArrayList<Integer>();
	rents.add(rent1);
	rents.add(house1);
	rents.add(house2); 
	rents.add(house3); 
	rents.add(house4);
	rents.add(hotel);
	rents.add(rent1 * 2);

	rent = rents.get(numHouses);

    }

    public boolean isProperty() {
	return true;
    }


    public String toString() {
	String s = "";

	s += name + '\t';
	/*
	s += "Cost: " + cost + '\n';
	s += "Rent: " + rents.get(numHouses) + '\n';
	s += "Mortgage: " + mortgage + '\n';
	s += "Monopoly: " + mColor + '\n';
	*/
	s += "Owner: " + owner + '\t';
	s += "Cost: " + cost + '\t';
	s += "Number of Houses: " + numHouses + '\t';
	s += "Mortgaged: " + mortgaged + '\t';
	s += "Color: " + mColor + '\t';


	return s;


    }

}