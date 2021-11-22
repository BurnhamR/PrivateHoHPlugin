package com.HideOrHunt.listeners;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

public class MobTameEvent implements Listener {
  @EventHandler
  public void onMobSpawn(EntityTameEvent event) {
    LivingEntity livingEntity = event.getEntity();
    File settingsFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(settingsFile);
    if (livingEntity instanceof org.bukkit.entity.Cat) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobTame.cat"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Donkey) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobTame.donkey"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Horse) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobTame.horse"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Llama) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobTame.llama"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Mule) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobTame.mule"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Ocelot) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobTame.ocelot"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Parrot) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobTame.parrot"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.SkeletonHorse) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobTame.skeletonhorse"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Wolf && 
      !yamlConfiguration.getBoolean("mobSettings.mobTame.wolf")) {
      event.setCancelled(true);
    } 
  }
}
