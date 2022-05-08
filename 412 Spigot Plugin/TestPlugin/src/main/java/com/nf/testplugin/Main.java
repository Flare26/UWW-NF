// MY FIRST MINECRAFT MOD
//Nathan Frazier
package com.nf.testplugin;
import com.nf.testplugin.Main;
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

public final class Main extends JavaPlugin implements Listener {

	private IODevice uno;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args)
	{
		if (cmd.getName().equalsIgnoreCase("hello"))
		{
			
			if(!(sender instanceof Player))
			{
				sender.sendMessage("You're not a player, so you can't use this command!");
				return true;
			}
		}
		
		// At this point if the sender is not a player, the code will not make it here.
		Player p = (Player) sender;
		if (p.hasPermission("hello.use"))
		{
			p.sendMessage("Hello! This is Nathan from the past!");
			p.sendMessage("I will now attempt to send power to an arduino pin....");
			
			Pin pin = uno.getPin(2);
			try {
				pin.setMode(Pin.Mode.OUTPUT);
				pin.setValue(1);
				p.sendMessage("Check your Arduino ;)");
			} catch (IllegalArgumentException | IOException e) {
				// TODO Auto-generated catch block
				p.sendMessage("FAILED to setMode or setValue of pin!");
				e.printStackTrace();
			}
			return true;
		} else
		{
			//player does not have permission
			p.sendMessage("You dont have permission silly! Sorry :(");
		}
			
		return false;
	}
	
	@Override
	public void onEnable()
	{
		// onEnable try and begin communications wth the Arduino
		String portName = "COM3";
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
	@Override
	public void onDisable()
	{
		System.out.println("Nate's first plugin has stopped!");
	}
}
