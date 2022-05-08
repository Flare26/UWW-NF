// MY FIRST MINECRAFT MOD
//Nathan Frazier
package com.nf.testplugin;
import com.nf.testplugin.Main2;
import java.io.IOException;

// Bukkit & Spigot imports for Minecraft API
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

// Firmata4j for Arduino API
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.I2CDevice;

public final class Main2 extends JavaPlugin implements Listener {

	// This is a function inside of the plugin code but it's put into its own file for testing
	
	static IODevice uno;
	public static void main(String [] args)
	{
		// onEnable try and begin communications wth the Arduino
		String portName = "COM1";
		System.out.println("----- Nate's first Minecraft mod has booted! :D -----");
		System.out.println("N: Attempting to establish COM link...");
		try {
			// Try to establish connection to the arduino via COM port
			
			uno = new FirmataDevice(portName);
			uno.start();
			uno.ensureInitializationIsDone();
			System.out.println("N: COM link success!!!!");
			
			Pin pin = uno.getPin(2);
			try {
				pin.setMode(Pin.Mode.OUTPUT);
				pin.setValue(1);
				System.out.println("Check your Arduino ;)");
			} catch (IllegalArgumentException | IOException e) {
				// TODO Auto-generated catch block
				System.out.println("FAILED to setMode or setValue of pin!");
				e.printStackTrace();
			}
			
		} catch (IOException | InterruptedException e)
		{
			e.printStackTrace();
			System.out.println("N: COM link FAILURE!");
		
	}
		
	}
}
