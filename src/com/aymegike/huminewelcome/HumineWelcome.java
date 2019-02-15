package com.aymegike.huminewelcome;

import org.bukkit.plugin.java.JavaPlugin;

import com.aymegike.huminewelcome.manager.CinematicManager;
import com.aymegike.huminewelcome.manager.EventManager;
import com.aymegike.huminewelcome.manager.MapManager;
import com.aymegike.huminewelcome.manager.NewPlayerManager;
import com.aymegike.huminewelcome.utils.Map;

public class HumineWelcome extends JavaPlugin {
	
	
	private static NewPlayerManager npm;
	private static CinematicManager cm;
	private static MapManager mm;
	
	public void onEnable() {
		super.onEnable();
		
		new EventManager(this);
		npm = new NewPlayerManager();
		cm = new CinematicManager();
		mm = new MapManager();
	}
	
	public void onDisable() {
		super.onDisable();
		
		for (Map map : mm.getMaps()) {
			if (map != null)
				map.destroy();
		}
	}
	
	public static NewPlayerManager getNewPlayerManager() {
		return npm;
	}
	
	public static CinematicManager getCinematicManager() {
		return cm;
	}
	
	public static MapManager getMapManager() {
		return mm;
	}
}
