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

public class InvisibilityDurationMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Invisibility Duration")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      yamlConfiguration.set("gameSettings.invis.duration", Integer.valueOf(item.getAmount() * 60));
      player.sendMessage("§aThe Duration of Invisibility was updated to §n" + item.getAmount() + "§a minutes.");
      try {
        yamlConfiguration.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Updating Invisibility Duration [GameSettings].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      player.closeInventory();
    } else if (menu.getTitle().equals("Invisibility Delay")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      yamlConfiguration.set("gameSettings.invis.timeBeforeGive", Integer.valueOf(item.getAmount() * 60));
      player.sendMessage("§aThe Delay Before Giving of Invisibility was updated to §n" + item.getAmount() + "§a minutes.");
      try {
        yamlConfiguration.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Updating Invisibility Delay [GameSettings].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      player.closeInventory();
    } 
  }
}
