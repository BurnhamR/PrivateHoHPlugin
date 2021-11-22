package com.HideOrHunt.menus;

import java.io.File;
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

public class ServerSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Server Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§bWeather Settings")) {
        player.openInventory(inventoryWeather((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§bEntry Messages")) {
        player.openInventory(inventoryEntryMessages((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§bDeath Messages")) {
        player.openInventory(inventoryDeathMessages((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
  
  public static Inventory inventoryWeather(FileConfiguration config) {
    Inventory weatherMenu = Bukkit.getServer().createInventory(null, 9, "Weather Settings");
    ItemStack rotate = new ItemStack(Material.BARRIER, 1);
    ItemMeta rotateMeta = rotate.getItemMeta();
    rotateMeta.setDisplayName("§cRotate");
    List<String> rotateLore = new ArrayList<>();
    rotateLore.add("§7Rotates through all possible");
    rotateLore.add("§7weather types when a change happens.");
    rotateLore.add("§7");
    rotateLore.add("§f§l<!>§r §fClick to §nSelect§r §f§nRotating");
    rotateMeta.setLore(rotateLore);
    rotate.setItemMeta(rotateMeta);
    weatherMenu.setItem(0, rotate);
    ItemStack sun = new ItemStack(Material.YELLOW_WOOL, 1);
    ItemMeta sunMeta = sun.getItemMeta();
    sunMeta.setDisplayName("§eSunny");
    List<String> sunLore = new ArrayList<>();
    sunLore.add("§7Constantly sunny.");
    sunLore.add("§7");
    sunLore.add("§f§l<!>§r §fClick to §nSelect§r §f§nSunny");
    sunMeta.setLore(sunLore);
    sun.setItemMeta(sunMeta);
    weatherMenu.setItem(1, sun);
    ItemStack rain = new ItemStack(Material.BLUE_WOOL, 1);
    ItemMeta rainMeta = rain.getItemMeta();
    rainMeta.setDisplayName("§bRainy");
    List<String> rainLore = new ArrayList<>();
    rainLore.add("§7Constantly rainy.");
    rainLore.add("§7");
    rainLore.add("§f§l<!>§r §fClick to §nSelect§r §f§nRainy");
    rainMeta.setLore(rainLore);
    rain.setItemMeta(rainMeta);
    weatherMenu.setItem(2, rain);
    ItemStack storm = new ItemStack(Material.WHITE_WOOL, 1);
    ItemMeta stormMeta = storm.getItemMeta();
    stormMeta.setDisplayName("§fStormy");
    List<String> stormLore = new ArrayList<>();
    stormLore.add("§7Constantly stormy.");
    stormLore.add("§7");
    stormLore.add("§f§l<!>§r §fClick to §nSelect§r §f§nStormy");
    stormMeta.setLore(stormLore);
    storm.setItemMeta(stormMeta);
    weatherMenu.setItem(3, storm);
    return weatherMenu;
  }
  
  public static Inventory inventoryEntryMessages(FileConfiguration config) {
    Inventory messageMenu = Bukkit.getServer().createInventory(null, 9, "Entry Message Settings");
    ItemStack off = new ItemStack(Material.RED_WOOL, 1);
    if (config.getString("serverSettings.entryMessages").equals("off"))
      off.setType(Material.BLACK_WOOL); 
    ItemMeta offMeta = off.getItemMeta();
    offMeta.setDisplayName("§cDisabled");
    List<String> offLore = new ArrayList<>();
    offLore.add("§7Join/Leave messages are disabled.");
    offLore.add("§7");
    offLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nEntry Messages");
    offMeta.setLore(offLore);
    off.setItemMeta(offMeta);
    messageMenu.setItem(0, off);
    ItemStack on = new ItemStack(Material.GREEN_WOOL, 1);
    if (config.getString("serverSettings.entryMessages").equals("on"))
      on.setType(Material.BLACK_WOOL); 
    ItemMeta onMeta = on.getItemMeta();
    onMeta.setDisplayName("§aEnabled");
    List<String> onLore = new ArrayList<>();
    onLore.add("§7Join/Leave messages are enabled.");
    onLore.add("§7");
    onLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nEntry Messages");
    onMeta.setLore(onLore);
    on.setItemMeta(onMeta);
    messageMenu.setItem(1, on);
    ItemStack op = new ItemStack(Material.PINK_WOOL, 1);
    if (config.getString("serverSettings.entryMessages").equals("op"))
      op.setType(Material.BLACK_WOOL); 
    ItemMeta opMeta = op.getItemMeta();
    opMeta.setDisplayName("§dOPs Only");
    List<String> opLore = new ArrayList<>();
    opLore.add("§7Join/Leave messages are enabled for OP-Only.");
    opLore.add("§7");
    opLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nEntry Messages§f for §nOPs");
    opMeta.setLore(opLore);
    op.setItemMeta(opMeta);
    messageMenu.setItem(2, op);
    return messageMenu;
  }
  
  public static Inventory inventoryDeathMessages(FileConfiguration config) {
    Inventory messageMenu = Bukkit.getServer().createInventory(null, 9, "Death Message Settings");
    ItemStack off = new ItemStack(Material.RED_WOOL, 1);
    if (config.getString("serverSettings.deathMessages").equals("off"))
      off.setType(Material.BLACK_WOOL); 
    ItemMeta offMeta = off.getItemMeta();
    offMeta.setDisplayName("§cDisabled");
    List<String> offLore = new ArrayList<>();
    offLore.add("§7Death messages are disabled.");
    offLore.add("§7");
    offLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nDeath Messages");
    offMeta.setLore(offLore);
    off.setItemMeta(offMeta);
    messageMenu.setItem(0, off);
    ItemStack on = new ItemStack(Material.GREEN_WOOL, 1);
    if (config.getString("serverSettings.deathMessages").equals("on"))
      on.setType(Material.BLACK_WOOL); 
    ItemMeta onMeta = on.getItemMeta();
    onMeta.setDisplayName("§aEnabled");
    List<String> onLore = new ArrayList<>();
    onLore.add("§7Death messages are enabled.");
    onLore.add("§7");
    onLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nDeath Messages");
    onMeta.setLore(onLore);
    on.setItemMeta(onMeta);
    messageMenu.setItem(1, on);
    ItemStack op = new ItemStack(Material.PINK_WOOL, 1);
    if (config.getString("serverSettings.deathMessages").equals("op"))
      op.setType(Material.BLACK_WOOL); 
    ItemMeta opMeta = op.getItemMeta();
    opMeta.setDisplayName("§dOPs Only");
    List<String> opLore = new ArrayList<>();
    opLore.add("§7Death messages are enabled for OP-Only.");
    opLore.add("§7");
    opLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nDeath Messages§f for §nOPs");
    opMeta.setLore(opLore);
    op.setItemMeta(opMeta);
    messageMenu.setItem(2, op);
    return messageMenu;
  }
}
