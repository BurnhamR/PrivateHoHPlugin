package com.HideOrHunt.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class KitUtilities {
  public static void giveKits(String kitname, Player player) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "kits.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    ConfigurationSection kitconfig = yamlConfiguration.getConfigurationSection(kitname.toLowerCase());
    for (String entry : kitconfig.getKeys(false)) {
      if (yamlConfiguration.getBoolean(String.valueOf(kitname) + "." + entry + ".valid")) {
        ItemStack guiitem = new ItemStack(Material.getMaterial(yamlConfiguration.getString(String.valueOf(kitname) + "." + entry + ".material")), yamlConfiguration.getInt(String.valueOf(kitname) + "." + entry + ".amount"));
        ItemMeta guiitemMeta = guiitem.getItemMeta();
        if (yamlConfiguration.isSet(String.valueOf(kitname) + "." + entry + ".name"))
          guiitemMeta.setDisplayName(yamlConfiguration.getString(String.valueOf(kitname) + "." + entry + ".name")); 
        if (yamlConfiguration.isSet(String.valueOf(kitname) + "." + entry + ".lore")) {
          ConfigurationSection kitItemsLores = yamlConfiguration.getConfigurationSection(String.valueOf(kitname) + "." + entry + ".lore");
          List<String> guiitemLore = new ArrayList<>();
          for (String lore : kitItemsLores.getKeys(false))
            guiitemLore.add(lore); 
          guiitemMeta.setLore(guiitemLore);
        } 
        if (yamlConfiguration.isSet(String.valueOf(kitname) + "." + entry + ".enchants")) {
          ConfigurationSection kitItemsEnchants = yamlConfiguration.getConfigurationSection(String.valueOf(kitname) + "." + entry + ".enchants");
          for (String enchant : kitItemsEnchants.getKeys(false))
            guiitemMeta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(yamlConfiguration.getString(String.valueOf(kitname) + "." + entry + ".enchants." + enchant + ".name"))), yamlConfiguration.getInt(String.valueOf(kitname) + "." + entry + ".enchants." + enchant + ".level"), false); 
        } 
        guiitem.setItemMeta(guiitemMeta);
        PlayerInventory playerInventory = player.getInventory();
        int emptySpace = 0;
        byte b;
        int i;
        ItemStack[] arrayOfItemStack;
        for (i = (arrayOfItemStack = playerInventory.getStorageContents()).length, b = 0; b < i; ) {
          ItemStack item = arrayOfItemStack[b];
          if (item == null || item.getType() == Material.AIR)
            emptySpace++; 
          b++;
        } 
        if (emptySpace <= 0) {
          player.getWorld().dropItem(player.getLocation(), guiitem);
          continue;
        } 
        playerInventory.addItem(new ItemStack[] { guiitem });
      } 
    } 
  }
  
  public static void saveKits(FileConfiguration config, File configFile, Inventory inv, String kitname) {
    int startingValue = 0;
    byte b;
    int i;
    ItemStack[] arrayOfItemStack;
    for (i = (arrayOfItemStack = inv.getContents()).length, b = 0; b < i; ) {
      ItemStack item = arrayOfItemStack[b];
      config.set(String.valueOf(kitname) + "." + startingValue, null);
      try {
        config.save(configFile);
      } catch (IOException e) {
        e.printStackTrace();
      } 
      if (item == null || item.getType() == Material.AIR) {
        config.set(String.valueOf(kitname) + "." + startingValue + ".valid", Boolean.valueOf(false));
        try {
          config.save(configFile);
          startingValue++;
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else {
        config.set(String.valueOf(kitname) + "." + startingValue + ".valid", Boolean.valueOf(true));
        config.set(String.valueOf(kitname) + "." + startingValue + ".material", item.getType().toString());
        config.set(String.valueOf(kitname) + "." + startingValue + ".amount", Integer.valueOf(item.getAmount()));
        if (item.getItemMeta().hasDisplayName())
          config.set(String.valueOf(kitname) + "." + startingValue + ".name", item.getItemMeta().getDisplayName()); 
        if (item.getItemMeta().hasLore()) {
          int startingLore = 0;
          for (String line : item.getItemMeta().getLore()) {
            config.set(String.valueOf(kitname) + "." + startingValue + ".lore." + startingLore, line);
            startingLore++;
          } 
        } 
        if (item.getItemMeta().hasEnchants()) {
          int startingEnchantment = 0;
          Map<Enchantment, Integer> enchantments = item.getItemMeta().getEnchants();
          for (Map.Entry<Enchantment, Integer> mapEntry : enchantments.entrySet()) {
            Enchantment enchant = mapEntry.getKey();
            Integer level = mapEntry.getValue();
            config.set(String.valueOf(kitname) + "." + startingValue + ".enchants." + startingEnchantment + ".name", enchant.getKey().getKey());
            config.set(String.valueOf(kitname) + "." + startingValue + ".enchants." + startingEnchantment + ".level", level);
            startingEnchantment++;
          } 
        } 
        try {
          config.save(configFile);
          startingValue++;
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } 
      b++;
    } 
  }
}
