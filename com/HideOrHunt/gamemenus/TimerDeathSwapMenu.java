package com.HideOrHunt.gamemenus;

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

public class TimerDeathSwapMenu implements Listener, InventoryHolder {
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
    File configFile = new File("plugins/PrivateHideOrHunt/", "custom.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (menu.getTitle().equals("Timer Death Swap Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§fTimer Death Swap Status")) {
        if (yamlConfiguration.getBoolean("timerdeathswap.enabled")) {
          yamlConfiguration.set("timerdeathswap.enabled", Boolean.valueOf(false));
          player.sendMessage("§aTimer Death Swap has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Timer Death Swap Status [CustomGamemodes].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("timerdeathswap.enabled", Boolean.valueOf(true));
          player.sendMessage("§aTimer Death Swap has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Timer Death Swap Status [CustomGamemodes].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(CustomGamemodeMenu.inventoryTimerDeathSwapSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fTimer Peace Swap Status")) {
        if (yamlConfiguration.getBoolean("timerdeathswap.swapDuringPeace")) {
          yamlConfiguration.set("timerdeathswap.swapDuringPeace", Boolean.valueOf(false));
          player.sendMessage("§aTimer Peace Swap has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Timer Peace Swap Status [CustomGamemodes].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("timerdeathswap.swapDuringPeace", Boolean.valueOf(true));
          player.sendMessage("§aTimer Peace Swap has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Timer Peace Swap Status [CustomGamemodes].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(CustomGamemodeMenu.inventoryTimerDeathSwapSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fPlayer Swap Notify")) {
        if (yamlConfiguration.getBoolean("timerdeathswap.notifyOfSwapper")) {
          yamlConfiguration.set("timerdeathswap.notifyOfSwapper", Boolean.valueOf(false));
          player.sendMessage("§aPlayer Swap Notify has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Player Swap Notify [CustomGamemodes].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("timerdeathswap.notifyOfSwapper", Boolean.valueOf(true));
          player.sendMessage("§aPlayer Swap Notify has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Player Swap Notify [CustomGamemodes].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(CustomGamemodeMenu.inventoryTimerDeathSwapSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fTime Between Swaps")) {
        player.openInventory(inventoryTimeBetweenSwapsDuration((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fFixed Swap Time")) {
        if (yamlConfiguration.getBoolean("timerdeathswap.fixedSwapTime")) {
          yamlConfiguration.set("timerdeathswap.fixedSwapTime", Boolean.valueOf(false));
          player.sendMessage("§aFixed Swap Time has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Swap Type [CustomGamemodes].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("timerdeathswap.fixedSwapTime", Boolean.valueOf(true));
          player.sendMessage("§aFixed Swap Time has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Swap Type [CustomGamemodes].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(CustomGamemodeMenu.inventoryTimerDeathSwapSettings((FileConfiguration)yamlConfiguration));
      } 
    } else if (menu.getTitle().equals("Timer Death Swap Duration")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      yamlConfiguration.set("timerdeathswap.timeBetweenSwaps", Integer.valueOf(item.getAmount() * 60));
      player.sendMessage("§aThe Time Between Death Swaps was updated to §n" + item.getAmount() + "§a minutes.");
      try {
        yamlConfiguration.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Updating Time Between Swaps [CustomGamemodes].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      player.closeInventory();
    } 
  }
  
  public Inventory inventoryTimeBetweenSwapsDuration(FileConfiguration config) {
    Inventory invisibilityDurationMenu = Bukkit.getServer().createInventory(null, 54, "Timer Death Swap Duration");
    for (int i = 0; i < 45; i++) {
      ItemStack durationItem = new ItemStack(Material.WHITE_WOOL, i + 1);
      if (i + 1 == config.getInt("timerdeathswap.timeBetweenSwaps") / 60)
        durationItem.setType(Material.RED_WOOL); 
      ItemMeta durationItemMeta = durationItem.getItemMeta();
      durationItemMeta.setDisplayName("§e" + (i + 1) + "-minutes");
      List<String> durationItemLore = new ArrayList<>();
      durationItemLore.add("§fDuration Information:");
      durationItemLore.add("§f - §7Duration: §f§n" + (i + 1) + " minutes");
      durationItemLore.add("§7");
      durationItemLore.add("§f§l<!>§r §fClick to §nSelect§r §f§n" + (i + 1) + " minutes§f Swap Times");
      durationItemMeta.setLore(durationItemLore);
      durationItem.setItemMeta(durationItemMeta);
      invisibilityDurationMenu.setItem(i, durationItem);
    } 
    return invisibilityDurationMenu;
  }
}
