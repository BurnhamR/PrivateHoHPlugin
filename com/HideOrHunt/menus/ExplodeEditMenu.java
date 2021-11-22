package com.HideOrHunt.menus;

import com.HideOrHunt.utilities.ExplodeUtilities;
import java.io.File;
import java.util.UUID;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ExplodeEditMenu implements Listener, InventoryHolder {
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
    File configFile = new File("plugins/PrivateHideOrHunt/", "explode.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (menu.getItem(slotClicked) == null)
      return; 
    if (menu.getTitle().contains("Explode Blacklist") && 
      !item.getType().isBlock())
      event.setCancelled(true); 
  }
  
  @EventHandler
  public void InventoryClose(InventoryCloseEvent event) {
    Player player = (Player)event.getPlayer();
    UUID playerUUID = player.getUniqueId();
    InventoryView menu = event.getView();
    Inventory inv = event.getInventory();
    File configFile = new File("plugins/PrivateHideOrHunt/", "explode.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (menu.getTitle().contains("Explode Blacklist"))
      ExplodeUtilities.saveBlacklistedBlocks((FileConfiguration)yamlConfiguration, configFile, inv); 
  }
}
