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

public class TeamTypeMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Team Type")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getAmount() == 1) {
        yamlConfiguration.set("teamsSettings.type", "SOLO");
        player.sendMessage("§aThe Game Type was updated to §nSolo§a. §8[§71 player§8]");
        try {
          yamlConfiguration.save(configFile);
          Bukkit.getLogger().info("Hide or Hunt > Updating Team Type [TeamSettings].");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else if (item.getAmount() == 2) {
        yamlConfiguration.set("teamsSettings.type", "DUO");
        player.sendMessage("§aThe Game Type was updated to §nDuo§a. §8[§72 players§8]");
        try {
          yamlConfiguration.save(configFile);
          Bukkit.getLogger().info("Hide or Hunt > Updating Team Type [TeamSettings].");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else if (item.getAmount() == 3) {
        yamlConfiguration.set("teamsSettings.type", "TRIO");
        player.sendMessage("§aThe Game Type was updated to §nTrio§a. §8[§73 players§8]");
        try {
          yamlConfiguration.save(configFile);
          Bukkit.getLogger().info("Hide or Hunt > Updating Team Type [TeamSettings].");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else if (item.getAmount() == 4) {
        yamlConfiguration.set("teamsSettings.type", "QUAD");
        player.sendMessage("§aThe Game Type was updated to §nQuad§a. §8[§74 players§8]");
        try {
          yamlConfiguration.save(configFile);
          Bukkit.getLogger().info("Hide or Hunt > Updating Team Type [TeamSettings].");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else if (item.getAmount() == 10) {
        yamlConfiguration.set("teamsSettings.type", "SQUAD10");
        player.sendMessage("§aThe Game Type was updated to §nSquad§a. §8[§710 players§8]");
        try {
          yamlConfiguration.save(configFile);
          Bukkit.getLogger().info("Hide or Hunt > Updating Team Type [TeamSettings].");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } 
      player.closeInventory();
    } 
  }
}
