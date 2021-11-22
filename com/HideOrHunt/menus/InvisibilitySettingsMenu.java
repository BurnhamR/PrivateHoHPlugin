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

public class InvisibilitySettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Invisibility Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§fInvisibility Status")) {
        if (yamlConfiguration.getBoolean("gameSettings.invis.enabled")) {
          yamlConfiguration.set("gameSettings.invis.enabled", Boolean.valueOf(false));
          player.sendMessage("§aInvisibility has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Invisibility Status [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.invis.enabled", Boolean.valueOf(true));
          player.sendMessage("§aInvisibility has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Invisibility Status [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryInvisibilitySettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fInvisibility Type")) {
        if (yamlConfiguration.getString("gameSettings.invis.given").equals("potion")) {
          yamlConfiguration.set("gameSettings.invis.given", "auto");
          player.sendMessage("§aInvisibility type has been changed to §a§nAutomatic Giving§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Invisibility Status [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else if (yamlConfiguration.getString("gameSettings.invis.given").equals("auto")) {
          yamlConfiguration.set("gameSettings.invis.given", "potion");
          player.sendMessage("§aInvisibility type has been changed to §a§nPotion Giving§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Invisibility Status [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryInvisibilitySettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fInvisibility Duration")) {
        player.openInventory(inventoryInvisibilityDuration((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fInvisibility Delay")) {
        player.openInventory(inventoryInvisibilityDelay((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fInvisibility Removal on Damage")) {
        if (yamlConfiguration.getBoolean("gameSettings.invis.removeOnDamageDone")) {
          yamlConfiguration.set("gameSettings.invis.removeOnDamageDone", Boolean.valueOf(false));
          player.sendMessage("§aInvisibility Removal on Damage has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Invisibility Removal on Damage [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.invis.removeOnDamageDone", Boolean.valueOf(true));
          player.sendMessage("§aInvisibility Removal on Damage has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Invisibility Removal on Damage [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryInvisibilitySettings((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
  
  public Inventory inventoryInvisibilityDuration(FileConfiguration config) {
    Inventory invisibilityDurationMenu = Bukkit.getServer().createInventory(null, 54, "Invisibility Duration");
    for (int i = 0; i < 45; i++) {
      ItemStack durationItem = new ItemStack(Material.WHITE_WOOL, i + 1);
      if (i + 1 == config.getInt("gameSettings.invis.duration") / 60)
        durationItem.setType(Material.RED_WOOL); 
      ItemMeta durationItemMeta = durationItem.getItemMeta();
      durationItemMeta.setDisplayName("§e" + (i + 1) + "-minutes");
      List<String> durationItemLore = new ArrayList<>();
      durationItemLore.add("§fDuration Information:");
      durationItemLore.add("§f - §7Duration: §f§n" + (i + 1) + " minutes");
      durationItemLore.add("§7");
      durationItemLore.add("§f§l<!>§r §fClick to §nSelect§r §f§n" + (i + 1) + " minutes§f Invisibility");
      durationItemMeta.setLore(durationItemLore);
      durationItem.setItemMeta(durationItemMeta);
      invisibilityDurationMenu.setItem(i, durationItem);
    } 
    return invisibilityDurationMenu;
  }
  
  public Inventory inventoryInvisibilityDelay(FileConfiguration config) {
    Inventory invisibilityDelayMenu = Bukkit.getServer().createInventory(null, 54, "Invisibility Delay");
    for (int i = 0; i < 45; i++) {
      ItemStack delayItem = new ItemStack(Material.WHITE_WOOL, i + 1);
      if (i + 1 == config.getInt("gameSettings.invis.timeBeforeGive") / 60)
        delayItem.setType(Material.RED_WOOL); 
      ItemMeta delayItemMeta = delayItem.getItemMeta();
      delayItemMeta.setDisplayName("§e" + (i + 1) + "-minutes");
      List<String> delayItemLore = new ArrayList<>();
      delayItemLore.add("§fDelay Information:");
      delayItemLore.add("§f - §7Delay Before Giving: §f§n" + (i + 1) + " minutes");
      delayItemLore.add("§7");
      delayItemLore.add("§f§l<!>§r §fClick to §nSelect§r §f§n" + (i + 1) + " minutes§f Delayed Invisibility");
      delayItemMeta.setLore(delayItemLore);
      delayItem.setItemMeta(delayItemMeta);
      invisibilityDelayMenu.setItem(i, delayItem);
    } 
    return invisibilityDelayMenu;
  }
}
