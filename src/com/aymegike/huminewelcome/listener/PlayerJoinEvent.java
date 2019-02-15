package com.aymegike.huminewelcome.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.aymegike.huminewelcome.HumineWelcome;
import com.aymegike.huminewelcome.utils.CinematicState;
import com.aymegike.huminewelcome.utils.Map;
import com.aymegike.huminewelcome.utils.cinematic.Cinematic;
import com.aymegike.huminewelcome.utils.cinematic.WakeUpCinematic;

public class PlayerJoinEvent implements Listener {
	
	@EventHandler
	public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		
		Player player = e.getPlayer();
		
		if (HumineWelcome.getNewPlayerManager().isNewPlayer(player)) {
			
			Map map = new Map(player.getName(), player);
			map.load();
			map.runCinematic(new WakeUpCinematic(player));
			
		} else if (HumineWelcome.getNewPlayerManager().getState(HumineWelcome.getNewPlayerManager().getPlayerLine(player)) == CinematicState.FINISH) {
			//NORMAL SPAWN
		} else {
			//LOAD SAVE
			Map map = new Map(player.getName(), player);
			map.load();
			Cinematic cinematic = HumineWelcome.getCinematicManager().getInstances(player, HumineWelcome.getNewPlayerManager().getState(HumineWelcome.getNewPlayerManager().getPlayerLine(player)));
			map.runCinematic(cinematic);
		}
		
	}

}
