import java.io.*;
import java.util.*;

public class Player {

    protected int money;
    protected int location;
    protected String name;
    protected int doubles;

    protected boolean enoughMoney = true;
    protected boolean inJail = false;
    protected int turn;

    protected int numHouses;
    protected int numRails;
    protected int numUtil;

    protected int numPurple;
    protected int numLight;
    protected int numMagenta;
    protected int numOrange;
    protected int numRed;
    protected int numYellow;
    protected int numGreen;
    protected int numBlue;


    protected ArrayList<Place> properties;


    public Player(String s, int t) {
	name = s;
       	money = 1500;
	location = 0;
	properties = new ArrayList<Place>();
	doubles = 0;

	turn = t;

       	numHouses = 0;
       	numRails = 0;
       	numUtil = 0;

	numPurple = 0;
	numLight = 0;
	numMagenta = 0;
	numOrange = 0;
	numRed = 0;
	numYellow = 0;
	numGreen = 0;
	numBlue = 0;
    }

    public String printProp() {
	String s = "";
	for (int i = 0; i < properties.size(); i++) {
	    s += (i + 1);
	    s +=  ": " +  properties.get(i);
	    s += '\n';
	}
	return s;
    }

    public void move(int r, int i, ArrayList<Player> p) {
	Player b = p.get( (i + 1) % p.size());	
	//not in jail and no doubles
	if (!(this.inJail) && this.doubles == 0) {
	    if (b.inJail) {
		location += r;
		this.turn--;
		b.turn++;
	    }
	    else {
		location += r;
		this.turn--;
		b.turn++;
	    }
	    if (location > 39) { 
		location -= 40;
		this.money += 200;
	    }   
	}
	//not in jail and doubles
	else if (!(inJail) && this.doubles > 0) {
	    if (b.inJail) {
		location += r;
		this.turn = 0;
		b.turn++;
	    }
	    else
		location += r;
	    if (location > 39) { 
		location -= 40;
		this.money += 200;
	    }
	    this.turn--;
	}
      
	//in jail
	else {
	    if (this.turn == 0) {
		//money -= 50;
		inJail = false;
		System.out.println("You have gotten out of Jail.");
		location += r;
		this.turn--;
		b.turn = 0;
		if (location > 39) {
		    location -= 40;
		    this.money += 200;
		}
	    }
	    else {
		System.out.println("You are in Jail");
	       	b.turn = 0;
	    }
	}

	//you are just visiting
	if (location == 10 && !(inJail)) 
	    System.out.println("You are just visiting");

	//go to jail square
	if (location == 30  || this.doubles == 3)  {
	    location = 10;
	    inJail = true;
	    System.out.println("You are in Jail");
	    b.turn = 0;
	    this.turn = -2;
	}
    }



    public boolean isProperty(Place p) {
       	if (!(p.isUtility()) && !(p.isRailroad())) 
	    return true;
	return false;
    }

    public boolean isMonopoly(Property l) {
	String m = l.mColor;
	int x = 0;
	if (m.equals("purple") || m.equals("blue"))
	    x = 1;
	for (int i = 0; i < properties.size(); i++) {
	    if (properties.get(i).mColor.equals(m)) 
		x++;
	}
	if (x == 3){	 
	    return true;
	}
	return false;
    }

    public void buy(Place p) {
	if (this.money >= p.getCost()) {
	    System.out.println("You bought: " + p.name + "\n\n");
	    money -= p.getCost();
	    p.setOwner(this);
	    properties.add(p);	    
	    if (p.isUtility()) {
		this.numUtil++;
		p.rent = ((Utility)p).utilities.get(this.numUtil + 1);
	    }
	    
	    if (p.isRailroad()) {
		this.numRails++;
		p.rent = ((Railroad)p).rails.get(this.numRails);
	    }
	}
	else {
	    System.out.println("You don't have enough money to buy this!");
	}
    }

    public void getMoney(Location p) {
	if (p.rent < 0 && this.money <= p.rent * -1) {
	    System.out.println("You don't have enough money to do this!");
	    enoughMoney = false;

	}
	else
	    money += p.rent;
    }

    public String getPlace(Board x) {
	String s = "";
	s = x.toString(this.location);
	return s;
    }

    public void payRent(Place p) {
	int rentNow = p.rent;
	if (p.getOwner() != null && !(p.mortgaged)) {
	    if (isProperty(p)){
		if (p.getOwner().isMonopoly((Property)p)){
		    if (((Property)p).numHouses == 0)
			rentNow = ((Property)p).rents.get(6);
		    else
			rentNow = ((Property)p).rents.get(((Property)p).numHouses);
		}
	    }
	    if (this.money >= rentNow) {
		this.money -= rentNow;
		p.getOwner().money += rentNow;
	    }
	    else {
		System.out.println("You don't have enough money to pay rent!");
		enoughMoney = false;
	
	    }
	}
    }

    

    public void buyHouse(Property p) {
	if (this.money >= p.houseCost) {
	    if (this.isMonopoly(p)) {
		p.numHouses++;
		this.money -= p.houseCost;
		p.rent = p.rents.get(p.numHouses);
		System.out.println("You bought a house on " + p + '\n');
	    }
	    else
		System.out.println("You don't have a monopoly of this property, so you cannot buy a house here.");
	 
	}
	else {
	    System.out.println("You don't have enough money to buy this!");
	}
    }

    public void sellHouse(Property f) {
	if (f.numHouses > 0) {
	    System.out.println("You sold a house on " + f + '\n');
	    this.money += f.houseCost / 2;
	    f.numHouses--;
	}
	else 
	    System.out.println("You don't have any houses to sell!");
    }

    public void mortgage(Place p) {
	if (isProperty(p) && ((Property)p).numHouses != 0) {
	    System.out.println("You cannot mortgage until you sell all the houses on this property");
	}
	if (!p.mortgaged) {
	    this.money += p.mortgage;
	    p.mortgaged = true;
	}
	else {
	    if (this.money >= p.mortgage * 1.5) {
		this.money -= p.mortgage * 1.5;
		p.mortgaged = false;
	    }
	    else {
		System.out.println("You don't have enough money to unmortgage this!");
		enoughMoney = false;
	
	    }
	}
    }


   

    public String toString() {
	String s = "";
	s = name;
	return s;
    }
    

    public String toString(Board r) {
	String s = "";
	s = name + '\t';
	s += getPlace(r) + '\n';
	s += "Amount of money: " + money + '\n';

	return s;

    }

  



}




  /*
    public void whatColor(Property a) {
	if (!(a.isUtility()) && !(a.isRailroad())) {
	    if (a.mColor.equals("purple"))
		numPurple++;
	    else if (a.mColor.equals("light blue"))
		numLight++;
	    else if (a.mColor.equals("magenta"))
		numMagenta++;
	    else if (a.mColor.equals("orange"))
		numOrange++;
	    else if (a.mColor.equals("red"))
		numRed++;
	    else if (a.mColor.equals("yellow"))
		numYellow++;
	    else if (a.mColor.equals("green"))
		numGreen++;
	    else
		numBlue++;
	}
	    
    }
    */