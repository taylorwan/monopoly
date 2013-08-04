import java.io.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;





public class MonopolyAP {


    private InputStreamReader isr;
    private BufferedReader br;
   
    public static int roll;
    public static int die1;
    public static int die2;
    public static ArrayList<Player> players;


    public MonopolyAP() {
	players = new ArrayList<Player>();
	isr = new InputStreamReader(System.in);
	br = new BufferedReader(isr);


	roll = 0;

    }

  public void clearScreen()
    {
	System.out.println("[0;9H");
	System.out.println("[2J");
    }


    public static int Roll(Player s) {
        die1 = (int) (Math.random() * 6) + 1;
	die2 = (int) (Math.random() * 6) + 1;
	if (die1 == die2){
	    System.out.println("You rolled a double");
	    s.doubles++;
	    s.turn++;
	}
	if (s.inJail || die1 != die2) 
	    s.doubles = 0;

       	roll = die1 + die2;
	return roll;
    }

    public static int Roll() {
	return die1 + die2;
    }

    public int hasLost() {
	for (int i=0;i<players.size();i++)
	    if (players.get(i).money < 0) 
		return i;
	return -1;
    }

  

    public Location getPlace(Player w, Board q) {
	return q.places.get(w.location);
    }


    public void payMoney(int i, Board z) {
	Player a = players.get(i);
	if (a.enoughMoney) {
	    //paying rent
	    if (getPlace(a, z).isPlace() && getPlace(a, z).getOwner() != null && getPlace(a, z).getOwner() != a)
		a.payRent((Place)getPlace(a, z));
	    //Chance/Other cards
	    else if(z.places.get(a.location).isOther()) {
		a.getMoney(getPlace(a, z));

	    }
	}
	else {
	    notEnough(a);
	}
    }


  public void notEnough(Player p) {
	if (!(p.enoughMoney)) {
	    System.out.println("You don't have enough money!  What would you like to do?");
	    System.out.println("\n1. Sell Houses \n2. Mortgage Properties \n3. Declare Bankrupcy");
	    String f = read();
	    if (f.equals("1")) {  
		System.out.println(p.printProp());
		System.out.println("Which property would you like to sell a house on?");
		int q = Integer.parseInt(read());
		p.sellHouse((Property)p.properties.get(q - 1));
	    }
	    else if (f.equals("2")) {
		System.out.println(p.printProp());
		System.out.println("Which property would you like to mortgage or unmortgage?");
		int e = Integer.parseInt(read());
		p.mortgage(p.properties.get(e - 1));
	    }
	    else if (f.equals("3")) {
		p.money = 0;
	    }
	    else {
		System.out.println("Error, invalid input");
		System.out.println("\n1. Sell Houses \n2. Mortgage Properties \n3. Declare Bankrupcy");
		read();
	    }
	}
    }

   
    public void play2(int i, Board x) {
	Player r = players.get(i);
	while (r.inJail || r.turn == 0) {
	    this.Roll(r);
	    System.out.println("You rolled a: " + roll);

	    if (r.inJail) {
		System.out.println("You're in jail");
		r.move(0, i, players);
		return;
	    }	

	    r.move(roll, i, players);
	    payMoney(i, x);
	    System.out.println(r.toString(x));

	    //if this is an unbought place
	    if (getPlace(r, x).isPlace() && getPlace(r, x).getOwner() == null){
		System.out.println(menuAfterProp());
		if (read().equals("1"))
		    r.buy((Place)getPlace(r, x));
		else
		    System.out.println("You did not buy " + getPlace(r, x) + "\n\n");
		
	    }
	}

    }
	
    


    public String menuBefore() {
	String s = "What would you like to do?" + "\n\n1. Roll" + '\n' + "2. Mortgage/Un-mortgage Properties" + '\n' + "3. Buy Houses/Hotels" + '\n' + "4. Trade" + '\n' + "5. See Your Properties" + '\n' + "6. Print Board" + "\n\nSelection: ";
	return s;
    }

