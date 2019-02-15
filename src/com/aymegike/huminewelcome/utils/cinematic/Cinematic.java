package com.aymegike.huminewelcome.utils.cinematic;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.aymegike.huminewelcome.HumineWelcome;
import com.aymegike.huminewelcome.utils.CinematicState;

public abstract class Cinematic {
	
	protected final Plugin PLUGIN = Bukkit.getPluginManager().getPlugin("HumineWelcome");
	
	private CinematicState cinematicState;
	private Player player; 
	private World world;
	
	public Cinematic(Player player, CinematicState cinematicState) {

		this.cinematicState = cinematicState;
		
		if (player != null) {
			this.player = player;
			this.world = Bukkit.getWorld(player.getName());
			HumineWelcome.getCinematicManager().addCinematics(this);
			HumineWelcome.getNewPlayerManager().update(player, cinematicState);
		}
	}
	
	public abstract void start();
	protected abstract void stop();
	
	public void destroy() {
		stop();
		HumineWelcome.getCinematicManager().removeCinematics(this);
	}
	
	public CinematicState getCinematicState() {
		return cinematicState;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public World getWorld() {
		return world;
	}

	public abstract Cinematic newInstance(Player player);
	
	

}
