package com.HideOrHunt.menus;

import com.HideOrHunt.HideOrHunt;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
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
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class MobSpawnSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().contains("Mob Spawns")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().contains("Page")) {
        if (menu.getTitle().contains("#1")) {
          if (item.getItemMeta().getDisplayName().equals("§cLast Page §8[§7Click§8]")) {
            player.sendMessage("§cThis is the §nLast Page§c.");
          } else if (item.getItemMeta().getDisplayName().equals("§aNext Page §8[§7Click§8]")) {
            player.closeInventory();
            player.openInventory(MobSettingsMenu.inventoryMobSpawnPage2((FileConfiguration)yamlConfiguration));
          } 
        } else if (menu.getTitle().contains("#2")) {
          if (item.getItemMeta().getDisplayName().equals("§cLast Page §8[§7Click§8]")) {
            player.closeInventory();
            player.openInventory(MobSettingsMenu.inventoryMobSpawnPage1((FileConfiguration)yamlConfiguration));
          } else if (item.getItemMeta().getDisplayName().equals("§aNext Page §8[§7Click§8]")) {
            player.sendMessage("§cThis is the §nLast Page§c.");
          } 
        } 
      } else {
        NamespacedKey mobspawnTypeConfig = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobspawntypeconfig");
        NamespacedKey mobspawnType = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobspawntype");
        PersistentDataContainer itemTags = item.getItemMeta().getPersistentDataContainer();
        if (itemTags.has(mobspawnTypeConfig, PersistentDataType.STRING)) {
          if (yamlConfiguration.getBoolean("mobSettings.mobSpawn." + (String)itemTags.get(mobspawnTypeConfig, PersistentDataType.STRING))) {
            yamlConfiguration.set("mobSettings.mobSpawn." + (String)itemTags.get(mobspawnTypeConfig, PersistentDataType.STRING), Boolean.valueOf(false));
            player.sendMessage("§aMob Spawn for §n" + (String)itemTags.get(mobspawnType, PersistentDataType.STRING) + "§a has been §c§nDisabled§a.");
            try {
              yamlConfiguration.save(configFile);
              Bukkit.getLogger().info("Hide or Hunt > Updating the " + (String)itemTags.get(mobspawnType, PersistentDataType.STRING) + " Spawn. [MobSpawningSettings]");
            } catch (IOException e) {
              e.printStackTrace();
            } 
          } else {
            yamlConfiguration.set("mobSettings.mobSpawn." + (String)itemTags.get(mobspawnTypeConfig, PersistentDataType.STRING), Boolean.valueOf(true));
            player.sendMessage("§aMob Spawn for §n" + (String)itemTags.get(mobspawnType, PersistentDataType.STRING) + "§a has been §a§nEnabled§a.");
            try {
              yamlConfiguration.save(configFile);
              Bukkit.getLogger().info("Hide or Hunt > Updating the " + (String)itemTags.get(mobspawnType, PersistentDataType.STRING) + " Spawn. [MobSpawningSettings]");
            } catch (IOException e) {
              e.printStackTrace();
            } 
          } 
          player.closeInventory();
          if (menu.getTitle().contains("#1")) {
            player.openInventory(MobSettingsMenu.inventoryMobSpawnPage1((FileConfiguration)yamlConfiguration));
          } else if (menu.getTitle().contains("#2")) {
            player.openInventory(MobSettingsMenu.inventoryMobSpawnPage2((FileConfiguration)yamlConfiguration));
          } 
        } 
      } 
    } 
  }
}
