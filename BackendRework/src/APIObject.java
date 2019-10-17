import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

//Nathan Frazier
// Goal of this class : Be able to create an object that you can invoke an Http GET / POST request on
// Requested object will be persistent within memory
public class APIObject {
	
	private JSONObject returnObject;
	private String apiNode;
	public static String jsonString;
	public static String reqmethod;
	
	
	public APIObject ( String apiNode ) {
		//When Api object is being created, it will take "username:password" credentials and convert it into a byte []
		//byte [] is required for sending an http request
		//Object type will be asset, assets, location, locations, ect... first key from API
		this.apiNode = apiNode;
	}
	
	private String getRemoteJSON ( String urlParameter) throws Exception {
		String output = null;
		String basicAuth = "Basic " + APICredentials.getAuthToken();
		URL url = new URL(apiNode+urlParameter);
		System.out.println(url);
		
		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

		
		con.setRequestMethod(reqmethod);
		con.setRequestProperty ("Authorization", basicAuth);
		con.setRequestProperty("Content-Type", "application/json");
		con.setUseCaches(true);
		con.setDoInput(true);
		con.setDoOutput(true);
		
		//only create input stream reader after credentials are set otherwise 401 response unauthorized

		System.out.println("Response Code : " + con.getResponseMessage() + "\t" + con.getResponseCode() );
		
		if ( con != null ) {
			try {
				
				   System.out.println("**** STRING FROM URL ******");			
				   BufferedReader br = 
					new BufferedReader(
						new InputStreamReader(con.getInputStream()));
							
				   String text;
							
				   while ((text = br.readLine()) != null){
				      System.out.println(text);
				      output += text;
				   }
				   br.close();
							
				} catch (IOException e) {
				   e.printStackTrace();
				}
		} //End of IF statement
		return output;
	}
	
	
	public void getRequest( String urlParameter ) {
		reqmethod = "GET";
		System.out.println("Sending 'GET' request to -->" + (apiNode+urlParameter));
		try {
		String json = getRemoteJSON(urlParameter);
		returnObject = new JSONObject( json );
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			System.out.println("Server Returned : \n" + returnObject.toString());
		
	}
	
	public void postRequest(String urlParameter , String outjson) {
		//Send edited json object back to server and read response code
	}
	
	public String toString() {
		return returnObject.toString();
	}
}
