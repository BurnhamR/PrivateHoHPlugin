package com.HideOrHunt.menus;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class TeamNameCharacterMenu implements Listener, InventoryHolder {
  public Inventory getInventory() {
    return null;
  }
  
  @EventHandler
  public void InventoryClick(InventoryClickEvent event) {
    Player player = (Player)event.getWhoClicked();
    UUID playerUUID = player.getUniqueId();
    ClickType click = event.getClick();
    ItemStack item = event.getCurrentItem();
    InventoryView menu = event.getView();
    int slotClicked = event.getSlot();
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (menu.getTitle().equals("Maximum Team Name Characters")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getAmount() <= yamlConfiguration.getInt("teamsSettings.minTeamNameCharacters")) {
        player.sendMessage("§cThe Maximum Number of Characters cannot be lower or equal to the Minimum Number of Characters.");
        return;
      } 
      yamlConfiguration.set("teamsSettings.maxTeamNameCharacters", Integer.valueOf(item.getAmount()));
      player.sendMessage("§aThe Maximum Number of Characters in a Team Name was updated to §n" + item.getAmount() + "§a.");
      try {
        yamlConfiguration.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Updating Maximum Team Name Characters [TeamSettings].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      player.closeInventory();
    } else if (menu.getTitle().equals("Minimum Team Name Characters")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getAmount() >= yamlConfiguration.getInt("teamsSettings.maxTeamNameCharacters")) {
        player.sendMessage("§cThe Minimum Number of Characters cannot be higher or equal to the Maximum Number of Characters.");
        return;
      } 
      yamlConfiguration.set("teamsSettings.minTeamNameCharacters", Integer.valueOf(item.getAmount()));
      player.sendMessage("§aThe Minimum Number of Characters in a Team Name was updated to §n" + item.getAmount() + "§a.");
      try {
        yamlConfiguration.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Updating Minimum Team Name Characters [TeamSettings].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      player.closeInventory();
    } 
  }
}
