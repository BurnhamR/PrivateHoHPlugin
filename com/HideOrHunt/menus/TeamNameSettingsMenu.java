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

public class TeamNameSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Team Name Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§eNumbers")) {
        if (yamlConfiguration.getBoolean("teamsSettings.canTeamNameContainNumber")) {
          yamlConfiguration.set("teamsSettings.canTeamNameContainNumber", Boolean.valueOf(false));
          player.sendMessage("§aNumbers inside of Team Names has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Team Name Numbers [TeamSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("teamsSettings.canTeamNameContainNumber", Boolean.valueOf(true));
          player.sendMessage("§aNumbers inside of Team Names has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Team Name Numbers [TeamSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(GameSettingsMenu.inventoryTeamNameSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§eRandom Characters")) {
        if (yamlConfiguration.getBoolean("teamsSettings.canTeamNameContainRandomCharacters")) {
          yamlConfiguration.set("teamsSettings.canTeamNameContainRandomCharacters", Boolean.valueOf(false));
          player.sendMessage("§aRandom Characters inside of Team Names has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Team Name Random Characters [TeamSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("teamsSettings.canTeamNameContainRandomCharacters", Boolean.valueOf(true));
          player.sendMessage("§aRandom Characters inside of Team Names has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Team Name Random Characters [TeamSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(GameSettingsMenu.inventoryTeamNameSettings((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§eMaximum Name Characters")) {
        player.openInventory(inventoryTeamNameMaximumCharacters((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§eMinimum Name Characters")) {
        player.openInventory(inventoryTeamNameMinimumCharacters((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
  
  public Inventory inventoryTeamNameMaximumCharacters(FileConfiguration config) {
    Inventory teamNameCharacterMenu = Bukkit.getServer().createInventory(null, 54, "Maximum Team Name Characters");
    for (int i = 0; i < 50; i++) {
      ItemStack teamNameCharacter = new ItemStack(Material.WHITE_WOOL, i + 3);
      if (i + 3 == config.getInt("teamsSettings.maxTeamNameCharacters"))
        teamNameCharacter.setType(Material.RED_WOOL); 
      ItemMeta teamNameCharacterMeta = teamNameCharacter.getItemMeta();
      teamNameCharacterMeta.setDisplayName("§eMaximum Characters: " + (i + 3));
      List<String> teamNameCharacterLore = new ArrayList<>();
      teamNameCharacterLore.add("§fName Information:");
      teamNameCharacterLore.add("§f - §7Maximum Characters: §f§n" + (i + 3));
      teamNameCharacterLore.add("§7");
      teamNameCharacterLore.add("§f§l<!>§r §fClick to §nSelect§r §f§n" + (i + 3) + "§f Maximum Name Characters");
      teamNameCharacterMeta.setLore(teamNameCharacterLore);
      teamNameCharacter.setItemMeta(teamNameCharacterMeta);
      teamNameCharacterMenu.setItem(i, teamNameCharacter);
    } 
    return teamNameCharacterMenu;
  }
  
  public Inventory inventoryTeamNameMinimumCharacters(FileConfiguration config) {
    Inventory teamNameCharacterMenu = Bukkit.getServer().createInventory(null, 54, "Minimum Team Name Characters");
    for (int i = 0; i < 50; i++) {
      ItemStack teamNameCharacter = new ItemStack(Material.WHITE_WOOL, i + 3);
      if (i + 3 == config.getInt("teamsSettings.minTeamNameCharacters"))
        teamNameCharacter.setType(Material.RED_WOOL); 
      ItemMeta teamNameCharacterMeta = teamNameCharacter.getItemMeta();
      teamNameCharacterMeta.setDisplayName("§eMinimum Characters: " + (i + 3));
      List<String> teamNameCharacterLore = new ArrayList<>();
      teamNameCharacterLore.add("§fName Information:");
      teamNameCharacterLore.add("§f - §7Minimum Characters: §f§n" + (i + 3));
      teamNameCharacterLore.add("§7");
      teamNameCharacterLore.add("§f§l<!>§r §fClick to §nSelect§r §f§n" + (i + 3) + "§f Minimum Name Characters");
      teamNameCharacterMeta.setLore(teamNameCharacterLore);
      teamNameCharacter.setItemMeta(teamNameCharacterMeta);
      teamNameCharacterMenu.setItem(i, teamNameCharacter);
    } 
    return teamNameCharacterMenu;
  }
}
