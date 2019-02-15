package com.aymegike.huminewelcome.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Door {
	
	private Location location;
	
	private int size_x;
	private int size_y;
	private int size_z;
	
	private int h;
	
	private boolean open;
	
	private int task;
	
	public Door(Location loc, int size_x, int size_y, int size_z, int h) {
		this.location = loc;
		
		this.size_x = size_x;
		this.size_y = size_y;
		this.size_z = size_z;
		
		this.h = h;
		
		open = false;
		
	}
	
	
	
	public boolean isOpen() {
		return open;
	}
	
	public void changeSate() {
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("HumineWelcome"), new Runnable() {
			
			int incress = open ? -1 : 1;
			int i = 0;
			@Override
			public void run() {
				
				if (!(i < h)) {
					Bukkit.getScheduler().cancelTask(task);
				}
				
				
				ArrayList<Material> m = new ArrayList<Material>();
				
				for (int x = 0 ; x <= size_x ; x++) {
					for (int y = 0 ; y <= size_y ; y++) {
						for (int z = 0 ; z <= size_z ; z++) {
							
							m.add(new Location(location.getWorld(), location.getBlockX() + x + 0.5, location.getBlockY() + y + 0.5, location.getBlockZ() + z + 0.5).getBlock().getType());
							new Location(location.getWorld(), location.getBlockX() + x + 0.5, location.getBlockY() + y + 0.5, location.getBlockZ() + z + 0.5).getBlock().setType(Material.AIR);
						
						}
					}
				}
				
				location.setY(location.getY() + incress);
				i++;
				for (int x = 0 ; x <= size_x ; x++) {
					for (int y = 0 ; y <= size_y ; y++) {
						for (int z = 0 ; z <= size_z ; z++) {
							
							new Location(location.getWorld(), location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z).getBlock().setType(m.get(0));
							m.remove(0);
							
						}
					}
				}
			}			
		
		}, 0, 5);
	}
	
	
	

}
