package com.HideOrHunt.utilities;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InvisUtilities {
  public static void invisPotion(FileConfiguration config) {
    if (config.getString("gameSettings.invis.given").equals("potion")) {
      for (Player oPlayer : Bukkit.getOnlinePlayers()) {
        if (PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), oPlayer)) {
          ItemStack invisPotion = new ItemStack(Material.POTION);
          PotionMeta invisPotionMeta = (PotionMeta)invisPotion.getItemMeta();
          invisPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, config.getInt("gameSettings.invis.duration") * 20, 0), true);
          invisPotionMeta.setDisplayName("§aInvisibilty Potion §8[§7" + (config.getInt("gameSettings.invis.duration") / 60) + " minutes§8]");
          invisPotionMeta.setColor(Color.FUCHSIA);
          invisPotion.setItemMeta((ItemMeta)invisPotionMeta);
          PlayerInventory playerInventory = oPlayer.getInventory();
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
            oPlayer.getWorld().dropItem(oPlayer.getLocation(), invisPotion);
            oPlayer.sendMessage("§aYour invisibility potion for §n" + (config.getInt("gameSettings.invis.duration") / 60) + " minutes§a was dropped on the floor!");
            continue;
          } 
          playerInventory.addItem(new ItemStack[] { invisPotion });
          oPlayer.sendMessage("§aYou were given an invisibility potion for §n" + (config.getInt("gameSettings.invis.duration") / 60) + " minutes§a!");
        } 
      } 
    } else if (config.getString("gameSettings.invis.given").equals("auto")) {
      int duration = config.getInt("gameSettings.invis.duration") * 20;
      for (Player oPlayer : Bukkit.getOnlinePlayers()) {
        if (PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), oPlayer)) {
          oPlayer.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, duration, 0));
          oPlayer.sendMessage("§aYou were given invisibility for §n" + (config.getInt("gameSettings.invis.duration") / 60) + " minutes§a!");
        } 
      } 
    } 
  }
}
