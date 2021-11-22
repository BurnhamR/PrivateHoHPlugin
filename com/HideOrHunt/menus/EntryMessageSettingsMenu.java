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

public class EntryMessageSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Entry Message Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§cDisabled")) {
        yamlConfiguration.set("serverSettings.entryMessages", "off");
        player.sendMessage("§aEntry Messages set to §nDisabled§a.");
        try {
          yamlConfiguration.save(configFile);
          Bukkit.getLogger().info("Hide or Hunt > Updating the Entry Messages to Disabled [ServerSettings]");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else if (item.getItemMeta().getDisplayName().equals("§aEnabled")) {
        yamlConfiguration.set("serverSettings.entryMessages", "on");
        player.sendMessage("§aEntry Messages set to §nEnabled§a.");
        try {
          yamlConfiguration.save(configFile);
          Bukkit.getLogger().info("Hide or Hunt > Updating the Entry Messages to Enabled [ServerSettings]");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else if (item.getItemMeta().getDisplayName().equals("§dOPs Only")) {
        yamlConfiguration.set("serverSettings.entryMessages", "op");
        player.sendMessage("§aEntry Messages set to §nOP Only§a.");
        try {
          yamlConfiguration.save(configFile);
          Bukkit.getLogger().info("Hide or Hunt > Updating the Entry Messages to OP Only [ServerSettings]");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } 
      player.closeInventory();
    } 
  }
}
