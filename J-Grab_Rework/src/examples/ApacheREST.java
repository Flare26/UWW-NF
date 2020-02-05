package examples;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

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
//Nathan Frazier 
//Prototype for a login system built using an obsolete language (java)
//CaN I get An ExTrA LUuUuURggE piZza - Michael Cooper 8/8/2019
//Grab fields for assets and store the edited object. Executing put method will post the object
public class ApacheREST {

	//one field and asset per instance
	//INITIALIZE VARS
	
	private JSONObject OUTBOUND = null; // outbound object
	private String displayID = null;
	private String apiToken = null;
	private String apiEndpoint = null;
	private String apiNode = "https://johnsburg12.freshservice.com/api/v2/";
	private HttpPut putrequest = null;
	private URL url;
	public HttpClientContext CLIENT_CONTEXT; //Should be set upon constructing
	
	//APACHE reqBuilder
	 final HttpClientBuilder hcBuilder = HttpClientBuilder.create();
     final RequestBuilder reqBuilder = RequestBuilder.post();
     final RequestConfig.Builder rcBuilder = RequestConfig.custom();
	
	//CONSTRUCTOR 
	public ApacheREST ( JSONObject outbound, String apiend, String username, String pass ) throws UnsupportedEncodingException { 
		//Store username:password = apiToken
		System.out.println("**constructor()");
		apiEndpoint = apiend;
		apiToken = username + ":" + pass;
		
		// CREATE PUT REQUEST AS HTTPENTITY
		
		//Server will send back 400 error upon put request if these fields are not removed : 

		//Send object with trimmed fields
		OUTBOUND = (JSONObject) outbound;

    	HttpEntity stringEntity = new StringEntity(OUTBOUND.toString());
    	//set url for request & headers
    	putrequest = new HttpPut(apiNode + apiEndpoint);
    	putrequest.setEntity(stringEntity);
    	putrequest.setHeader("Content-Type", "application/json");
    	System.out.println("INITIALIZING..." + apiend);
    	try
    	{
		initialize();
    	} catch (Exception e) {
    		System.err.println("ERROR IN METHOD 'INITIALIZE' !!!");
    		e.printStackTrace();
    	}
	}
	//1
	public void initialize() throws MalformedURLException, URISyntaxException, Exception {
		System.out.println("**initialize()");
		// URL OBJECT FROM API ENDPOINT
    	 url = new URL(apiNode + apiEndpoint);
    	 final String urlHost = url.getHost();
         final int urlPort = url.getPort();
         final String urlProtocol = url.getProtocol();
         
         reqBuilder.setUri(url.toURI());
         //Store HttpClientContext object in class var
         CLIENT_CONTEXT =  authentication(urlHost, urlPort, urlProtocol);
	}
	//2 authentication() used recursively in above method ^
	public HttpClientContext authentication(String urlHost , int urlPort , String urlProtocol) throws Exception
	{
		System.out.println("**authentication()");
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
        return hccContext;

	}
	
	//3 sendPUT() is executed independently, requires instance.CLIENT_CONTEXT as param
	public int sendPUT(HttpClientContext hccContext) throws IOException {
	     // Body:
		System.out.println("Executing sendPUT()...");
		
        final String jsonBody = OUTBOUND.toString();
        HttpEntity outboundEntity = new StringEntity(jsonBody,
                ContentType.APPLICATION_JSON.withCharset(Charset.forName("utf-8")));
        reqBuilder.setEntity(outboundEntity);
        
     // Execute:
        RequestConfig rc = rcBuilder.build();
        reqBuilder.setConfig(rc);
        System.out.println(reqBuilder.getConfig());
        
        HttpClient hc = hcBuilder.build();
        HttpUriRequest req = reqBuilder.build();
        HttpUriRequest testput = putrequest;
        
        System.out.println("EXECUTE PUT REQUEST : " + testput.toString());
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
}
