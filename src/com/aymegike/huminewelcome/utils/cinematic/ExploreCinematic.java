package com.aymegike.huminewelcome.utils.cinematic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.aymegike.huminewelcome.utils.CinematicState;
import com.aymegike.huminewelcome.utils.Door;
import com.aymegike.huminewelcome.utils.PlaySound;
import com.aypi.utils.Timer;
import com.aypi.utils.inter.TimerFinishListener;

public class ExploreCinematic extends Cinematic {

	public static final CinematicState CINEMATIC_STATE = CinematicState.EXPLORE;
	
	private Player player;
	private World world;
	
	private PlaySound music;
	private PlaySound random;
	
	private PlaySound melodie_1, melodie_2, melodie_3, melodie_4;
	
	private int taskMusic;
	private int taskRandom;
	private int taskSound;
	
	public ExploreCinematic(Player player) {
		super(player, CINEMATIC_STATE);
		
		this.player = player;
		this.world = getWorld();
		
		this.music = new PlaySound(player, "music_part_1");
		this.random = new PlaySound(player, "inactive");
		
		melodie_1 = new PlaySound(player, "music_part_melodie_1");
		melodie_2 = new PlaySound(player, "music_part_melodie_2");
		melodie_3 = new PlaySound(player, "music_part_melodie_3");
		melodie_4 = new PlaySound(player, "music_part_melodie_4");
	}
	
	private void loopMusic() {
		taskMusic = Bukkit.getScheduler().scheduleSyncRepeatingTask(PLUGIN, new Runnable() {
			
			int i = 0;
			
			@Override
			public void run() {
//				music.play(false, 5);
				
				switch(i) {
					
					case 0:
						melodie_1.play();
						i++;
						break;
					case 1:
						melodie_2.play();
						i++;
						break;
					case 2:
						melodie_3.play();
						i++;
						break;
					case 3:
						melodie_4.play();
						i = 0;
						break;
				}
				
			}
		}, 0, 20*21 + 10);
	}

	@Override
	public void start() {
		if (!player.getLocation().getWorld().getName().equals(player.getName()))
			player.teleport(new Location(world, (float) -6.5, (float) 51, (float) 0.4, (float) -90, (float) 0));
		loopMusic();
		new PlaySound(player, "sas_ouverture").play();
		new Door(new Location(world, 5, 51, -1), 0, 5, 2, 3).changeSate();
		
		taskRandom = Bukkit.getScheduler().scheduleSyncRepeatingTask(PLUGIN, new Runnable() {
			
			Location loc = null;
			
			@Override
			public void run() {
				if (loc == player.getLocation()) {
					random.play();
				} else {
					loc = player.getLocation();
				}
			}
			
		}, 0, 20*5);
		
		
		
		taskSound = Bukkit.getScheduler().scheduleSyncRepeatingTask(PLUGIN, new Runnable() {
			
			@Override
			public void run() {
				PlaySound space = new PlaySound(player, "space_ambient");
				space.play();
				
			}
			
		}, 0, 20*5 - 5);
		
		
		new Timer(PLUGIN, 15, new TimerFinishListener() {
			
			@Override
			public void execute() {
				new PlaySound(player, "sas_ouverture").play();
				new Door(new Location(world, 33, 51, -1), 0, 10, 2, 3).changeSate();
				
			}
			
		}).start();
	}

	@Override
	protected void stop() {
		Bukkit.getScheduler().cancelTask(taskMusic);
		Bukkit.getScheduler().cancelTask(taskRandom);
		Bukkit.getScheduler().cancelTask(taskSound);
	}

	@Override
	public Cinematic newInstance(Player player) {
		return new ExploreCinematic(player);
	}

}
