import java.io.*;
import java.util.*;

public class Place extends Location{

    protected int cost;
    protected int mortgage;
    protected Player owner;
    protected boolean mortgaged = false;
    protected String mColor;

    public String toString() {
	String s = "";

	s +=  name + '\t';
	s += "Owner: " + owner + '\t';
	s += "Cost: " + cost + '\t';

	return s;

    }

    public int getRent() {
	return rent;
    }
    
    public Player getOwner() {
	return owner;
    }

    public int getCost() {
	return cost;
    }

    public boolean isPlace() {
	return true;
    }

    public boolean isRailroad() {
	return false;
    }

    public boolean isUtility() {
	return false;
    }

    public boolean isProperty() {
	return false;
    }

    public boolean isOther() {
	return false;
    }

    public void setOwner(Player p) {
	owner = p;
    }

    public static int getRoll() {
	return MonopolyAP.Roll();
    }

    /*  
    public static void main(String[] args) {
	Place p = new Property("Mediterranean Ave", 60, 30, 50, 2, 10, 30, 90, 160, 250, "purple");


	System.out.println(p);
	System.out.println(getRoll());
    }

    
    */

}





/*


	s += "Cost: " + cost + '\n';
      	s += "Rent: " + rent + '\n';
       	s += "Mortgage: " + mortgage + '\n';






 */