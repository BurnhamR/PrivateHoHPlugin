package com.HideOrHunt.utilities;

import java.io.File;
import java.io.IOException;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ExplodeUtilities {
  public static void saveBlacklistedBlocks(FileConfiguration config, File configFile, Inventory inv) {
    int startingValue = 0;
    byte b;
    int i;
    ItemStack[] arrayOfItemStack;
    for (i = (arrayOfItemStack = inv.getContents()).length, b = 0; b < i; ) {
      ItemStack item = arrayOfItemStack[b];
      config.set("blacklist." + startingValue, null);
      try {
        config.save(configFile);
      } catch (IOException e) {
        e.printStackTrace();
      } 
      if (item == null || item.getType() == Material.AIR) {
        config.set("blacklist." + startingValue + ".valid", Boolean.valueOf(false));
        try {
          config.save(configFile);
          startingValue++;
        } catch (IOException e) {
          e.printStackTrace();
        } 
      } else {
        config.set("blacklist." + startingValue + ".valid", Boolean.valueOf(true));
        config.set("blacklist." + startingValue + ".material", item.getType().toString());
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
