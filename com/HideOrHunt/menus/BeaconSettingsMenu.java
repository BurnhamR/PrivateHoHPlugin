package com.HideOrHunt.menus;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
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

public class BeaconSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Beacon Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§fCrafting @ Beacon")) {
        if (yamlConfiguration.getBoolean("gameSettings.beacon.crafting")) {
          yamlConfiguration.set("gameSettings.beacon.crafting", Boolean.valueOf(false));
          player.sendMessage("§aCrafting @ Beacons has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Crafting @ Beacons [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.beacon.crafting", Boolean.valueOf(true));
          player.sendMessage("§aCrafting @ Beacons has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Crafting @ Beacons [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryBeaconSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fRespawning @ Beacon")) {
        if (yamlConfiguration.getBoolean("gameSettings.beacon.respawn")) {
          yamlConfiguration.set("gameSettings.beacon.respawn", Boolean.valueOf(false));
          player.sendMessage("§aRespawning @ Beacons has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Respawning @ Beacons [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.beacon.respawn", Boolean.valueOf(true));
          player.sendMessage("§aRespawning @ Beacons has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Respawning @ Beacons [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryBeaconSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fLeader-Only Placing")) {
        if (yamlConfiguration.getBoolean("gameSettings.beacon.leaderOnlyPlace")) {
          yamlConfiguration.set("gameSettings.beacon.leaderOnlyPlace", Boolean.valueOf(false));
          player.sendMessage("§aLeader-Only Placing has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Leader-Only Placing [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.beacon.leaderOnlyPlace", Boolean.valueOf(true));
          player.sendMessage("§aLeader-Only Placing has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Leader-Only Placing [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryBeaconSettings((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
}
