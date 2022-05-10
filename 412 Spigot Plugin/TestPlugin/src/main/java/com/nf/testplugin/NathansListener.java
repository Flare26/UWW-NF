package com.nf.testplugin;

import java.io.IOException;
import java.text.DecimalFormat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.fazecast.jSerialComm.SerialPort;

public class NathansListener implements Listener {

	int c = 0; // test counter
	SerialPort sp;
	private static final DecimalFormat hf = new DecimalFormat("00.0");
	public NathansListener(Main pluginMain)
	{
		sp = pluginMain.sp; // pass the serial port into listener so the listener can be the one to send data
							// I am doing it this way because only the methods in this listener class get executed when the event actually triggers.
	}
	
	// Event handler
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent pje)
	{
		pje.getPlayer().sendMessage("Nathan: Greetings from NathansListener.java!");
	}
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event)
	{
		double dmg = event.getDamage();
		// check if the entity recieving the damage is a player and not a skeleton, zombie, etc... 
		if(event.getEntity() instanceof Player)
		{
			Player p = (Player) event.getEntity(); // cast to player since an entity could be a chicken or a cow, etc
			if (p.getName().equals("Nateboy57"))
			{ 
				// do a name check otherwise it'll be executed each time any player takes dmg. not just me :D
				// This is where the shock function would be to have me take damage in real life, but for now we're going to just send the HP value
				c++;
				double hp = p.getHealth(); // store current HP before damage. Add +1 because i guess for some reason the health is represented by array idx
				int newHP = (int)( hp - dmg ); // at the time this event fires off, the dmg hasnt been applied to the player yet so we do this calculation to see what hp they will have after this event fully resolves.
				//byte [] outbound = hf.format(hp).getBytes();
				//String test = "17.7";
				String f = hf.format(newHP);
				try {
					//sp.getOutputStream().write(hp);
					sp.getOutputStream().write(newHP);
					sp.getOutputStream().flush(); // flush so each consec write operation does not concatenate 
					p.sendMessage("Nathan: Your remaining HP is " + f +  ".\n Since the server has been alive you've taken dmg " + c + " times." );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					p.sendMessage("Nathan: You've taken some damage, but I couldn't tell the arduino! :'(");
					e.printStackTrace();
				}
				
			}
		}
	}
	
	@EventHandler
	public void onEntityRegainHealthEvent(EntityRegainHealthEvent event)
	{
		// check if the entity recieving the healing is a player and not a skeleton, zombie, etc... 
		if (event.getEntity() instanceof Player)
		{
			Player p = (Player) event.getEntity(); // cast to player since an entity could be a chicken or a cow, etc
			if (p.getName().equals("Nateboy57"))
			{ 
				double hp = p.getHealth(); // store current HP before Healing.
				double healnum = event.getAmount();
				int newHP = (int)( hp + healnum ); // at the time this event fires off, the dmg hasnt been applied to the player yet so we do this calculation to see what hp they will have after this event fully resolves.
				String f = hf.format(healnum);
				try {
					sp.getOutputStream().write(newHP);
					sp.getOutputStream().flush(); // flush so each consec write operation does not concatenate 
					p.sendMessage("Nathan: You healed " + f + " points." );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					p.sendMessage("Nathan: You've healed! I couldn't tell the arduino! :'(");
					e.printStackTrace();
				}
		}
	}
}
}
