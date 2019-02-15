package com.aymegike.huminewelcome.manager;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.aymegike.huminewelcome.utils.CinematicState;
import com.aypi.manager.FileManager;

public class NewPlayerManager {
	
	FileManager filePlayerList;
	
	public NewPlayerManager() {
		this.filePlayerList = new FileManager(new File("plugins/huminewelcome/player_list.craft"));
	}
	
	public void addPlayer(Player player, CinematicState state) {
		filePlayerList.printLine(player.getUniqueId().toString()+" "+state);
	}
	
	@SuppressWarnings("deprecation")
	public OfflinePlayer getPlayer(String line) {
		String[] args = line.split(" ");
		return Bukkit.getOfflinePlayer(args[0]);
	}
	
	public CinematicState getState(String line) {
		String[] args = line.split(" ");
		return CinematicState.valueOf(args[1]);
	}
	
	public void update(Player player, CinematicState state) {
		removePlayer(player);
		addPlayer(player, state);
	}
	
	public void removePlayer(Player player) {
		for (int i = 0 ; i < filePlayerList.getTextFile().size() ; i++) {
			String line = filePlayerList.getTextFile().get(i);
			if (line.contains(player.getUniqueId().toString())) {
				filePlayerList.removeLine(line);
			}
		}
	}
	
	public String getPlayerLine(Player player) {
		for (String line : filePlayerList.getTextFile()) {
			if (line.contains(player.getUniqueId().toString())) {
				return line;
			}
		}
		return null;
	}
	
	public boolean isNewPlayer(Player player) {
		for (String line : filePlayerList.getTextFile()) {
			if (line.contains(player.getUniqueId().toString())) {
				return false;
			}
		}
		return true;
	}
	
	public FileManager getFilePlayerList() {
		return filePlayerList;
	}

}
