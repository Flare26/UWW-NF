import java.util.Base64;


//Nathan Frazier
//I want this object to store everything having to do with username / pass , api URLS, ect...
public class APICredentials {

	private static String userCredentials;
	private static String APINODE;
	private static String encodedCredentials;
	public APICredentials(String creds , String apiURL) {
		userCredentials = creds;
		APINODE = apiURL;
		encodedCredentials = new String(Base64.getEncoder().encode(userCredentials.getBytes()));
	}

	public static String getAPINode() {
		return APINODE;
	}
	
	public static String getAuthToken () {
		
		return encodedCredentials;
	}
	
}
