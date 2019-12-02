package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;

import org.json.JSONArray;
import org.json.JSONObject;
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
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

// This class holds all relevent auth and http path information for the session
// Once user log in details are found, store them in this object
// I suppose this should also handle get / put methods.
// PUT method should take in an APIobj and extract the url pointer /api/v2/asset/xxxxx

// apiEndpoint == johnsburg12.freshservice.com/api/v2 & apiNode == /asset/xxxx
public class APISession {
	
	//create spaces in memory for put / get
	
	public static String apiEndpoint; //johnsburg12.freshservice.com/api/v2/
	
    public HttpClientBuilder hcBuilder = HttpClientBuilder.create();
    
	protected byte [] authToken;
	protected CredentialsProvider credsProvider = new BasicCredentialsProvider();
	
	protected String username;
	protected String password;
	
	
	public APISession (String username, String password, String freshDomain) {
		System.out.println("Constructing APISession...>" + freshDomain);
		String creds = username + ":" + password;
		authToken = creds.getBytes();
		apiEndpoint = freshDomain;
		// buildLocationCache();
	}
	
	public APISession () {
		//DEV CONSTRUCTOR DELETE THIS EVENTUALLY
		System.out.println("Construction DEV APISession...");
		apiEndpoint = "https://johnsburg12.freshservice.com/api/v2/";
		username = "29D3QS0ZKyjLzCef3LB";
		password = "X";
		}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAPIEndpoint() {
		return apiEndpoint;
	}
	
	public void putRequest(APIObj subject) {
		RequestConfig.Builder rcBuilder = RequestConfig.custom();
		RequestBuilder putBuilder = RequestBuilder.put();
		URL url;
		HttpResponse response;
		HttpClientContext hccContext = HttpClientContext.create();
		String apiPath = subject.getAPIPath();
		System.out.println("\nConstructing putRequest...>" + apiEndpoint + apiPath);
		System.out.println("subject is objectType.[" + subject.getObjectType() + "]");
		
		String response_str = "";
		
		try {
			url = new URL(apiEndpoint + apiPath);
			 final String urlHost = url.getHost();
		     final int urlPort = url.getPort();
		     final String urlProtocol = url.getProtocol();
		     
		     putBuilder.setUri(url.toURI());
		     
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
		        
		        // now use child class StringEntity to contain the jsonBody string
		        
		        final String jsonBody = subject.getJSON_PUT();
		        HttpEntity entity = new StringEntity(jsonBody, 
		        		ContentType.APPLICATION_JSON.withCharset(Charset.forName("utf-8")));
		        putBuilder.setEntity(entity);
		        hccContext.setAuthCache(authCache);
		        System.out.println("Executing putRequest...\nPAYLOAD = " + jsonBody);
		        // Execute:
		        RequestConfig rc = rcBuilder.build();
		        putBuilder.setConfig(rc); // sets the getBuilder with proper config
		        
		        HttpClient hc = hcBuilder.build();
		        HttpUriRequest req = putBuilder.build();
		        
		        System.out.println("Listening for response...");
		        response = hc.execute(req, hccContext);
		        System.out.println("Interpreting...");
		        // Print out:
		        HttpEntity body = response.getEntity();
		        InputStream is = body.getContent();
		        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
		        String line;
		        StringBuilder sb = new StringBuilder();
		        while((line=br.readLine())!=null) {
		            sb.append(line);
		        }
		        int response_status = response.getStatusLine().getStatusCode();
		        response_str = sb.toString();
		        System.out.println("!! RESPONSE CODE : " + response_status);
		        System.out.println(response_str);
		     
		} catch (MalformedURLException e) {
			System.err.println("putRequest() URL ERROR");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			System.err.println("putRequest() URI SYNTAX ERROR");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			System.err.println("putRequest() EXECUTE BLOCK ERROR");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("putRequest() EXECUTE BLOCK ERROR");
			e.printStackTrace();
		}
		
	}
	
	// Method returns a json array with query results
	public List<JSONObject> apiQuery(String type, String query) {
		HttpResponse response;
		JSONArray jarray = new JSONArray();
		RequestBuilder getBuilder = RequestBuilder.get();
		RequestConfig.Builder rcBuilder = RequestConfig.custom();
		HttpClientContext hccContext = HttpClientContext.create();
		String path = apiEndpoint + query;
		String response_str = "";
		
		try {
			URIBuilder builder = new URIBuilder(apiEndpoint + type);
			builder.setParameter("query", query );
			getBuilder.setUri(builder.build());
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
			        System.out.println("Executing apiQuery...");
			        // Execute:
			        RequestConfig rc = rcBuilder.build();
			        getBuilder.setConfig(rc); // sets the getBuilder with proper config
			        
			        HttpClient hc = hcBuilder.build();
			        HttpUriRequest req = getBuilder.build();
			        response = hc.execute(req, hccContext);
			        
			        // Print out:
			        HttpEntity body = response.getEntity();
			        InputStream is = body.getContent();
			        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
			        String line;
			        StringBuilder sb = new StringBuilder();
			        while((line=br.readLine())!=null) {
			            sb.append(line);
			        }
			        
			        int response_status = response.getStatusLine().getStatusCode();
			        response_str = sb.toString();
			        System.out.println(response_status);
			        System.out.println(response_str);
			     
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
		
		JSONObject nest = new JSONObject(response_str);
		JSONArray result_arr = nest.getJSONArray(type);
		List<JSONObject> results = new ArrayList<JSONObject> ();
		int count = 0;
		for (Object o : result_arr) {
			results.add((JSONObject) o);
			count++;
		}
		System.out.printf("Query found %d result(s)...\n" , count);
		return results;
	} // END QUERY FUNCTION
	
	// This method is for direct access to objects assets/xxxx locations/xxxx using the ID
	public String getRequest(String apiNode) {
		
		String path = apiEndpoint+apiNode;
		String response_str = "";
		RequestBuilder getBuilder = RequestBuilder.get();
		RequestConfig.Builder rcBuilder = RequestConfig.custom();
		HttpClientContext hccContext = HttpClientContext.create();
		try {
		
		System.out.println("Constructing getRequest...>" + path);
		URIBuilder builder = new URIBuilder(path);
		getBuilder.setUri(builder.build());
	 
		HttpResponse response;
		URL url = new URL(path);
		final String urlHost = url.getHost();
	    final int urlPort = url.getPort();
	    final String urlProtocol = url.getProtocol();
	 
	    System.out.println(url.toString());
		    
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
		        System.out.println("Executing getRequest...");
		        // Execute:
		        RequestConfig rc = rcBuilder.build();
		        getBuilder.setConfig(rc); // sets the getBuilder with proper config
		        
		        HttpClient hc = hcBuilder.build();
		        HttpUriRequest req = getBuilder.build();
		        response = hc.execute(req, hccContext);
		        
		        // Print out:
		        HttpEntity body = response.getEntity();
		        InputStream is = body.getContent();
		        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
		        String line;
		        StringBuilder sb = new StringBuilder();
		        while((line=br.readLine())!=null) {
		            sb.append(line);
		        }
		        int response_status = response.getStatusLine().getStatusCode();
		        response_str = sb.toString();
		        System.out.println(response_status);
		        System.out.println(response_str);
		     
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
		
		return response_str;
		
	}

}
