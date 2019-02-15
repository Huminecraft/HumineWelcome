package com.aymegike.huminewelcome.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaySound {
	
	private Player player;
	private String sound;
	
	public PlaySound(Player player, String sound) {
		this.player = player;
		this.sound = sound;
	}
	
	public void play() {
		if (player.isOnline())
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:huminesound."+sound+" ambient "+player.getName()+" "+player.getLocation().getBlockX()+" "+player.getLocation().getBlockY()+" "+player.getLocation().getBlockZ()+" 5 1");
	}
	
	public void play(boolean isMusic, int volume) {
		if (player.isOnline())
			if (isMusic)
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:huminesound."+sound+" music "+player.getName()+" "+player.getLocation().getBlockX()+" "+player.getLocation().getBlockY()+" "+player.getLocation().getBlockZ()+" "+volume+" 1");
			else
				if (player.isOnline())
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound minecraft:huminesound."+sound+" ambient "+player.getName()+" "+player.getLocation().getBlockX()+" "+player.getLocation().getBlockY()+" "+player.getLocation().getBlockZ()+" "+volume+" 1");
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getSound() {
		return sound;
	}

}
