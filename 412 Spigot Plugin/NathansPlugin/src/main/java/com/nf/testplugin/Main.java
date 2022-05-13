// MY FIRST MINECRAFT MOD
//Nathan Frazier
package com.nf.testplugin;
import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;

import org.bukkit.plugin.PluginManager;
// Bukkit & Spigot imports for Minecraft API
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public final class Main extends JavaPlugin implements Listener {

	SerialPort sp = SerialPort.getCommPort("COM3");
	
	
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
		System.out.println("----- Nate's first Minecraft mod has booted! :D -----");
		
		// register listeners because that's a thing you gotta do, Unity engine has me spoiled.
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		NathansListener listener = new NathansListener(this); // pass in main class so we can still access the stuff from here
		pm.registerEvents(listener, this);
		// OK, now the listener class should be registered and it should fire off correctly.
		
		sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
		sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
	    if (sp.openPort()) {
	        System.out.println("Nathan: COM Established!!!");
	      } else {
	        System.out.println("Nathan: COM link FAILURE");
	        return;
	      }	
	    
	    
	}
	@Override
	public void onDisable()
	{
		System.out.println("Nate's first plugin has stopped!");
	}
}
