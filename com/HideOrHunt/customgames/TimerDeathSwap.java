package com.HideOrHunt.customgames;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.ConfigRegistery;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import io.netty.util.internal.ThreadLocalRandom;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TimerDeathSwap {
  public static void timerSwap(HideOrHunt plugin, boolean withPeace, FileConfiguration config, FileConfiguration defaultConfig) {
    if (withPeace) {
      if (defaultConfig.getBoolean("timerdeathswap.fixedSwapTime")) {
        int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)plugin, new Runnable(defaultConfig) {
              int time;
              
              public void run() {
                File customConfigFile = new File("plugins/PrivateHideOrHunt/", "custom.yml");
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(customConfigFile);
                if (this.time == yamlConfiguration.getInt("timerdeathswap.timeBetweenSwaps"))
                  Bukkit.broadcastMessage("§cSwapping Locations with another Player in §n" + (yamlConfiguration.getInt("timerdeathswap.timeBetweenSwaps") / 60) + " minutes!"); 
                if (this.time == 0) {
                  TimerDeathSwap.swapServerPlayers((FileConfiguration)yamlConfiguration);
                  this.time = yamlConfiguration.getInt("timerdeathswap.timeBetweenSwaps");
                  Bukkit.broadcastMessage("§cSwapping Locations with another Player in §n" + (yamlConfiguration.getInt("timerdeathswap.timeBetweenSwaps") / 60) + " minutes!");
                } else {
                  if (this.time == 30)
                    Bukkit.broadcastMessage("§cSwapping Locations with another Player in §n" + this.time + " seconds!"); 
                  if (this.time <= 10)
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + this.time + "\",\"color\":\"red\",\"bold\":true}"); 
                  this.time--;
                } 
              }
            }0L, 20L);
        ConfigRegistery.setTaskID(plugin, "timerdeathswap", "swappingTask", taskID);
      } 
    } else if (defaultConfig.getBoolean("timerdeathswap.fixedSwapTime")) {
      int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)plugin, new Runnable(defaultConfig) {
            int time;
            
            public void run() {
              File customConfigFile = new File("plugins/PrivateHideOrHunt/", "custom.yml");
              YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(customConfigFile);
              if (this.time == yamlConfiguration.getInt("timerdeathswap.timeBetweenSwaps"))
                Bukkit.broadcastMessage("§cSwapping Locations with another Player in §n" + (yamlConfiguration.getInt("timerdeathswap.timeBetweenSwaps") / 60) + " minutes!"); 
              if (this.time == 0) {
                TimerDeathSwap.swapServerPlayers((FileConfiguration)yamlConfiguration);
                this.time = yamlConfiguration.getInt("timerdeathswap.timeBetweenSwaps");
              } else {
                if (this.time == 30)
                  Bukkit.broadcastMessage("§cSwapping Locations with another Player in §n" + this.time + " seconds!"); 
                if (this.time <= 10)
                  Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + this.time + "\",\"color\":\"red\",\"bold\":true}"); 
                this.time--;
              } 
            }
          }(config.getInt("gameSettings.peaceful.delayToRemove") * 20 + 200), 20L);
      ConfigRegistery.setTaskID(plugin, "timerdeathswap", "swappingTask", taskID);
    } else if (!defaultConfig.getBoolean("timerdeathswap.fixedSwapTime")) {
      int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)plugin, new Runnable() {
            int time = ThreadLocalRandom.current().nextInt(1, 3) * 60;
            
            int originalTime = this.time;
            
            public void run() {
              File customConfigFile = new File("plugins/PrivateHideOrHunt/", "custom.yml");
              YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(customConfigFile);
              if (this.time == this.originalTime)
                Bukkit.broadcastMessage("§cSwapping Locations with another Player in §n" + (this.originalTime / 60) + " minutes!"); 
              if (this.time == 0) {
                TimerDeathSwap.swapServerPlayers((FileConfiguration)yamlConfiguration);
                this.time = ThreadLocalRandom.current().nextInt(1, 3) * 60;
                this.originalTime = this.time;
              } else {
                if (this.time == 30)
                  Bukkit.broadcastMessage("§cSwapping Locations with another Player in §n" + this.time + " seconds!"); 
                if (this.time <= 10)
                  Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + this.time + "\",\"color\":\"red\",\"bold\":true}"); 
                this.time--;
              } 
            }
          }(config.getInt("gameSettings.peaceful.delayToRemove") * 20 + 200), 20L);
      ConfigRegistery.setTaskID(plugin, "timerdeathswap", "swappingTask", taskID);
    } 
  }
  
  public static void swapServerPlayers(FileConfiguration customConfig) {
    List<Player> players = new ArrayList<>();
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.isDead())
        continue; 
      if (!PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player))
        continue; 
      if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR))
        continue; 
      if (!PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player) || 
        TeamRegistery.getPlayerEliminated(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player), player.getUniqueId().toString()))
        continue; 
      players.add(player);
    } 
    Collections.shuffle(players);
    for (int i = 0; i < players.size(); i += 2) {
      if (!players.isEmpty()) {
        if (players.size() - i == 1) {
          ((Player)players.get(players.size() - 1)).sendMessage("§aLucky! You missed the Swap!");
          Player plucky = players.get(players.size() - 1);
          plucky.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 800, 0));
          plucky.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 800, 0));
          plucky.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 800, 0));
          players.clear();
          break;
        } 
        if (players.size() >= 2) {
          Player pone = players.get(i);
          Player ptwo = players.get(i + 1);
          Location lone = pone.getLocation();
          Location ltwo = ptwo.getLocation();
          pone.teleport(ltwo);
          ptwo.teleport(lone);
          if (customConfig.getBoolean("timerdeathswap.notifyOfSwapper")) {
            pone.sendMessage("§aYou swapped positions with §f" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), ptwo));
            ptwo.sendMessage("§aYou swapped positions with §f" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), pone));
          } 
        } else {
          Bukkit.broadcastMessage("ERROR");
          players.clear();
          break;
        } 
      } 
    } 
  }
}
