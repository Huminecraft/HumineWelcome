package com.aymegike.huminewelcome.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileDeleteStrategy;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import com.aymegike.huminewelcome.HumineWelcome;
import com.aymegike.huminewelcome.manager.MapManager;
import com.aymegike.huminewelcome.utils.cinematic.Cinematic;
import com.aypi.manager.FileManager;
import com.aypi.utils.Timer;
import com.aypi.utils.inter.TimerFinishListener;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_13_R2.RegionFileCache;

public class Map {
	
	private String name;
	private Player player;
	
	public Map(String name, Player player) {
		
		this.name = name;
		this.player = player;
		HumineWelcome.getMapManager().addMap(this);
	}
	
	public void load() {
		File file = new File(name);
		file.mkdirs();
		
		try {
			for (File content : new File(MapManager.PATH).listFiles())
				FileManager.copyFullRecursive(content, file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Bukkit.getServer().createWorld(new WorldCreator(name));
	}	

	public void destroy() {
		World world = Bukkit.getWorld(name);
		delete(world);
		HumineWelcome.getMapManager().removeMap(this);
	}
	
	private void delete(World world) {
		new Timer(Bukkit.getPluginManager().getPlugin("HumineWelcome"), 1, new TimerFinishListener() {
			
			@Override
			public void execute() {
				for (Player player : world.getPlayers()) {
					player.kickPlayer(ChatColor.DARK_PURPLE+"La map est en train d'être supprimer...");
				}
				for (Chunk chunk : world.getLoadedChunks()) {
					chunk.unload();
					world.unloadChunk(chunk);
					
				}
				Bukkit.getServer().unloadWorld(world, false);
				RegionFileCache.a();
				FileDeleteStrategy.FORCE.deleteQuietly(new File(name));
				
				new Timer(Bukkit.getPluginManager().getPlugin("HumineWelcome"), 1, new TimerFinishListener() {
					
					@Override
					public void execute() {
						if (new File(name).exists()) {
							System.out.println("retry: to delete "+name);
							delete(world);
						} else {
							System.out.println("success "+name);
						}
					}
				}).start();
				
				
			}
		}, true).start();
		
	}

	public void runCinematic(Cinematic cinematic) {
		cinematic.start();
	}
	
	public String getName() {
		return name;
	}
	
	public Player getPlayer() {
		return player;
	}

}
