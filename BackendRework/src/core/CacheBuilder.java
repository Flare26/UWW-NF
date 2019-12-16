package core;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class CacheBuilder {
	// 1 - Build a system that returns all pages of a given field
	// 2 - Parse each location and store it < String location_name , Short location_id > in hash map
	// -- in a hash map, duplicates will not be allocated to unique memory or something like that
	// 3 - Save map object to a serialized locations.tmp 
	
	
	private Map<String , Long> map = new HashMap<String , Long>();
	
	public CacheBuilder() {
		
	}
	
	public void buildLocationMap(APISession clientsession) {
		// use MapBuilder and read in all location pages, store in hash map
		MapBuilder bob = new MapBuilder(clientsession); // pass api session from client
		System.out.println("Building location map...");
		bob.buildLocationMap(map);
		
		cacheMap(map); // cache the location Name , ID map to a tmp file
		
		System.out.printf("»» API Locations map built & cached");
	}
	
	public Map<String, Long> getLocationHashMap(){ 
		return map; // returns the hash map with locations
	}
	
	public void cacheMap(Map<String, Long> hashmap) { 
		//Right now this method only writes to txt, but eventually just write hashmap to serializable
		System.out.println("cacheMap() hashmap.toString()=" + hashmap.toString());
		//System.out.println("Testing JHS LC Chromebook Cart: " + hashmap.get("JHS LC Chromebook Cart"));
		StringBuilder sb = new StringBuilder(); // Builder will keep map data in readable format for txt
		
		
		try {
			FileOutputStream hashlogger = new FileOutputStream("hashmap_log.txt"); 
			BufferedOutputStream hashwriter = new BufferedOutputStream(hashlogger);
			System.out.println("Writing location cache...");
			// Maps are Iterable, they go by Entries NOT indexes. 
			for (Map.Entry<String,Long> entry : hashmap.entrySet())  {
	            sb.append("Key = " + entry.getKey() + 
	                             ", Value = " + entry.getValue()+"\n----------\n"); 
	    		} 
			hashwriter.write(sb.toString().getBytes("UTF-8")); // getBytes gets byte [] for BOS
			
		} catch (IOException e) {
			System.err.println("ERROR WRITING MAP<STR, LNG> TO 'hashmap_log.txt'");
			e.printStackTrace();
		}
		
	}
	
	// MapBuilder handles API pagination but A LOT of code is re-written from APISession
	// Find a way to use inheritance or interfaces maybe?? abstract MapBuilder extends CacheBuilder?? 
	class MapBuilder {
		boolean endofpages = false;
		String log_str = "";
		final APISession apisession;
		private String username;
		private String password;
		final String path;
	
		public MapBuilder (APISession clientsession) {
			apisession = clientsession;
			username = clientsession.getUsername();
			password = clientsession.getPassword();
			path  = apisession.getAPIEndpoint() + "locations"; // johnsburg12.freshservice.com/api/v2/locations
		}
		
		
		public Map<String, Long> buildLocationMap(Map<String, Long> hash_map) {
			List<JSONObject> results = new ArrayList<JSONObject> ();
			HttpResponse response;
			
			RequestBuilder getBuilder = RequestBuilder.get(); // create a new get RequestBuilder
			RequestConfig.Builder rcBuilder = RequestConfig.custom(); // rcBuilder creates request configs
			HttpClientContext hccContext = HttpClientContext.create();
			RequestConfig rc = rcBuilder.build(); // build a request config obj
			JSONArray location_objects;
				
				try {
					HttpClientBuilder hcBuilder = HttpClientBuilder.create();
					URIBuilder builder = new URIBuilder(path);
					
					getBuilder.setUri(builder.build()); // sets the get RequestBuilder to first page
														// /locations?page=1
					System.out.println("Constructing apiQuery...>" + getBuilder.getUri().toString());
					
					URL url = new URL(path);
					final String urlHost = url.getHost();
				    final int urlPort = url.getPort();
				    final String urlProtocol = url.getProtocol();
					  // Build authentication cache:
					        List<String> authPrefs = new ArrayList<>();
					        authPrefs.add(AuthSchemes.BASIC);
					        rcBuilder.setTargetPreferredAuthSchemes(authPrefs);
					        CredentialsProvider credsProvider = new BasicCredentialsProvider();
					        credsProvider.setCredentials(
					                new AuthScope(urlHost, urlPort, AuthScope.ANY_REALM),
					                new UsernamePasswordCredentials(username, password));
					        hcBuilder.setDefaultCredentialsProvider(credsProvider);
					        AuthCache authCache = new BasicAuthCache();
					        AuthSchemeBase authScheme = new BasicScheme();
					        //Cache the auth config we just built
					        authCache.put(new HttpHost(urlHost, urlPort, urlProtocol), authScheme);
					        
					        hccContext.setAuthCache(authCache);
					        
					        
					        System.out.println("Executing /location...");
					        // Execute:
					        // WHILE LOOP WILL GRAB EACH PAGE
					        int pgcount = 1;
					        String pg = "1";
					        while (endofpages == false) {
					        builder.setParameter("page", pg );
							getBuilder.setUri(builder.build());
					        getBuilder.setConfig(rc); // sets the getBuilder with proper config
					        
					        HttpClient hc = hcBuilder.build();
					        HttpUriRequest req = getBuilder.build();
					        response = hc.execute(req, hccContext); // THIS IS THE REGION WHERE I NEED TO LOOOOP
					        System.out.println("Awaiting response..." + " ?page=" + pg);
					        // Print out:
					        int response_status = response.getStatusLine().getStatusCode();
					        System.out.println("CODE="+response_status);
					        HttpEntity body = response.getEntity();


					        InputStream is = body.getContent();
					        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
					        String line = br.readLine();
					     
					        JSONObject nest = new JSONObject(line); // parses the current nest
					        
					        if (  nest.getJSONArray("locations").isEmpty()  ) {
					        	System.out.println("!! PAGE EMPTY ; NO MORE PAGES - " + (pgcount-1));
				        		endofpages = true; // if API is resturning empty, its the end!
					        }
					        
					        // If API is still returning arrays with content in them...
					        if (endofpages == false) {
					        	
						        //System.out.println("nest_STR=" + nest.toString());
						        int objcount = 0; // count objects in this page's array
						        JSONArray page_array = nest.getJSONArray("locations"); // parse the location array
						        for (int i = 0 ; i < page_array.length() ; i++) {
						        	JSONObject current = page_array.getJSONObject(i);
						        	 results.add(current); //add each object in array to ArrayList
						        	 map.put(current.getString("name"), current.getLong("id"));
						        	 
						        	 objcount ++;
						        }
						       System.out.printf("%d objects sucesfully added to result list\t" , objcount);
						       pgcount ++;
					       		} // END FOR OBJ X : ARR
					        
					        pg = Integer.toString(pgcount); // increment numeric but store as string
				        	
					        } // END endofpages LOOP
					        // PAGE STRING BUILT, parse location object
					   // create a text log of all objects pulled from their nests
					        
					        FileOutputStream writer = new FileOutputStream("location_log.txt");
					        
							BufferedOutputStream bw = new BufferedOutputStream(writer);
							
							// This array will b printed to .txt & contains all un-nested locations
							location_objects = new JSONArray(); 
							
							StringBuilder sb = new StringBuilder();
							int index;
							for (index = 0; index < results.size(); index++)
							{
								
								JSONObject location = results.get(index);
								sb.append(location.toString());
								location_objects.put(index , location); // gathers all parsed objects into JSONArray location_objects
							
							}
							System.out.printf("\n# %d objects appended to location_objects\n",index);
							
							bw.write(location_objects.toString().getBytes("UTF-8"));
							
					     
					} catch (MalformedURLException e) {
						System.err.println("getRequest() URL ERROR");
						e.printStackTrace();
					} catch (ClientProtocolException e) {
						System.err.println("getRequest() EXECUTE BLOCK ERROR");
						e.printStackTrace();
					} catch (IOException e) {
						System.err.println("getRequest() EXECUTE BLOCK ERROR");
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				
				System.out.printf("Query found %d result(s)...\n" , results.size());
		
				return map;
			}
		
	}
}
