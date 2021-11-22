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

public class DeathChanceMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("XP Chances")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      double chance = ((item.getAmount() - 1) * 5) / 100.0D;
      int displayChance = (item.getAmount() - 1) * 5;
      yamlConfiguration.set("gameSettings.death.chanceXPSave", Double.valueOf(chance));
      player.sendMessage("§aKeep XP on Death Chances have been set to §n" + displayChance + "%§a.");
      try {
        yamlConfiguration.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Updating XP Chance on Death [GameSettings].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      player.closeInventory();
    } else if (menu.getTitle().equals("Item Chances")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      double chance = ((item.getAmount() - 1) * 5) / 100.0D;
      int displayChance = (item.getAmount() - 1) * 5;
      yamlConfiguration.set("gameSettings.death.chanceItemSave", Double.valueOf(chance));
      player.sendMessage("§aKeep Item on Death Chances have been set to §n" + displayChance + "%§a.");
      try {
        yamlConfiguration.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Updating Item Chance on Death [GameSettings].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      player.closeInventory();
    } 
  }
}
