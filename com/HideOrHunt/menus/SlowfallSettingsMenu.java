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

public class SlowfallSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Slowfall Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§fSlowfall Status")) {
        if (yamlConfiguration.getBoolean("gameSettings.slowfall.enabled")) {
          yamlConfiguration.set("gameSettings.slowfall.enabled", Boolean.valueOf(false));
          player.sendMessage("§aSlowfall has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Slowfall Status [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.slowfall.enabled", Boolean.valueOf(true));
          player.sendMessage("§aSlowfall has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Slowfall Status [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventorySlowfallSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fSlowfall Removal on Contact")) {
        if (yamlConfiguration.getBoolean("gameSettings.slowfall.removeOnContact")) {
          yamlConfiguration.set("gameSettings.slowfall.removeOnContact", Boolean.valueOf(false));
          player.sendMessage("§aSlowfall Removal on Contact has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Slowfall Removal on Contact [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.slowfall.removeOnContact", Boolean.valueOf(true));
          player.sendMessage("§aSlowfall Removal on Contact has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Slowfall Removal on Contact [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventorySlowfallSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fSlowfall Duration")) {
        player.openInventory(inventorySlowfallDuration((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
  
  public Inventory inventorySlowfallDuration(FileConfiguration config) {
    Inventory slowfallDurationMenu = Bukkit.getServer().createInventory(null, 54, "Slowfall Duration");
    int currentDuration = config.getInt("gameSettings.slowfall.duration");
    slowfallDurationMenu.setItem(0, slowfallDurationItem(15, 15, currentDuration));
    slowfallDurationMenu.setItem(1, slowfallDurationItem(30, 30, currentDuration));
    slowfallDurationMenu.setItem(2, slowfallDurationItem(45, 45, currentDuration));
    slowfallDurationMenu.setItem(3, slowfallDurationItem(60, 60, currentDuration));
    return slowfallDurationMenu;
  }
  
  public ItemStack slowfallDurationItem(int itemAmount, int itemDuration, int currentDuration) {
    ItemStack duration = new ItemStack(Material.WHITE_WOOL, itemAmount);
    if (itemDuration == currentDuration)
      duration.setType(Material.RED_WOOL); 
    ItemMeta durationMeta = duration.getItemMeta();
    durationMeta.setDisplayName("§e" + itemDuration + "-seconds");
    List<String> durationLore = new ArrayList<>();
    durationLore.add("§fDuration Information:");
    durationLore.add("§f - §7Duration: §f§n" + itemDuration + " seconds");
    durationLore.add("§7");
    durationLore.add("§f§l<!>§r §fClick to §nSelect§r §f§n" + itemDuration + " seconds§f Slowfall");
    durationMeta.setLore(durationLore);
    duration.setItemMeta(durationMeta);
    return duration;
  }
}
