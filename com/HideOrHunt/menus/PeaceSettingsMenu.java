package com.HideOrHunt.menus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import org.bukkit.inventory.meta.ItemMeta;

public class PeaceSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Peace Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§fPeace Status")) {
        if (yamlConfiguration.getBoolean("gameSettings.peaceful.enabled")) {
          yamlConfiguration.set("gameSettings.peaceful.enabled", Boolean.valueOf(false));
          player.sendMessage("§aPeace has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Peace Status [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.peaceful.enabled", Boolean.valueOf(true));
          player.sendMessage("§aPeace has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Peace Status [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryPeaceSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fPeace Delay")) {
        player.openInventory(inventoryPeaceDelay((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
  
  public Inventory inventoryPeaceDelay(FileConfiguration config) {
    Inventory peaceDelayMenu = Bukkit.getServer().createInventory(null, 54, "Peace Delay");
    for (int i = 0; i < 45; i++) {
      ItemStack delayItem = new ItemStack(Material.WHITE_WOOL, i + 1);
      if (i + 1 == config.getInt("gameSettings.peaceful.delayToRemove") / 60)
        delayItem.setType(Material.RED_WOOL); 
      ItemMeta delayItemMeta = delayItem.getItemMeta();
      delayItemMeta.setDisplayName("§e" + (i + 1) + "-minutes");
      List<String> delayItemLore = new ArrayList<>();
      delayItemLore.add("§fDelay Information:");
      delayItemLore.add("§f - §7Delay Before Removing: §f§n" + (i + 1) + " minutes");
      delayItemLore.add("§7");
      delayItemLore.add("§f§l<!>§r §fClick to §nSelect§r §f§n" + (i + 1) + " minutes§f Delayed Peace Removal");
      delayItemMeta.setLore(delayItemLore);
      delayItem.setItemMeta(delayItemMeta);
      peaceDelayMenu.setItem(i, delayItem);
    } 
    return peaceDelayMenu;
  }
}
