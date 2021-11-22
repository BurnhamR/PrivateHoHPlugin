package com.HideOrHunt.listeners;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKickedServerEvent implements Listener {
  @EventHandler
  public void onPlayerKicked(PlayerKickEvent event) {
    Player player = event.getPlayer();
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (yamlConfiguration.getString("serverSettings.entryMessages").equals("off")) {
      event.setLeaveMessage("");
    } else if (yamlConfiguration.getString("serverSettings.entryMessages").equals("op")) {
      event.setLeaveMessage("");
      for (Player oplayer : Bukkit.getOnlinePlayers()) {
        if (oplayer.isOp())
          oplayer.sendMessage("§e" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + " has been kicked from the game for " + event.getReason() + "."); 
      } 
    } else if (yamlConfiguration.getString("serverSettings.entryMessages").equals("on")) {
      event.setLeaveMessage("§e" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + " has been kicked from the game for " + event.getReason() + ".");
    } 
  }
}
