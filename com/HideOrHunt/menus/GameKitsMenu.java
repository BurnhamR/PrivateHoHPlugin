package com.HideOrHunt.menus;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.ConfigRegistery;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
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

public class GameKitsMenu implements Listener, InventoryHolder {
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
    File configFile = new File("plugins/PrivateHideOrHunt/", "kits.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (menu.getTitle().equals("Game Kits")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§dStarting Kit §8[§7HoH§8]")) {
        player.openInventory(inventoryStartingHoHKit((FileConfiguration)yamlConfiguration));
      } else if (item.getItemMeta().getDisplayName().equals("§dDeath Kit §8[§7HoH§8]")) {
        player.openInventory(inventoryDeathHoHKit((FileConfiguration)yamlConfiguration));
      } 
    } 
  }
  
  public static Inventory inventoryStartingHoHKit(FileConfiguration config) {
    Inventory kitMenu = Bukkit.getServer().createInventory(null, 36, "Kit-StartingHoH");
    if (!config.isSet("startinghoh.0.valid")) {
      ConfigRegistery.loadGameKits(HideOrHunt.getInstance(), "kits");
      return kitMenu;
    } 
    return plotInventory(config, kitMenu, "startinghoh");
  }
  
  public static Inventory inventoryDeathHoHKit(FileConfiguration config) {
    Inventory kitMenu = Bukkit.getServer().createInventory(null, 36, "Kit-DeathHoH");
    if (!config.isSet("deathhoh.0.valid")) {
      ConfigRegistery.loadGameKits(HideOrHunt.getInstance(), "kits");
      return kitMenu;
    } 
    return plotInventory(config, kitMenu, "deathhoh");
  }
  
  public static Inventory plotInventory(FileConfiguration config, Inventory invToPlot, String kitname) {
    Inventory kitToPlot = invToPlot;
    ConfigurationSection kitItems = config.getConfigurationSection(kitname);
    for (String item : kitItems.getKeys(false)) {
      if (config.getBoolean(String.valueOf(kitname) + "." + item + ".valid")) {
        ItemStack guiitem = new ItemStack(Material.getMaterial(config.getString(String.valueOf(kitname) + "." + item + ".material")), config.getInt(String.valueOf(kitname) + "." + item + ".amount"));
        ItemMeta guiitemMeta = guiitem.getItemMeta();
        if (config.isSet(String.valueOf(kitname) + "." + item + ".name"))
          guiitemMeta.setDisplayName(config.getString(String.valueOf(kitname) + "." + item + ".name")); 
        if (config.isSet(String.valueOf(kitname) + "." + item + ".lore")) {
          ConfigurationSection kitItemsLores = config.getConfigurationSection(String.valueOf(kitname) + "." + item + ".lore");
          List<String> guiitemLore = new ArrayList<>();
          for (String lore : kitItemsLores.getKeys(false))
            guiitemLore.add(lore); 
          guiitemMeta.setLore(guiitemLore);
        } 
        if (config.isSet(String.valueOf(kitname) + "." + item + ".enchants")) {
          ConfigurationSection kitItemsEnchants = config.getConfigurationSection(String.valueOf(kitname) + "." + item + ".enchants");
          for (String enchant : kitItemsEnchants.getKeys(false))
            guiitemMeta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(config.getString(String.valueOf(kitname) + "." + item + ".enchants." + enchant + ".name"))), config.getInt(String.valueOf(kitname) + "." + item + ".enchants." + enchant + ".level"), false); 
        } 
        guiitem.setItemMeta(guiitemMeta);
        kitToPlot.setItem(Integer.parseInt(item), guiitem);
      } 
    } 
    return kitToPlot;
  }
}
