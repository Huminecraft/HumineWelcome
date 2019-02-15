package com.aymegike.huminewelcome.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.aymegike.huminewelcome.HumineWelcome;
import com.aypi.utils.Timer;
import com.aypi.utils.inter.TimerFinishListener;

public class PlayerLeftEvent implements Listener {
	
	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent e) {
		
		Player player = e.getPlayer();
		player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
		new Timer(Bukkit.getPluginManager().getPlugin("Aypi"), 2, new TimerFinishListener() {
			
			@Override
			public void execute() {
				if (HumineWelcome.getMapManager().getMap(player.getName()) != null) {
					HumineWelcome.getMapManager().getMap(player.getName()).destroy();
				}
			}
			
		}).start();
		
	}

}
