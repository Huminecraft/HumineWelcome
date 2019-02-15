package com.aymegike.huminewelcome.utils.cinematic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.aymegike.huminewelcome.HumineWelcome;
import com.aymegike.huminewelcome.utils.CinematicState;
import com.aymegike.huminewelcome.utils.PlaySound;
import com.aypi.utils.Timer;
import com.aypi.utils.inter.TimerFinishListener;

public class WakeUpCinematic extends Cinematic {

	public static final CinematicState CINEMATIC_STATE = CinematicState.WAKE_UP;
	
	private Player player;
	private World world;
	
	private int taskSound;
	private int taskRandom;
	private int taskMusic;
	
	private boolean can_go = false;
	private boolean next_step = false;
	
	private PlaySound emeteur_sonore;
	
	private PlaySound alarm_2;
	private PlaySound alarm_3;
	
	private PlaySound ouverture_capsule;
	private PlaySound jour_inactivite;
	
	private PlaySound dialogue_1;
	
	private PlaySound random;
	
	private PlaySound intro;
	
	public WakeUpCinematic(Player player) {
		super(player, CINEMATIC_STATE);
		this.player = getPlayer();
		this.world = getWorld();
		
		emeteur_sonore = new PlaySound(player, "emeteur_sonore");
		
		alarm_2 = new PlaySound(player, "alarme_2");
		alarm_3 = new PlaySound(player, "alarme_3");
		
		ouverture_capsule = new PlaySound(player, "ouverture_capsule_robot");
		jour_inactivite = new PlaySound(player, "jour_inactivite_robot");
		
		dialogue_1 = new PlaySound(player, "dialogue_1");
		
		random = new PlaySound(player, "inactive");
		
		intro = new PlaySound(player, "music_part_intro");
	}
	
	private void startMusic() {
		
		intro.play(false, 5);
		new Timer(PLUGIN, 20*21+10, new TimerFinishListener() {
			
			@Override
			public void execute() {
				loopMusic();
				
			}
		}, false).start();
		
	}
	
	private void loopMusic() {
		taskMusic = Bukkit.getScheduler().scheduleSyncRepeatingTask(PLUGIN, new Runnable() {
			
			@Override
			public void run() {
				if (!next_step) {
					intro.play(false, 5);
				} else {
					stop();
					Cinematic c = HumineWelcome.getCinematicManager().getInstances(player, CinematicState.EXPLORE);
					HumineWelcome.getMapManager().getMap(player.getName()).runCinematic(c);
				}
			}
		}, 0, 20*21);
	}

	@Override
	public void start() {
		player.teleport(new Location(world, (float) -6.5, (float) 52, (float) 4.5, (float) 180, (float) 90));
		
		PlaySound space = new PlaySound(player, "space_ambient");
		
		taskSound = Bukkit.getScheduler().scheduleSyncRepeatingTask(PLUGIN, new Runnable() {
			
			@Override
			public void run() {
				
				space.play();
				
			}
			
		}, 0, 20*5 - 5);
		
		startMusic();
		
		taskRandom = Bukkit.getScheduler().scheduleSyncRepeatingTask(PLUGIN, new Runnable() {
			
			@Override
			public void run() {
				if (can_go)
					random.play();
					
			}
			
		}, 0, 20*30);
		
		new Timer(PLUGIN, 5, new TimerFinishListener() { //up head
			
			@Override
			public void execute() {
				
				
				
				new Timer(PLUGIN, 3, new TimerFinishListener() { //BIB avant l'eau
					
					@Override
					public void execute() {
						emeteur_sonore.play();
					}
					
				}).start();
				
				new Timer(PLUGIN, 6, new TimerFinishListener() {// ouverture capsule
					
					@Override
					public void execute() {
						
						ouverture_capsule.play();
						
						new Timer(PLUGIN, 3, new TimerFinishListener() {
							
							@Override
							public void execute() {
								alarm_2.play();
							}
						}).start();
						
						new Timer(PLUGIN, 5, new TimerFinishListener() {
							
							@Override
							public void execute() {
								new Location(world, -7, 52, 3).getBlock().setType(Material.AIR);
								new Location(world, -7, 53, 3).getBlock().setType(Material.AIR);
							}
							
						}).start();
						
						new Timer(PLUGIN, 5, new TimerFinishListener() {
							
							@Override
							public void execute() {
								alarm_3.play();
							}
							
						}).start();
						
						new Timer(PLUGIN, 10, new TimerFinishListener() {
							
							@Override
							public void execute() {
								jour_inactivite.play();
							}
							
						}).start();
						

						
					}
					
				}).start();
				
				new Timer(PLUGIN, 5, new TimerFinishListener() { //suppression de la source d'eau au niveau de la tête
					
					@Override
					public void execute() {
						new Location(getWorld(), -7, 54, 4).getBlock().setType(Material.PRISMARINE_BRICKS);
					}
					
				}).start();	
				

				
				
				new Timer(PLUGIN, 20, new TimerFinishListener() { //play first dialogue
					
					@Override
					public void execute() {
						dialogue_1.play();
						
						new Timer(PLUGIN, 30, new TimerFinishListener() {
							
							@Override
							public void execute() {
								can_go = true;

								next_step = true;
							}
							
						}).start();
					}
					
				}).start();
				
			}
		}).start();
		
		
		
	}
	
	protected void stop() {
		Bukkit.getScheduler().cancelTask(taskSound);
		Bukkit.getScheduler().cancelTask(taskRandom);
		Bukkit.getScheduler().cancelTask(taskMusic);
	}

	@Override
	public Cinematic newInstance(Player player) {
		return new WakeUpCinematic(player);
	}

}
