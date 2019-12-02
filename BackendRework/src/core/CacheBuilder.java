package core;

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
import java.util.List;
import java.util.Map;

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
	
	
	private Map<String , Short> map = new HashMap<String , Short>();
	
	public CacheBuilder() {
		
	}
	
	public void buildLocationMap(APISession clientsession) {
		// use MapBuilder and read in all location pages, store in hash map
		MapBuilder bob = new MapBuilder(clientsession); // pass api session from client
		System.out.println("Building location map...");
		bob.buildLocationMap(map);
		System.out.println("Writing location cache...");
		cacheMap(map); // cache the location Name , ID map to a tmp file
	}
	
	public void cacheMap(Map<String, Short> hashmap) {
		
	}
	
	
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
		
		
		public Map<String, Short> buildLocationMap(Map<String, Short> hash_map) {
			List<JSONObject> results = new ArrayList<JSONObject> ();
			HttpResponse response;
			StringBuilder sb = new StringBuilder();
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
					        int count = 1;
					        String pg = "1";
					        while (endofpages == false) {
					        builder.setParameter("page", pg );
							getBuilder.setUri(builder.build());
					        getBuilder.setConfig(rc); // sets the getBuilder with proper config
					        
					        HttpClient hc = hcBuilder.build();
					        HttpUriRequest req = getBuilder.build();
					        response = hc.execute(req, hccContext); // THIS IS THE REGION WHERE I NEED TO LOOOOP
					        System.out.println("Awaiting response...");
					        // Print out:
					        int response_status = response.getStatusLine().getStatusCode();
					        System.out.println("CODE="+response_status);
					        HttpEntity body = response.getEntity();


					        InputStream is = body.getContent();
					        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
					        String line = br.readLine();
					        JSONObject nest = new JSONObject(line); // parses the nest
					        
					        if (  nest.getJSONArray("locations").isEmpty()  ) {
					        	System.out.println("!! NO MORE PAGES - " + count);
				        		endofpages = true; // if API is resturning empty, its the end!
					        }
					        
					        // If API is still returning arrays with content in them...
					        if (endofpages == false) {
						        System.out.println("nest_STR=" + nest.toString());
						        int objcount = 0; // count objects in this page's array
						        JSONArray arr = nest.getJSONArray("locations"); // parse the location array
						        for (Object x : arr) {
						        	 results.add(new JSONObject(x.toString())); //add each object in array to ArrayList
						        	 sb.append(x.toString());
						        	 objcount ++;
						        }
						       System.out.printf("%d objects sucesfully added to result list\t" , objcount);
					
						        	count ++;
						        	System.out.println("!! END PG " + count);
						        	pg = Integer.toString(count); // increment numeric but store as string
						        	System.out.println(sb.toString());
						        	
					       		} // END FOR OBJ X : ARR
					        } // EXIT endofpages LOOP
					        // PAGE STRING BUILT, parse location object
					   // create a text log of all objects pulled from their nests
					        
					        FileWriter writer = new FileWriter("json_locations.txt");
							BufferedWriter bw = new BufferedWriter(writer);
							
							location_objects = new JSONArray();
							int objects_appended=0;
							for (JSONObject location : results)
							{
								location_objects.put(location); // gathers all parsed objects into JSONArray location_objects
								objects_appended++;
							}
							System.out.printf("\n# %d object strings appended to log_str\n",objects_appended);
							
							bw.write(location_objects.toString());
							
					     
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
