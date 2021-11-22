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

public class MobBreedingSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().contains("Mob Breeds")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      NamespacedKey mobbreedTypeConfig = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobbreedtypeconfig");
      NamespacedKey mobbreedType = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "mobbreedtype");
      PersistentDataContainer itemTags = item.getItemMeta().getPersistentDataContainer();
      if (itemTags.has(mobbreedTypeConfig, PersistentDataType.STRING)) {
        if (yamlConfiguration.getBoolean("mobSettings.mobBreed." + (String)itemTags.get(mobbreedTypeConfig, PersistentDataType.STRING))) {
          yamlConfiguration.set("mobSettings.mobBreed." + (String)itemTags.get(mobbreedTypeConfig, PersistentDataType.STRING), Boolean.valueOf(false));
          player.sendMessage("§aMob Breeding for §n" + (String)itemTags.get(mobbreedType, PersistentDataType.STRING) + "§a has been §c§nDisabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating the " + (String)itemTags.get(mobbreedType, PersistentDataType.STRING) + " Breedable. [MobBreedingSettings]");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration.set("mobSettings.mobBreed." + (String)itemTags.get(mobbreedTypeConfig, PersistentDataType.STRING), Boolean.valueOf(true));
          player.sendMessage("§aMob Breeding for §n" + (String)itemTags.get(mobbreedType, PersistentDataType.STRING) + "§a has been §a§nEnabled§a.");
          try {
            yamlConfiguration.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating the " + (String)itemTags.get(mobbreedType, PersistentDataType.STRING) + " Breedable. [MobBreedingSettings]");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(MobSettingsMenu.inventoryMobBreed((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
}
