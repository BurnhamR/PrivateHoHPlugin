package com.HideOrHunt.listeners;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class MobBreedEvent implements Listener {
  @EventHandler
  public void onMobBreedEvent(EntityBreedEvent event) {
    LivingEntity livingEntity = event.getEntity();
    File settingsFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(settingsFile);
    if (livingEntity instanceof org.bukkit.entity.Bee) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.bee"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Cat) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.cat"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Chicken) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.chicken"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Donkey) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.donkey"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Fox) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.fox"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Hoglin) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.hoglin"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Horse) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.horse"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Llama) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.llama"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.TraderLlama) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.llama"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Mule) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.mule"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.MushroomCow) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.mushroomcow"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Ocelot) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.ocelot"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Panda) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.panda"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Pig) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.pig"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Rabbit) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.rabbit"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Sheep) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.sheep"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Strider) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.strider"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Turtle) {
      if (!yamlConfiguration.getBoolean("mobSettings.mobBreed.turtle"))
        event.setCancelled(true); 
    } else if (livingEntity instanceof org.bukkit.entity.Wolf && 
      !yamlConfiguration.getBoolean("mobSettings.mobBreed.wolf")) {
      event.setCancelled(true);
    } 
  }
}
