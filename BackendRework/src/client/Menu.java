package client;

import java.util.ArrayList;

import core.APIObject;

// Nathan Frazier
// Menu built with intent to call from static void main. DO NOT CREATE INSTANCE. Instead, just call these statics to get stuff from the user.
// Order of appearance labeled
public class Menu {
	
	public static client.InputHandler IH = new client.InputHandler(); 
	
	
	// 1
	// Design dialogue asking to select search method
	public static void printSearchOptions() {
		System.out.println("Select a search method");
		System.out.printf("[ 1 ] Asset Tag\n[ 2 ] Student ID\n");		
		
	}
	
	
	
	public static void selectFromSearchResults(char selection_num ) {
		
	}
	
	private static ArrayList<APIObject> searchByAssetTag() {
		ArrayList<APIObject> results = new ArrayList<APIObject>();
		
		
		
		
		return results;
	}
	
	
}
