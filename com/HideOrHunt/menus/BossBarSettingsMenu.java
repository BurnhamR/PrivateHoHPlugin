package com.HideOrHunt.menus;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.ConfigRegistery;
import com.HideOrHunt.config.PlayerRegistery;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
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

public class BossBarSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Boss Bar Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§fDisplay Boss Bar")) {
        if (yamlConfiguration.getBoolean("gameSettings.bossbar.enabled")) {
          yamlConfiguration.set("gameSettings.bossbar.enabled", Boolean.valueOf(false));
          player.sendMessage("§aBoss Bars have been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Boss Bars [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
          if (yamlConfiguration.getBoolean("gameSettings.active")) {
            BossBar header = HideOrHunt.getBossBar();
            for (Player oPlayer : Bukkit.getOnlinePlayers()) {
              for (Player listedPlayer : header.getPlayers()) {
                if (listedPlayer.getUniqueId().toString().equals(oPlayer.getUniqueId().toString()))
                  header.removePlayer(oPlayer); 
              } 
            } 
            ConfigRegistery.updateBossBar(HideOrHunt.getInstance(), false);
          } 
        } else {
          yamlConfiguration.set("gameSettings.bossbar.enabled", Boolean.valueOf(true));
          player.sendMessage("§aBoss Bars have been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Boss Bars [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
          if (yamlConfiguration.getBoolean("gameSettings.active")) {
            BossBar header = HideOrHunt.getBossBar();
            for (Player oPlayer : Bukkit.getOnlinePlayers()) {
              if (PlayerRegistery.getPlayerBossBar(HideOrHunt.getInstance(), oPlayer))
                header.addPlayer(oPlayer); 
            } 
            ConfigRegistery.updateBossBar(HideOrHunt.getInstance(), true);
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryBossBarSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§fDisable for All")) {
        if (yamlConfiguration.getBoolean("gameSettings.bossbar.allowDisableForAll")) {
          yamlConfiguration.set("gameSettings.bossbar.allowDisableForAll", Boolean.valueOf(false));
          player.sendMessage("§aBoss Bar Disabled for All has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Boss Bar Access [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("gameSettings.bossbar.allowDisableForAll", Boolean.valueOf(true));
          player.sendMessage("§aBoss Bar Disabled for All has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Boss Bar Access [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(SpecificGameSettingsMenu.inventoryBossBarSettings((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
}
