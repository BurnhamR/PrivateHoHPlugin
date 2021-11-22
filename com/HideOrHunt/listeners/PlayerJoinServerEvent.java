package com.HideOrHunt.listeners;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinServerEvent implements Listener {
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    PlayerRegistery.updatePlayerFile(HideOrHunt.getInstance(), player);
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (yamlConfiguration.getString("serverSettings.entryMessages").equals("off")) {
      event.setJoinMessage("");
    } else if (yamlConfiguration.getString("serverSettings.entryMessages").equals("op")) {
      event.setJoinMessage("");
      for (Player oplayer : Bukkit.getOnlinePlayers()) {
        if (oplayer.isOp())
          oplayer.sendMessage("§e" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + " has joined the game!"); 
      } 
    } else if (yamlConfiguration.getString("serverSettings.entryMessages").equals("on")) {
      event.setJoinMessage("§e" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + " has joined the game!");
    } 
  }
}
