package examples;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@SuppressWarnings("resource")
public class ApachePut {

	private static LocationHandler locationParse = new LocationHandler();
	private static String dispID = null;
	private static Long DID = 3420L; //matt 
	private static String apiToken = "29D3QS0ZKyjLzCef3LB";
   
    static String apiNode = "https://johnsburg12.freshservice.com/api/v2";
    static String apiEndpoint = "/assets/3420.json";
    
    public static int testPost(String apiToken, String apiEndpoint, JSONObject outbound) throws Exception
    {
    	
    	// CREATE PUT REQUEST AS HTTPENTITY
    	HttpEntity stringEntity = new StringEntity(outbound.toString());
    	HttpPut putrequest = new HttpPut(apiNode + apiEndpoint);
    	putrequest.setEntity(stringEntity);
    	putrequest.setHeader("Content-Type", "application/json");
    	
   	 final HttpClientBuilder hcBuilder = HttpClientBuilder.create();
     final RequestBuilder reqBuilder = RequestBuilder.post();
     final RequestConfig.Builder rcBuilder = RequestConfig.custom();
     
     
    	// URL OBJECT FROM API ENDPOINT
    	URL url = new URL(apiNode + apiEndpoint);
    	 final String urlHost = url.getHost();
         final int urlPort = url.getPort();
         final String urlProtocol = url.getProtocol();
         
         reqBuilder.setUri(url.toURI());
		
        // Authentication:
         List<String> authPrefs = new ArrayList<>();
         authPrefs.add(AuthSchemes.BASIC);
         rcBuilder.setTargetPreferredAuthSchemes(authPrefs);
         CredentialsProvider credsProvider = new BasicCredentialsProvider();
         credsProvider.setCredentials(
                 new AuthScope(urlHost, urlPort, AuthScope.ANY_REALM),
                 new UsernamePasswordCredentials(apiToken, "X"));
         hcBuilder.setDefaultCredentialsProvider(credsProvider);
         AuthCache authCache = new BasicAuthCache();
         AuthSchemeBase authScheme = new BasicScheme();
         authCache.put(new HttpHost(urlHost, urlPort, urlProtocol), authScheme);
         HttpClientContext hccContext = HttpClientContext.create();
         hccContext.setAuthCache(authCache);
         
      // Body:
         final String jsonBody = outbound.toString();
         HttpEntity entity = new StringEntity(jsonBody,
                 ContentType.APPLICATION_JSON.withCharset(Charset.forName("utf-8")));
         reqBuilder.setEntity(entity);
         
      // Execute:
         RequestConfig rc = rcBuilder.build();
         reqBuilder.setConfig(rc);
         System.out.println(reqBuilder.getConfig());
         
         HttpClient hc = hcBuilder.build();
         HttpUriRequest req = reqBuilder.build();
         HttpUriRequest testput = putrequest;
         
         org.apache.http.HttpResponse response = hc.execute(testput, hccContext);
        
      // Print out:
         HttpEntity body = response.getEntity();
         InputStream is = body.getContent();
         BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
         String line;
         StringBuilder sb = new StringBuilder();
         while((line=br.readLine())!=null) {
             sb.append(line);
         }
         System.out.println("Body:\n");
         System.out.println(sb.toString());
         
         return response.getStatusLine().getStatusCode();
    }
    
    //MAIN FOR TESTING
	public static void main(String[] args) {
		try {
			Auth auth = new Auth();
			System.out.println("Extracting details...");
			JSONObject serverobj = auth.getCustomObject("assets/"+DID);
			JSONObject details = new JSONObject(serverobj.getJSONObject("asset"));
			System.out.println("PARENT LOCATION = " + locationParse.getAssignedSchool((Long)DID));
			System.out.println("ENTER desc ---> ");
			String desc = new Scanner(System.in).nextLine();
			
			JSONObject outgoing = serverobj.getJSONObject("asset");
			outgoing.put("description", desc);
			outgoing.remove("id");
			outgoing.remove("display_id");
			outgoing.remove("created_at");
			outgoing.remove("author_type");
			outgoing.remove("updated_at");
			
			System.out.println("Outgoing desc now reads : " + outgoing.get("description"));
			System.out.println("Repacking...");
			System.out.println(outgoing.toString());
			
			
			serverobj.put("asset", outgoing);
			
			
			//SEND OUTGOING BACK TO SERVER
			
			testPost(apiToken, apiEndpoint, serverobj);
	         
	} catch (Exception e) {
		e.printStackTrace();
	}

}
}
