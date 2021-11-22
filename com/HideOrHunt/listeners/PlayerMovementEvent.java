package com.HideOrHunt.listeners;

import java.io.File;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerMovementEvent implements Listener {
  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();
    Location playerLocation = player.getLocation();
    File settingsFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(settingsFile);
    if (yamlConfiguration.getBoolean("gameSettings.slowfall.enabled") && 
      yamlConfiguration.getBoolean("gameSettings.slowfall.removeOnContact") && 
      player.hasPotionEffect(PotionEffectType.SLOW_FALLING)) {
      Block blockBelow = player.getWorld().getBlockAt(new Location(playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY() - 2.0D, playerLocation.getZ()));
      if (blockBelow.getType() != Material.AIR)
        player.removePotionEffect(PotionEffectType.SLOW_FALLING); 
    } 
  }
}
