package client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import core.CacheBuilder;

// 11 - 29 - 2019
/* APIObj - Pass json string from APISession to APIObj, which parses and stores all relevant info
 * APISession - get requests use direct assets/xxxx while apiQuery takes (String type) and the
 * query parameters enclosed in double quotations apiQuery(String type, String paraminquotes)
 * */
// https://johnsburg12.freshservice.com/api/v2/assets/3420
// TEST ASSET A06859 
public class clientNO_UI {

	public static void main(String[] args) {
		core.APISession apisession = new core.APISession();
		boolean quit = false;
		CacheBuilder locationCb = new CacheBuilder();
		locationCb.buildLocationMap(apisession);
		HashMap<String, Long> locationmap = (HashMap<String, Long>) locationCb.getLocationHashMap();
		for (Map.Entry<String,Long> entry : locationmap.entrySet() )
		{
			//using entrySet() on a hashmap returns an iterable set
			//System.out.println(entry.getKey()); // key is the STR name
		}
		
		
		// END CACHE INITIALIZATION
				System.exit(0); // KEEP EXIT CLAUSE UNTIL DONE TESTING CACHE FUNCTION
		
		String disp_id = "";
		String aTag = "";
		
		
		while (! quit) {
		//String query = buildQuery();
		// create new APIObject and use the APISession object to grab json code from fresh
		//core.APIObj apiobject = new core.APIObj(testses.getRequest("assets/3420"));
		List<JSONObject> results = apisession.apiQuery("assets" , "");
		for (Object e : results) {
			System.out.println(e.toString());
		}
		
		System.out.println("START HARD CODED SECTION ------ ");
		
		core.APIObj apiobject = new core.APIObj(apisession.getRequest("assets/3420"));
		System.out.println("?? Parsed object info:");
		System.out.println("Name: " + apiobject.getName());
		System.out.println("Location_ID: " + apiobject.getLocID());
		System.out.printf("Please enter a new desc for :\t%s\n", apiobject.getName());
		Scanner scan = new Scanner(System.in);
		String newtext = scan.nextLine();
		
		JSONObject edited_obj = apiobject.cloneJOBJ();
		edited_obj.put("description", newtext);
		apiobject.updateOBJ(edited_obj);
		
		System.out.print("!! Object description field updated\n-->"+apiobject.getDesc());
		apisession.putRequest(apiobject); 
		
		System.out.println("Query again? (y/n)");
		if (scan.next().equalsIgnoreCase("n"))
			quit = true;
		}
		

	}

	public static String buildQuery() {
		System.out.println("NO UI DEV VERSION - Nathan Frazier");
		String query = "";
		InputHandler ih = new InputHandler();
		ih.setYNAllowed(false);
		System.out.println("Please select:\n[1] - Query asset tag\n[2] - Query J-ID or organization e-mail:");
		
		char in = ih.getInput(2);
		
		switch (in) {
		case '1' :
			query = "\"asset_tag:"+ promptAssetTag() + "\"";
			break;
			
		case '2' :
			// promptID();
		
		};
		
		return query;
	}
	
	public static String promptAssetTag() {
		System.out.println("Please enter a valid fresh asset tag: ");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		while (input.charAt(0) != 'A' && input.charAt(0) != 'a' ) {
			System.out.println("Asset tags must start with 'a'. Try again...");
			input = scan.nextLine();
		}
		// when input is an int
		input = input.trim();
		System.out.println("Using asset tag ->" + input);
		return input;
	}
	
}
