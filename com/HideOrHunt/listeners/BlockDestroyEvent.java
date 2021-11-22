package com.HideOrHunt.listeners;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.utilities.BeaconUtilities;
import java.io.File;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockDestroyEvent implements Listener {
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();
    Block block = event.getBlock();
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (block.getType() == Material.BEACON && 
      yamlConfiguration.getBoolean("gameSettings.active") && 
      BeaconUtilities.searchBeacon(HideOrHunt.getInstance(), player, block.getLocation())) {
      event.setDropItems(false);
      event.setCancelled(true);
      BeaconUtilities.destroyBeacon(HideOrHunt.getInstance(), player, BeaconUtilities.getBeacon(HideOrHunt.getInstance(), player, block.getLocation()), block.getLocation());
    } 
  }
}
