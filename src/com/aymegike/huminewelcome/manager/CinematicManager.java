package com.aymegike.huminewelcome.manager;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.aymegike.huminewelcome.utils.CinematicState;
import com.aymegike.huminewelcome.utils.cinematic.Cinematic;
import com.aymegike.huminewelcome.utils.cinematic.ExploreCinematic;
import com.aymegike.huminewelcome.utils.cinematic.WakeUpCinematic;

public class CinematicManager {

	private ArrayList<Cinematic> cinematics;
	
	private ArrayList<Cinematic> instances;
	
	public CinematicManager() {
		this.cinematics = new ArrayList<Cinematic>();
		this.instances = new ArrayList<Cinematic>();
		
		instances.add(new WakeUpCinematic(null));
		instances.add(new ExploreCinematic(null));
		
	}
	
	public void addCinematics(Cinematic cinematic) {
		cinematics.add(cinematic);
	}
	
	public void removeCinematics(Cinematic cinematic) {
		cinematics.remove(cinematic);
	}
	
	public ArrayList<Cinematic> getCinematics() {
		return cinematics;
	}
	
	public Cinematic getInstances(Player player, CinematicState cinematicState) {
		
		for (Cinematic cinematic : instances) {
			if (cinematic.getCinematicState() == cinematicState) {
				return cinematic.newInstance(player);
			}
		}
		
		return null;
	}
	
}
