package com.HideOrHunt.listeners;

import java.io.File;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerTouchEvent implements Listener {
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (event.hasBlock()) {
      Block block = event.getClickedBlock();
      Action action = event.getAction();
      if (block.getType() == Material.CRAFTING_TABLE) {
        if (!yamlConfiguration.getBoolean("gameSettings.craftingTablesEnabled") && 
          action.equals(Action.RIGHT_CLICK_BLOCK))
          event.setCancelled(true); 
      } else if (block.getType() == Material.BEACON) {
        if (yamlConfiguration.getBoolean("gameSettings.beacon.crafting") && 
          action.equals(Action.RIGHT_CLICK_BLOCK)) {
          event.setCancelled(true);
          player.openWorkbench(null, true);
        } 
      } else if ((block.getType() == Material.BLACK_BED || block.getType() == Material.BLUE_BED || block.getType() == Material.BROWN_BED || block.getType() == Material.CYAN_BED || block.getType() == Material.GRAY_BED || block.getType() == Material.GREEN_BED || block.getType() == Material.LIGHT_BLUE_BED || block.getType() == Material.LIGHT_GRAY_BED || block.getType() == Material.LIME_BED || block.getType() == Material.MAGENTA_BED || block.getType() == Material.ORANGE_BED || block.getType() == Material.PINK_BED || block.getType() == Material.PURPLE_BED || block.getType() == Material.RED_BED || block.getType() == Material.WHITE_BED || (block.getType() == Material.YELLOW_BED && action == Action.RIGHT_CLICK_BLOCK)) && 
        yamlConfiguration.getBoolean("gameSettings.beacon.respawn") && 
        action.equals(Action.RIGHT_CLICK_BLOCK)) {
        event.setCancelled(true);
        player.sendMessage("§cYour Beacon is your Respawn Point - You cannot set a Bed Spawn!");
      } 
    } 
  }
}
