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

public class WeatherSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Weather Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§cRotate")) {
        yamlConfiguration.set("serverSettings.weather", "rotate");
        player.sendMessage("§aWeather set to §nRotate§a.");
        try {
          yamlConfiguration.save(configFile);
          Bukkit.getLogger().info("Hide or Hunt > Updating the Weather to Rotate [ServerSettings]");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else if (item.getItemMeta().getDisplayName().equals("§eSunny")) {
        yamlConfiguration.set("serverSettings.weather", "sun");
        player.sendMessage("§aWeather set to §nSunny§a.");
        try {
          yamlConfiguration.save(configFile);
          if (player.getWorld().isThundering())
            player.getWorld().setThundering(false); 
          if (player.getWorld().hasStorm())
            player.getWorld().setStorm(false); 
          Bukkit.getLogger().info("Hide or Hunt > Updating the Weather to Sunny [ServerSettings]");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else if (item.getItemMeta().getDisplayName().equals("§bRainy")) {
        yamlConfiguration.set("serverSettings.weather", "rain");
        player.sendMessage("§aWeather set to §nRainy§a.");
        try {
          yamlConfiguration.save(configFile);
          if (player.getWorld().isThundering())
            player.getWorld().setThundering(false); 
          if (!player.getWorld().hasStorm())
            player.getWorld().setStorm(true); 
          Bukkit.getLogger().info("Hide or Hunt > Updating the Weather to Rainy [ServerSettings]");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else if (item.getItemMeta().getDisplayName().equals("§fStormy")) {
        yamlConfiguration.set("serverSettings.weather", "storm");
        player.sendMessage("§aWeather set to §nStormy§a.");
        try {
          yamlConfiguration.save(configFile);
          if (player.getWorld().hasStorm()) {
            if (!player.getWorld().isThundering())
              player.getWorld().setThundering(true); 
          } else {
            player.getWorld().setStorm(true);
            player.getWorld().setThundering(true);
          } 
          Bukkit.getLogger().info("Hide or Hunt > Updating the Weather to Stormy [ServerSettings]");
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } 
      player.closeInventory();
    } 
  }
}
