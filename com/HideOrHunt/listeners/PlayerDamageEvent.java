package com.HideOrHunt.listeners;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageEvent implements Listener {
  @EventHandler
  public void onPlayerDamage(EntityDamageEvent event) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (event.getEntity() instanceof org.bukkit.entity.Player)
      if (yamlConfiguration.getBoolean("gameSettings.peaceful.enabled")) {
        event.setCancelled(true);
        return;
      }  
  }
}
