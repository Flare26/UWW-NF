
public class testClient {

	public static void main(String[] args) {
		//assuming it constructs APICredentials on program start, after showing a login / domain select screen
		new APICredentials("29D3QS0ZKyjLzCef3LB:X" , "https://johnsburg12.freshservice.com/api/v2/");	
		APIObject testObject = new APIObject(APICredentials.getAPINode());
		testObject.getRequest("assets/4398");
		
		System.out.println(testObject.toString());

	}

}
