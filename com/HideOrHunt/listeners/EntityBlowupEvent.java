package com.HideOrHunt.listeners;

import java.io.File;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityBlowupEvent implements Listener {
  @EventHandler
  public void onEntityExplode(EntityExplodeEvent event) {
    File settingsFile = new File("plugins/PrivateHideOrHunt/", "explode.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(settingsFile);
    ConfigurationSection blacklistedItems = yamlConfiguration.getConfigurationSection("blacklist");
    for (Block block : event.blockList()) {
      Material type = block.getType();
      for (String item : blacklistedItems.getKeys(false)) {
        if (!yamlConfiguration.getBoolean("blacklist." + item + ".valid"))
          continue; 
        if (type == Material.getMaterial(yamlConfiguration.getString("blacklist." + item + ".material")))
          event.setCancelled(true); 
      } 
    } 
  }
}
