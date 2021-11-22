package com.HideOrHunt.listeners;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitServerEvent implements Listener {
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (yamlConfiguration.getString("serverSettings.entryMessages").equals("off")) {
      event.setQuitMessage("");
    } else if (yamlConfiguration.getString("serverSettings.entryMessages").equals("op")) {
      event.setQuitMessage("");
      for (Player oplayer : Bukkit.getOnlinePlayers()) {
        if (oplayer.isOp())
          oplayer.sendMessage("§e" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + " has left the game!"); 
      } 
    } else if (yamlConfiguration.getString("serverSettings.entryMessages").equals("on")) {
      event.setQuitMessage("§e" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + " has left the game!");
    } 
  }
}
