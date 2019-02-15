package com.aymegike.huminewelcome.manager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import com.aymegike.huminewelcome.HumineWelcome;
import com.aymegike.huminewelcome.listener.PlayerJoinEvent;
import com.aymegike.huminewelcome.listener.PlayerLeftEvent;

public class EventManager {

	public EventManager(HumineWelcome pl) {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoinEvent(), pl);
		pm.registerEvents(new PlayerLeftEvent(), pl);
	}
	
}
