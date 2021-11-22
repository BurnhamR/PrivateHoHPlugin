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

public class SlowfallDurationMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Slowfall Duration")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      String[] itemParts = item.getItemMeta().getDisplayName().split("-");
      String itemName = itemParts[0];
      itemName = itemName.substring(itemName.indexOf("§") + 2);
      yamlConfiguration.set("gameSettings.slowfall.duration", Integer.valueOf(Integer.parseInt(itemName)));
      player.sendMessage("§aThe Duration of Slowfall was updated to §n" + itemName + "§a seconds.");
      try {
        yamlConfiguration.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Updating Slowfall Duration [GameSettings].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      player.closeInventory();
    } 
  }
}
