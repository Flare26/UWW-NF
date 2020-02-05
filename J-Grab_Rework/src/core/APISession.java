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
	
	public static String DOMAIN_PATH; //johnsburg12.freshservice.com/api/v2/
	
    public HttpClientBuilder hcBuilder = HttpClientBuilder.create();
    protected Account currentAccount ; // use this obj to pull username, password, and domain
	protected byte [] authToken;
	protected CredentialsProvider credsProvider = new BasicCredentialsProvider();
	
	String un = null;
	String pw = null;
	
	public APISession() {
		DOMAIN_PATH = "MYFRESHDOMAIN";
		un = "MYUSERNAME";
		pw = "MYPASSWORD";
		System.out.println("Blank APISession constructed.");
	}
	
	public APISession(Account acc) {
		currentAccount = acc; // This will only execute if the Account log-in credentials are valild. Otherwise it'll keep looping Account's code.
		DOMAIN_PATH = String.format("https://%s.freshservice.com/api/v2/" , currentAccount.getDomain());
	}
	
	// if you wish to configure a manual link
	public void configManualLink (String username, String password, String freshDomain) {
		System.out.println("Constructing APISession...>" + freshDomain);
		String creds = username + ":" + password;
		authToken = creds.getBytes();
		DOMAIN_PATH = String.format("https://%s.freshservice.com/api/v2/", freshDomain);
		try {
		currentAccount = new Account(username, password, freshDomain);
		currentAccount = new Account(username, password, freshDomain);
		} catch (Exception e){
			System.out.println("Error setting up APISession.");
			e.printStackTrace();
		}
	}

	
	/*
	public APISession () {
		//DEV CONSTRUCTOR DELETE THIS EVENTUALLY
		System.out.println("Construction DEV APISession...");
		DOMAIN_PATH = "https://johnsburg12.freshservice.com/api/v2/";
		username = "29D3QS0ZKyjLzCef3LB";
		password = "X";
		}
		*/
	
	public String getAccountUsername() {
		return currentAccount.getUsername();
	}
	
	public String getAccountPassword() {
		return currentAccount.getPassword();
	}
	
	public String getAccountAPIEndpoint() {
		return DOMAIN_PATH;
	}
	
	public void putThisAsset(JSONObject subject, int dispid) {
		RequestConfig.Builder rcBuilder = RequestConfig.custom();
		RequestBuilder putBuilder = RequestBuilder.put();
		URL url;
		HttpResponse response;
		HttpClientContext hccContext = HttpClientContext.create();
		String apiPath = "assets/" + dispid;
		System.out.println("Updating " + subject.getString("name") + " @ >:" + DOMAIN_PATH + apiPath);
		System.out.println("\nConstructing putRequest...>" + DOMAIN_PATH + apiPath);
		
		String response_str = "";
		
		try {
			url = new URL(DOMAIN_PATH + apiPath);
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
		                new UsernamePasswordCredentials(currentAccount.getUsername(), currentAccount.getPassword()));
		        hcBuilder.setDefaultCredentialsProvider(credsProvider);
		        AuthCache authCache = new BasicAuthCache();
		        AuthSchemeBase authScheme = new BasicScheme();
		        //Cache the auth config we just built
		        authCache.put(new HttpHost(urlHost, urlPort, urlProtocol), authScheme);
		        
		        // now use child class StringEntity to contain the jsonBody string
		        
		        final String jsonBody = subject.toString();
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
		String path = DOMAIN_PATH + query;
		String response_str = "";
		
		try {
			URIBuilder builder = new URIBuilder(DOMAIN_PATH + type);
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
			                new UsernamePasswordCredentials(currentAccount.getUsername(), currentAccount.getPassword()));
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
		
		String path = DOMAIN_PATH+apiNode;
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
		                new UsernamePasswordCredentials(currentAccount.getUsername(), currentAccount.getPassword()));
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
	
	public JSONObject searchRequestedAsset(String asset_tag) {
		HttpResponse response;
		JSONArray r = new JSONArray();
		RequestBuilder getBuilder = RequestBuilder.get(); // create a new get RequestBuilder
		RequestConfig.Builder rcBuilder = RequestConfig.custom(); // rcBuilder creates request configs
		HttpClientContext hccContext = HttpClientContext.create();
		RequestConfig rc = rcBuilder.build(); // build a request config obj
		
		System.out.println("Attempting to query for ...>" + asset_tag);
		String path = DOMAIN_PATH + "assets";
		System.out.printf("DOMAIN_PATH = %s\n" , DOMAIN_PATH);
		String response_str = "";
		try {
			HttpClientBuilder hcBuilder = HttpClientBuilder.create();
			URIBuilder builder = new URIBuilder(path);
			builder.setParameter("query", "\"asset_tag:"+asset_tag+"\"");
			getBuilder.setUri(builder.build()); // sets the get RequestBuilder to first page
												// /locations?page=1
			System.out.println("Constructing /locations?page=X query...>" + getBuilder.getUri().toString());
			
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
			                new UsernamePasswordCredentials(currentAccount.getUsername(), currentAccount.getPassword()));
			        hcBuilder.setDefaultCredentialsProvider(credsProvider);
			        AuthCache authCache = new BasicAuthCache();
			        AuthSchemeBase authScheme = new BasicScheme();
			        //Cache the auth config we just built
			        authCache.put(new HttpHost(urlHost, urlPort, urlProtocol), authScheme);
			        
			        hccContext.setAuthCache(authCache);
			        
		        System.out.println("Executing getRequest...");
		        // Execute:
		        rc = rcBuilder.build();
		        getBuilder.setConfig(rc); // sets the getBuilder with proper config
		        
		        HttpClient hc = hcBuilder.build();
		        HttpUriRequest req = getBuilder.build();
		         response =hc.execute(req, hccContext);
		        
		        // Print out:
		        HttpEntity body = ((HttpResponse) response).getEntity();
		        InputStream is = body.getContent();
		        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("utf-8")));
		        String line;
		        StringBuilder sb = new StringBuilder();
		        while((line=br.readLine())!=null) {
		            sb.append(line);
		        }
		        int response_status = ((HttpResponse) response).getStatusLine().getStatusCode();
		        response_str = sb.toString();
		        System.out.println("--------" + response_status + "--------");
		        System.out.println(response_str);
		        
		        r = new JSONObject(response_str).getJSONArray("assets");
		        
		        System.out.printf("%d result(s) found.\n" , r.length());
		        System.out.println("-------------------");
		     
		} catch (MalformedURLException e) {
			System.err.println("getRequestedAsset() URL ERROR");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			System.err.println("getRequestedAsset() EXECUTE BLOCK ERROR");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("getRequestedAsset() EXECUTE BLOCK ERROR");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			//ArrayList<JSONObject> results = new ArrayList<JSONObject> ();
		
			//for ( int i = 0 ; i < r.length(); i ++) {
			// make this section ask what to do in the event of multiple return
			
			
		//}
		
		
		return r.getJSONObject(0); // by default for now, just return the first object
	}

	public int verifyCredentials() {
		int response_status = -1;
		String path = DOMAIN_PATH+"assets/"; 
		RequestBuilder getBuilder = RequestBuilder.get();
		RequestConfig.Builder rcBuilder = RequestConfig.custom();
		HttpClientContext hccContext = HttpClientContext.create();
		try {
		
		System.out.println("Sending verifyCredentials() request to>" + path);
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
		                new UsernamePasswordCredentials(currentAccount.getUsername(), currentAccount.getPassword()));
		        hcBuilder.setDefaultCredentialsProvider(credsProvider);
		        AuthCache authCache = new BasicAuthCache();
		        AuthSchemeBase authScheme = new BasicScheme();
		        //Cache the auth config we just built
		        authCache.put(new HttpHost(urlHost, urlPort, urlProtocol), authScheme);
		        
		        hccContext.setAuthCache(authCache);
		        System.out.println("Executing request...");
		        // Execute:
		        RequestConfig rc = rcBuilder.build();
		        getBuilder.setConfig(rc); // sets the getBuilder with proper config
		        
		        HttpClient hc = hcBuilder.build();
		        HttpUriRequest req = getBuilder.build();
		        response = hc.execute(req, hccContext);
		        
		        response_status = response.getStatusLine().getStatusCode();
		        System.out.println(response_status);
		        
		} catch (MalformedURLException e) {
			System.err.println("verifyCredentials() URL ERROR");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			System.err.println("verifyCredentials() EXECUTE BLOCK ERROR");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("verifyCredentials() EXECUTE BLOCK ERROR");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return response_status;
	} // END verify credentials
	
	@Override
	public String toString() {
		String str = "-APISession information-"
				+ "\nDomain = " + currentAccount.getDomain()
				+"\nDomain Path = " + DOMAIN_PATH
				+ "\nUsername = " + currentAccount.getUsername();
				
		
		return str;
	}
}
