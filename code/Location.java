/*

this will be the top most level of locations.  locations will be
extended by Place (those are properties, railroads, and utilities), by
cards (those will be community chest and chance, not sure if they're
different classes or not yet), and by others (Go, jail, tax, free
parking)


 */


import java.io.*;
import java.util.*;

public class Location {

    protected String name;
    protected int rent;

    public boolean isPlace() {
	return false;

    }

    public boolean isProperty() {
	return false;
    }

    public boolean isOther() {
	return true;
    }

    public Player getOwner() {
	Player hi = new Player("", 5);
	return hi;
    }



}