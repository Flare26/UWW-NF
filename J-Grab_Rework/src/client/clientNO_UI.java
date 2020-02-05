package client;

import java.util.Map;
import java.util.Scanner;

import core.APISession;
import core.Account;
import core.CacheBuilder;
import core.Chromebook;
import core.Requester;

// 11 - 29 - 2019
/* APIObj - Pass json string from APISession to APIObj, which parses and stores all relevant info
 * APISession - get requests use direct assets/xxxx while apiQuery takes (String type) and the
 * query parameters enclosed in double quotations apiQuery(String type, String paraminquotes)
 * */
// https://johnsburg12.freshservice.com/api/v2/assets/3420
// TEST ASSET A07439 location_id 	5000343179  display_id 3419

// FOR INPUT ---------------------------------------- > Menu.java contains public static InputHandler IH!! < ---------------------
public class clientNO_UI {
	static Chromebook stuChromebook = null; // Chromebook information stored for ease of access, including source json ( if any come back from query )
	static Requester assetAssignee = null; // Object information for whom the asset is assigned ( if any )
	static APISession RUNTIME_APISESSION;
	static Map<String , Long> map;
	final static CacheBuilder locationCb = new CacheBuilder(); // Will not change, this is a one time use temp file factory
	static boolean quit = false;		
	
	public static void main(String[] args) throws Exception {
		System.out.println("\nBuilding your database connection...");
		RUNTIME_APISESSION = new APISession(new Account()); // firstly, find a valid account object to use
		System.out.println(RUNTIME_APISESSION.toString());
		System.out.println("Downloading location data...");
		locationCb.buildLocationMap(RUNTIME_APISESSION); // Build the cache and save it upon startup
		map = locationCb.getLocationHashMap(); // getter method for the map we built during initialization
		
		/*
		 // build a hash map of updated locations in memory
		HashMap<String, Long> locationmap = (HashMap<String, Long>) locationCb.getLocationHashMap();
		for (Map.Entry<String,Long> entry : locationmap.entrySet() )
		{
			//using entrySet() on a hashmap returns an iterable set
			//System.out.println(entry.getKey()); // key is the STR name
		}
		*/
		
		// END CACHE INITIALIZATION
		System.out.println();
		System.out.println("\n------ INITIALIZED SUCCESFULLY ------");
		System.out.println("Asset Editor v3 alpha - Nathan Frazier\n");
		while (! quit) {
		//String query = buildQuery();
		// create new APIObject and use the APISession object to grab json code from fresh
		//core.APIObj apiobject = new core.APIObj(testses.getRequest("assets/3420"));
		
		
		String asset_tag = "";
		String stu_id = "";
		
		Menu.printSearchOptions();
		char in = Menu.IH.getChoice(2); 
		
		switch (in) {
		case '1' :
			System.out.printf("Please enter an asset identifier that belongs to your domain >: ");
			asset_tag = Menu.IH.stringInput();
			stuChromebook = new Chromebook(RUNTIME_APISESSION.searchRequestedAsset(asset_tag));
			
			break;
		case '2' :
			System.out.printf("Please enter a registerd student ID >: ");
			stu_id = Menu.IH.stringInput();
			break;
			
		}
		
		// We now either have a student ID, or an asset tag.
		
		
		 
		System.out.printf("»» Parsed %s info:\n" , stuChromebook.getObjectType() );
		System.out.println("ToString() = "  + stuChromebook.toString());
		System.out.println("Name: " + stuChromebook.getName());
		System.out.println("Assignee ID: " + stuChromebook.getUserID());
		System.out.println("Location_ID: " + stuChromebook.getLocID());
		System.out.println("Desc: " + stuChromebook.getDesc());
		System.out.println("Location name: " + locationCb.searchForKey(stuChromebook.getLocID()));
		
		System.out.printf("Please enter a new desc for\t%s:\n", stuChromebook.getName());
		Scanner scan = new Scanner(System.in);
		String newtext = scan.nextLine();
		
		stuChromebook.editField("description" , newtext);
		System.out.print("»» Object description field updated\n-->"+stuChromebook.getDesc());
		
		System.out.printf("\nEnter an optional new location for\t%s:\n", stuChromebook.getName());
		// Running out of time, just use an exact key match
		newtext = ""; // clear
		newtext = scan.nextLine();
		// Skip if left blank
		if ( ! newtext.equals("")) {
		
		
		stuChromebook.editField("location_id", map.get(newtext)); // Stored in map as long 
		System.out.printf("»» Location ID updated to %s:\n", stuChromebook.getLocID());
		}
		
		// Confirm the update
		
		System.out.println("CONFIRM UPDATE?");
		System.out.println("ToString() = "  + stuChromebook.toString());
		System.out.println("Name: " + stuChromebook.getName());
		System.out.println("Assignee ID: " + stuChromebook.getUserID());
		System.out.println("Location_ID: "  + stuChromebook.getLocID());
		System.out.println("Desc: " + stuChromebook.getDesc());
		System.out.println("Location name: " + locationCb.searchForKey(stuChromebook.getLocID()));
		
		
		Menu.IH.setYNOnly(true);
		in = Menu.IH.getChoice(0);
		
		if ( in == 'y')
			stuChromebook.putCurrentState(RUNTIME_APISESSION); 
		
		System.out.println("Query again?");
		Menu.IH.setYNOnly(true);
		if (Menu.IH.getChoice(0) == 'n');
			quit = true;
		}
		

	}

	
}
