package core;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Scanner;

// ACCES FROM STATIC CLIENT.
public class Account implements Serializable {

	private String USERNAME;
	private String PASSWORD;
	private String DOMAIN;
	File account = new File("lol.lol");
	File secret = new File("secret.uwu");
	public Account(String username, String password, String freshdomain) {
		this.USERNAME = username;
		this.PASSWORD = password;
		DOMAIN = freshdomain;
	}
	
	
	public Account() throws Exception {
		// if cached credentials exist, read them
		if (account.exists() & secret.exists() )
		{
			System.out.printf("\nPrevious log-in detected!\n");	
			FileInputStream fis = new FileInputStream(secret);
			ObjectInputStream is = new ObjectInputStream( new FileInputStream( account ));
			Base64.Decoder decoder = Base64.getDecoder();
			
			Account decoded = (Account) is.readObject(); // manual cast
			DOMAIN = decoded.getDomain();
			USERNAME = decoded.getUsername();
			PASSWORD = new String(decoder.decode(fis.readAllBytes())); // decode the password
			
			// I was setting the password twice here when it read from file....... oops
			
			
			boolean validuser = loginIsValid(USERNAME, PASSWORD, DOMAIN);
			if ( validuser == false ) {
				System.out.println("!! Saved credentials invalid! Please re-launch to log in.");
				account.delete();
				secret.delete();
				System.exit(0);
			}	
			if ( validuser == true )
				System.out.println("Log-in success!"); // now cache the user info
			
		} 
		else { //request a log-in
		
		boolean validuser = false;
		
		while (validuser == false) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your domain : ");
		String domain = input.nextLine().trim();
		
		System.out.printf("Please enter your %s Username : ", domain);
		String username = input.nextLine().trim();
		System.out.println();

		System.out.printf("Please enter your %s Password : ", domain); 
		String pass = new String(input.nextLine());
		
		System.out.println("Loggin in to database....");
		
		validuser = loginIsValid(username, pass, domain);
		if ( validuser == false )
			System.out.println("!! Log-in is invalid. Try again.");
		if ( validuser == true ) {
			System.out.println("Log-in success!"); // now cache the user info
			USERNAME = username;
			PASSWORD = pass;
			DOMAIN = domain;
			
			// remember me block
			System.out.println("Remember/Encrypt account details? (y/n)");
			// handle a y/n question with my midterm class
			client.InputHandler yn = new client.InputHandler();
			yn.setYNOnly(true); // set as a yes or no question
			char x = yn.getChoice(2); 
			// If they answer yes to remember me, serialize the account object
			if ( x == 'y')
				serialize();
		} // END validuser == true
		}
	}
		
	}
	// Account obj can be construced with pre existing credentials or a log - in prompt

	// Mutators
	
	public void setUsername(String u) {
		USERNAME = u;
	}
	
	public void setPassword(String p) {
		PASSWORD = p;
	}
	
	public String getUsername() {
		return USERNAME;
	}
	
	String getPassword() {
		return PASSWORD;
	}
	
	public String getDomain() {
		return DOMAIN;
	}
	
	private boolean loginIsValid(String uname, String pass, String domain) {
		APISession testsess = new APISession();
		testsess.configManualLink(uname, pass, domain);
		int response_code = testsess.verifyCredentials(); // runs a generic query to see if the credentials work
		// if server sends back success code 200...
		if ( response_code == 200 ) {
			return true;
		} else {
			return false;
		}
		
	}

	void serialize() {
		// write the password to a seperate file because it stores in plaintext with serialization
		try {
			Base64.Encoder encoder = Base64.getEncoder();
			File secret = new File("secret.uwu");
			FileOutputStream fos = new FileOutputStream ( secret );
			fos.write(encoder.encode(PASSWORD.getBytes())); // use getbytes[]
			
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(account));
			String temp = new String(this.PASSWORD);
			PASSWORD = null;
			os.writeObject(this); 
			System.out.println("Account cache succesful!");
			os.close();
			PASSWORD = temp; // reset password so it doesn't get written to the obj
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
