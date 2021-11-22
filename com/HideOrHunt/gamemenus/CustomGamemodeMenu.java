package com.HideOrHunt.gamemenus;

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

public class CustomGamemodeMenu implements Listener, InventoryHolder {
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
    YamlConfiguration yamlConfiguration1 = YamlConfiguration.loadConfiguration(configFile);
    File customConfigFile = new File("plugins/PrivateHideOrHunt/", "custom.yml");
    YamlConfiguration yamlConfiguration2 = YamlConfiguration.loadConfiguration(customConfigFile);
    if (menu.getTitle().equals("Custom Gamemodes")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§fTimer Death Swap"))
        player.openInventory(inventoryTimerDeathSwapSettings((FileConfiguration)yamlConfiguration2)); 
    } 
  }
  
  public static Inventory inventoryTimerDeathSwapSettings(FileConfiguration config) {
    Inventory timerDeathSwapMenu = Bukkit.getServer().createInventory(null, 9, "Timer Death Swap Settings");
    ItemStack timerDeathSwap = new ItemStack(Material.SKELETON_SKULL, 1);
    ItemMeta timerDeathSwapMeta = timerDeathSwap.getItemMeta();
    timerDeathSwapMeta.setDisplayName("§fTimer Death Swap Status");
    List<String> timerDeathSwapLore = new ArrayList<>();
    timerDeathSwapLore.add("§7Players will Teleport to another");
    timerDeathSwapLore.add("§7Player every x amount of Minutes.");
    timerDeathSwapLore.add("§7");
    timerDeathSwapLore.add("§7Does not include Peace Mode.");
    timerDeathSwapLore.add("§7");
    timerDeathSwapLore.add("§fGamemode Information:");
    if (config.getBoolean("timerdeathswap.enabled")) {
      timerDeathSwapLore.add("§f - §7Enabled: §a§nYes");
      timerDeathSwapLore.add("§7");
      timerDeathSwapLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nTimer Death Swap");
    } else {
      timerDeathSwapLore.add("§f - §7Enabled: §c§nNo");
      timerDeathSwapLore.add("§7");
      timerDeathSwapLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nTimer Death Swap");
    } 
    timerDeathSwapMeta.setLore(timerDeathSwapLore);
    timerDeathSwap.setItemMeta(timerDeathSwapMeta);
    timerDeathSwapMenu.setItem(0, timerDeathSwap);
    if (config.getBoolean("timerdeathswap.enabled")) {
      ItemStack timerPeaceSwap = new ItemStack(Material.ELYTRA, 1);
      ItemMeta timerPeaceSwapMeta = timerPeaceSwap.getItemMeta();
      timerPeaceSwapMeta.setDisplayName("§fTimer Peace Swap Status");
      List<String> timerPeaceSwapLore = new ArrayList<>();
      timerPeaceSwapLore.add("§7Players will Teleport to another");
      timerPeaceSwapLore.add("§7Player every x amount of Minutes.");
      timerPeaceSwapLore.add("§7");
      timerPeaceSwapLore.add("§7Does include Peace Mode.");
      timerPeaceSwapLore.add("§7");
      timerPeaceSwapLore.add("§fGamemode Information:");
      if (config.getBoolean("timerdeathswap.swapDuringPeace")) {
        timerPeaceSwapLore.add("§f - §7Enabled: §a§nYes");
        timerPeaceSwapLore.add("§7");
        timerPeaceSwapLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nTimer Peace Swap");
      } else {
        timerPeaceSwapLore.add("§f - §7Enabled: §c§nNo");
        timerPeaceSwapLore.add("§7");
        timerPeaceSwapLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nTimer Peace Swap");
      } 
      timerPeaceSwapMeta.setLore(timerPeaceSwapLore);
      timerPeaceSwap.setItemMeta(timerPeaceSwapMeta);
      timerDeathSwapMenu.setItem(1, timerPeaceSwap);
      ItemStack playerSwapNotify = new ItemStack(Material.BOOK, 1);
      ItemMeta playerSwapNotifyMeta = playerSwapNotify.getItemMeta();
      playerSwapNotifyMeta.setDisplayName("§fPlayer Swap Notify");
      List<String> playerSwapNotifyLore = new ArrayList<>();
      playerSwapNotifyLore.add("§7Sets whether the players are");
      playerSwapNotifyLore.add("§7told who they got swapped with.");
      playerSwapNotifyLore.add("§7");
      playerSwapNotifyLore.add("§fGamemode Information:");
      if (config.getBoolean("timerdeathswap.notifyOfSwapper")) {
        playerSwapNotifyLore.add("§f - §7Enabled: §a§nYes");
        playerSwapNotifyLore.add("§7");
        playerSwapNotifyLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nSwap Notify");
      } else {
        playerSwapNotifyLore.add("§f - §7Enabled: §c§nNo");
        playerSwapNotifyLore.add("§7");
        playerSwapNotifyLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nSwap Notify");
      } 
      playerSwapNotifyMeta.setLore(playerSwapNotifyLore);
      playerSwapNotify.setItemMeta(playerSwapNotifyMeta);
      timerDeathSwapMenu.setItem(2, playerSwapNotify);
      ItemStack fixedSwapTime = new ItemStack(Material.REDSTONE_BLOCK, 1);
      ItemMeta fixedSwapTimeMeta = fixedSwapTime.getItemMeta();
      fixedSwapTimeMeta.setDisplayName("§fFixed Swap Time");
      List<String> fixedSwapTimeLore = new ArrayList<>();
      fixedSwapTimeLore.add("§7Sets whether the timer is swapped");
      fixedSwapTimeLore.add("§7at a certain time or is random.");
      fixedSwapTimeLore.add("§7");
      fixedSwapTimeLore.add("§fGamemode Information:");
      if (config.getBoolean("timerdeathswap.fixedSwapTime")) {
        fixedSwapTimeLore.add("§f - §7Death Swap is: §a§nFixed Swap Time");
        fixedSwapTimeLore.add("§7");
        fixedSwapTimeLore.add("§f§l<!>§r §fClick to §nChange§r §f§nSwap Type");
      } else {
        fixedSwapTimeLore.add("§f - §7Death Swap is: §c§nRandom");
        fixedSwapTimeLore.add("§7");
        fixedSwapTimeLore.add("§f§l<!>§r §fClick to §nChange§r §f§nSwap Type");
      } 
      fixedSwapTimeMeta.setLore(fixedSwapTimeLore);
      fixedSwapTime.setItemMeta(fixedSwapTimeMeta);
      timerDeathSwapMenu.setItem(3, fixedSwapTime);
      if (config.getBoolean("timerdeathswap.fixedSwapTime")) {
        ItemStack duration = new ItemStack(Material.CLOCK, 1);
        ItemMeta durationMeta = duration.getItemMeta();
        durationMeta.setDisplayName("§fTime Between Swaps");
        List<String> durationLore = new ArrayList<>();
        durationLore.add("§7Sets the duration between when");
        durationLore.add("§7players are teleported to around.");
        durationLore.add("§7");
        durationLore.add("§fDuration: §a§n" + (config.getInt("timerdeathswap.timeBetweenSwaps") / 60) + " minutes");
        durationLore.add("§7");
        durationLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nTime Between Swaps");
        durationMeta.setLore(durationLore);
        duration.setItemMeta(durationMeta);
        timerDeathSwapMenu.setItem(4, duration);
      } 
    } 
    return timerDeathSwapMenu;
  }
}
