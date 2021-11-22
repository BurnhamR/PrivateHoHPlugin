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

public class DeathSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Death Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§fKeep XP")) {
        if (yamlConfiguration.getBoolean("gameSettings.death.keepXPOnDeath")) {
          yamlConfiguration.set("gameSettings.death.keepXPOnDeath", Boolean.valueOf(false));
          player.sendMessage("§aKeep XP on Death has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating XP on Death [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.death.keepXPOnDeath", Boolean.valueOf(true));
          player.sendMessage("§aKeep XP on Death has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating XP on Death [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryDeathSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fKeep Items")) {
        if (yamlConfiguration.getBoolean("gameSettings.death.keepItemsOnDeath")) {
          yamlConfiguration.set("gameSettings.death.keepItemsOnDeath", Boolean.valueOf(false));
          player.sendMessage("§aKeep Items on Death has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Items on Death [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.death.keepItemsOnDeath", Boolean.valueOf(true));
          player.sendMessage("§aKeep Items on Death has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Items on Death [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryDeathSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fAuto Kick")) {
        if (yamlConfiguration.getBoolean("gameSettings.death.autokick")) {
          yamlConfiguration.set("gameSettings.death.autokick", Boolean.valueOf(false));
          player.sendMessage("§aAuto Kick has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Auto Kick on Death [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.death.autokick", Boolean.valueOf(true));
          player.sendMessage("§aAuto Kick has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Auto Kick on Death [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryDeathSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fAuto Unwhitelist")) {
        if (yamlConfiguration.getBoolean("gameSettings.death.autounwhitelist")) {
          yamlConfiguration.set("gameSettings.death.autounwhitelist", Boolean.valueOf(false));
          player.sendMessage("§aAuto Unwhitelist has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Auto Unwhitelist on Death [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.death.autounwhitelist", Boolean.valueOf(true));
          player.sendMessage("§aAuto Unwhitelist has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Auto Unwhitelist on Death [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryDeathSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fXP Chances")) {
        player.openInventory(inventoryChance((FileConfiguration)yamlConfiguration, "XP"));
      } else if (item.getItemMeta().getDisplayName().equals("§fItem Chances")) {
        player.openInventory(inventoryChance((FileConfiguration)yamlConfiguration, "Item"));
      } 
    } 
  }
  
  public Inventory inventoryChance(FileConfiguration config, String type) {
    Inventory chanceMenu = Bukkit.getServer().createInventory(null, 54, String.valueOf(type) + " Chances");
    for (int i = 0; i < 21; i++) {
      ItemStack chanceItem = new ItemStack(Material.WHITE_WOOL, i + 1);
      if (type.equals("XP")) {
        if (i * 5 == (int)(config.getDouble("gameSettings.death.chanceXPSave") * 100.0D))
          chanceItem.setType(Material.RED_WOOL); 
      } else if (type.equals("Item") && 
        i * 5 == (int)(config.getDouble("gameSettings.death.chanceItemSave") * 100.0D)) {
        chanceItem.setType(Material.RED_WOOL);
      } 
      ItemMeta chanceItemMeta = chanceItem.getItemMeta();
      chanceItemMeta.setDisplayName("§eChance: " + (i * 5) + "%");
      List<String> chanceItemLore = new ArrayList<>();
      chanceItemLore.add("§fChance Information:");
      chanceItemLore.add("§f - §7Chance: §f§n" + (i * 5) + "%");
      chanceItemLore.add("§7");
      chanceItemLore.add("§f§l<!>§r §fClick to §nSelect§r §f§n" + (i * 5) + "%");
      chanceItemMeta.setLore(chanceItemLore);
      chanceItem.setItemMeta(chanceItemMeta);
      chanceMenu.setItem(i, chanceItem);
    } 
    return chanceMenu;
  }
}