    public String menuAfterProp() {
	String s = "What would you like to do?";
	s += "\n1. Buy \n2. Do not buy";
	return s;
    }

    public String menuBuySell() {
	String s = "Would you like to buy or sell?";
	s += "\n1. Buy \n2. Sell";
	return s;
    }

    public String menuTrade() {
	String s = "Would you like to trade...";
	s += "\n1. One of your properties for one of your opponents properties? \n2. One of your properties for money from your opponent? \n3. Money from you for one of your opponents properties?";
	return s;
    }


    public void trade(int i, String v) {
	Player k = players.get(i);
	Player o = null;
	int r = 0;
	String g = "";
	if (i == 1) {
	    o = players.get(0);
	    r = 0;
	}
	else {
	    o = players.get(1);
	    r = 1;
	}
	Place c = null;
	Place d = null;
	int s = 0;
	//player with index i (player k) is player initiating the trade
	//player o is the opposing player
	//property for property
	if (v.equals("1")) {
	    System.out.println("What property do you want to trade?");
	    System.out.println(k.printProp());
	    g = read();
	    c = k.properties.get(Integer.parseInt(g) - 1);
	    System.out.println("What property do you want from your opponent?");
	    System.out.println(o.printProp());
	    g = read();
	    d = o.properties.get(Integer.parseInt(g) - 1);
	    System.out.println("Player " + (i + 1) + " wants " + c + " from Player " + (r + 1) + " in exchange for " + d);
	    System.out.println("Does Player " + (r + 1) + " accept the trade?");
	    System.out.println("1. Yes \n2. No");
	    g = read();
	    if (g.equals("1")) {
		k.properties.remove(c);
		k.properties.add(d);
		d.setOwner(k);
		o.properties.remove(d);
		o.properties.add(c);
		c.setOwner(o);
		//	g = "7";
	    }
	}

	else if (v.equals("2")) {
	    System.out.println("What property do you want to trade?");
	    System.out.println(k.printProp());
	    g = read();
	    c = k.properties.get(Integer.parseInt(g) - 1);
	    System.out.println("How much money do you want from your opponent?");
	    g = read();
	    s = Integer.parseInt(g);

	    System.out.println("Player " + (i + 1) + " wants " + c + " from Player " + (r + 1) + " in exchange for $" + s);
	    System.out.println("Does Player " + (r + 1) + " accept the trade?");
	    System.out.println("1. Yes \n2. No");
	    g = read();
	    if (g.equals("1")) {
		k.properties.remove(c);
		o.properties.add(c);
		c.setOwner(o);
		o.money -= s;
		k.money += s;
	    }
	}

	else if (v.equals("3")) {
	    System.out.println("Which property do you want from your opponent?");
	    System.out.println(o.printProp());
	    g = read();
	    c = o.properties.get(Integer.parseInt(g) - 1);
	    System.out.println("How much money do you want to give opponent?");
	    g = read();
	    s = Integer.parseInt(g);

	    System.out.println("Player " + (i + 1) + " wants " + c + " from Player " + (r + 1) + " in exchange for $" + s);
	    System.out.println("Does Player " + (r + 1) + " accept the trade?");
	    System.out.println("1. Yes \n2. No");
	    g = read();
	    if (g.equals("1")) {
		o.properties.remove(c);
		k.properties.add(c);
		c.setOwner(k);
		k.money -= s;
		o.money += s;
	
	    }
	}

    }
    
    
    public String read() {
	String d = "";
	try {
	    d = br.readLine();
	}
	catch (IOException x) { }
	return d;
    }

   
    public void newGame( Board c) {
	String s = "";
	s = "Welcome to Monopoly! \nThe first player will be Player 1, and the second player will be Player 2.  \n\n";
	System.out.println(s);

	while (hasLost() == -1) {
	    clearScreen();
	    for (int i=0;i<players.size();i++) {		
		Player a = players.get(i);
		for (int t = 0; t < players.size(); t++) {
		    Player f = players.get(t);
		    System.out.println("Player " + (t + 1) + "'s Status: " + "\nName and Location: " + f.toString(c) + "\nProperties Owned:\n" + f.printProp() + "\n\n");
		}
	
		s = "Name and Location: \n" + a.toString(c) + menuBefore();
		System.out.println(s); 
		String g = read(); 
		while (!(g.equals("1"))) {		    
		    if (g.equals("2")){
			System.out.println(a.printProp());
			if (a.properties.size() == 0) {
			    System.out.println("You don't have any properties to mortgage!");
			    s = "Name and Location: \n" + a.toString(c) + menuBefore();
			    System.out.println(s);
			    g = read();
			}
			else {
			    s = "Which property would you like to mortgage or unmortgage?";
			    System.out.println(s);
			    int e = Integer.parseInt(read());
			    a.mortgage(a.properties.get(e - 1));
			    s = "Name and Location: \n" + a.toString(c) + menuBefore();
			    System.out.println(s);
			    g = read();
			}
		    }
		    else if (g.equals("3")) {
			System.out.println(menuBuySell());
			g = read();
			int l = Integer.parseInt(g);
			System.out.println(a.printProp());
			if (g.equals("1")) {
			    s = "Which peoperty would you like to buy a house on?";
			    System.out.println(s);
			    int q = Integer.parseInt(read());
			    a.buyHouse((Property)a.properties.get(q - 1));
			}
			else if (g.equals("2")) {
			    s = "Which property would you like to sell a house on?";
			    System.out.println(s);
			    int q = Integer.parseInt(read());
			    a.sellHouse((Property)a.properties.get(q - 1));
			}
			s = "Name and Location: \n" + a.toString(c) + menuBefore();
			System.out.println(s);
			g = read();
		    }

		    else if (g.equals("4")) {
			System.out.println("What would you like to trade?");
			System.out.println(menuTrade());
			g = read();
			int u = i;
			trade(u, g);
			g = "7";
		    }
		    

		    else if (g.equals("5")) {
			System.out.println(a.printProp());
			
			s = "Name and Location: \n" + a.toString(c) + menuBefore();
			System.out.println(s);
			g = read();	
		    }
		    else if (g.equals("6")) {
			System.out.println(c.printBoard());
			s = "Name and Location: \n" + a.toString(c) + menuBefore();
			System.out.println(s);
			g = read();
		    }
		    else if (g.equals("13")) {
			System.out.println("enter location: ");
			g = read();
			a.location = Integer.parseInt(g);
			a.move(0, i, players);
		    }
		    else if (g.equals("7")) {
			System.out.println("Trade Successful");	
			s = "Name and Location: \n" + a.toString(c) + menuBefore();
			System.out.println(s);
			g = read();
		    }
		    else {
			System.out.println("Error, invalid input.");
			s = "Name and Location: \n" + a.toString(c) + menuBefore();
			System.out.println(s);
			g = read();
		    }			
		}
		if (g.equals("1")) 
		    play2(i, c);
	    } 
	}
    }
		    
		    
		    
	    
       public static void main(String[] args) {

	MonopolyAP m = new MonopolyAP();
	players.add(new Player("Player 1", 0));
	players.add(new Player("Player 2", -1));

	Board z = new Board();
       	Intro i = new Intro();
	
       	m.clearScreen();

       	i.play(100);
       	i.pause(4000);

	m.clearScreen();

	m.newGame(z);
	/*	
	System.out.println(a.toString(z));
	a.location = 1;
	a.buy((Place)z.places.get(a.location));
	System.out.println(a.toString(z));



	
	a.buyHouse((Property)z.places.get(3));
	System.out.println(a.toString(z));
	
	a.sellHouse((Property)z.places.get(3));
	System.out.println(a.toString(z));
	*/
	
    }









}