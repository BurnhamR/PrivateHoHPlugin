package com.HideOrHunt.menus;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.ConfigRegistery;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpecificGameSettingsMenu implements Listener, InventoryHolder {
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
    File explodeConfigFile = new File("plugins/PrivateHideOrHunt/", "explode.yml");
    YamlConfiguration yamlConfiguration2 = YamlConfiguration.loadConfiguration(explodeConfigFile);
    if (menu.getTitle().equals("Specific Game Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§fSlowfall")) {
        player.openInventory(inventorySlowfallSettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§fInvisibility")) {
        player.openInventory(inventoryInvisibilitySettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§fPeace")) {
        player.openInventory(inventoryPeaceSettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§fBeacon Functions")) {
        player.openInventory(inventoryBeaconSettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§fDeath")) {
        player.openInventory(inventoryDeathSettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§fExplode Blacklist")) {
        player.openInventory(inventoryExplodeBlacklist((FileConfiguration)yamlConfiguration2));
      } else if (item.getItemMeta().getDisplayName().equals("§fCrafting Table Functions")) {
        if (yamlConfiguration1.getBoolean("gameSettings.craftingTablesEnabled")) {
          yamlConfiguration1.set("gameSettings.craftingTablesEnabled", Boolean.valueOf(false));
          player.sendMessage("§aCrafting Tables have been §c§nDisabled§a.");
          try {
            yamlConfiguration1.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Crafting Table Functions [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } else {
          yamlConfiguration1.set("gameSettings.craftingTablesEnabled", Boolean.valueOf(true));
          player.sendMessage("§aCrafting Tables have been §a§nEnabled§a.");
          try {
            yamlConfiguration1.save(configFile);
            Bukkit.getLogger().info("Hide or Hunt > Updating Crafting Table Functions [GameSettings].");
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
        player.closeInventory();
        player.openInventory(GameSettingsMenu.inventoryGameSettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§fBoss Bar")) {
        player.openInventory(inventoryBossBarSettings((FileConfiguration)yamlConfiguration1));
      } 
    } 
  }
  
  public static Inventory inventorySlowfallSettings(FileConfiguration config) {
    Inventory slowfallMenu = Bukkit.getServer().createInventory(null, 9, "Slowfall Settings");
    ItemStack status = new ItemStack(Material.FEATHER, 1);
    ItemMeta statusMeta = status.getItemMeta();
    statusMeta.setDisplayName("§fSlowfall Status");
    List<String> statusLore = new ArrayList<>();
    statusLore.add("§7Sets whether Slowfall is");
    statusLore.add("§7given at the start of the game.");
    statusLore.add("§7");
    if (config.getBoolean("gameSettings.slowfall.enabled")) {
      statusLore.add("§fEnabled: §a§nYes");
      statusLore.add("§7");
      statusLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nSlowfall");
    } else {
      statusLore.add("§fEnabled: §c§nNo");
      statusLore.add("§7");
      statusLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nSlowfall");
    } 
    statusMeta.setLore(statusLore);
    status.setItemMeta(statusMeta);
    slowfallMenu.setItem(0, status);
    if (config.getBoolean("gameSettings.slowfall.enabled")) {
      ItemStack duration = new ItemStack(Material.CLOCK, 1);
      ItemMeta durationMeta = duration.getItemMeta();
      durationMeta.setDisplayName("§fSlowfall Duration");
      List<String> durationLore = new ArrayList<>();
      durationLore.add("§7Sets the duration the Slowfall");
      durationLore.add("§7is at the start of the game.");
      durationLore.add("§7");
      durationLore.add("§fDuration: §a§n" + (config.getInt("gameSettings.slowfall.duration") / 60) + " minutes");
      durationLore.add("§7");
      durationLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nSlowfall Duration");
      durationMeta.setLore(durationLore);
      duration.setItemMeta(durationMeta);
      slowfallMenu.setItem(1, duration);
      ItemStack contact = new ItemStack(Material.DIAMOND_BOOTS, 1);
      ItemMeta contactMeta = contact.getItemMeta();
      contactMeta.setDisplayName("§fSlowfall Removal on Contact");
      List<String> contactLore = new ArrayList<>();
      contactLore.add("§7Sets whether the Slowfall is");
      contactLore.add("§7removed when the player walks");
      contactLore.add("§7on any block.");
      contactLore.add("§7");
      if (config.getBoolean("gameSettings.slowfall.removeOnContact")) {
        contactLore.add("§fEnabled: §a§nYes");
        contactLore.add("§7");
        contactLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nRemoval on Contact");
      } else {
        contactLore.add("§fEnabled: §c§nNo");
        contactLore.add("§7");
        contactLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nRemoval on Contact");
      } 
      contactMeta.setLore(contactLore);
      contactMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
      contact.setItemMeta(contactMeta);
      slowfallMenu.setItem(2, contact);
    } 
    return slowfallMenu;
  }
  
  public static Inventory inventoryInvisibilitySettings(FileConfiguration config) {
    Inventory invisibilityMenu = Bukkit.getServer().createInventory(null, 9, "Invisibility Settings");
    ItemStack status = new ItemStack(Material.SPIDER_EYE, 1);
    ItemMeta statusMeta = status.getItemMeta();
    statusMeta.setDisplayName("§fInvisibility Status");
    List<String> statusLore = new ArrayList<>();
    statusLore.add("§7Sets whether Invisibility");
    statusLore.add("§7is given out in the game.");
    statusLore.add("§7");
    if (config.getBoolean("gameSettings.invis.enabled")) {
      statusLore.add("§fEnabled: §a§nYes");
      statusLore.add("§7");
      statusLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nInvisibility");
    } else {
      statusLore.add("§fEnabled: §c§nNo");
      statusLore.add("§7");
      statusLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nInvisibility");
    } 
    statusMeta.setLore(statusLore);
    status.setItemMeta(statusMeta);
    invisibilityMenu.setItem(0, status);
    if (config.getBoolean("gameSettings.invis.enabled")) {
      ItemStack type = new ItemStack(Material.POTION, 1);
      ItemMeta typeMeta = type.getItemMeta();
      typeMeta.setDisplayName("§fInvisibility Type");
      List<String> typeLore = new ArrayList<>();
      typeLore.add("§7Sets the method that Invisibility");
      typeLore.add("§7will be given out in the game.");
      typeLore.add("§7");
      if (config.getString("gameSettings.invis.given").equals("potion")) {
        typeLore.add("§fMethod: §a§nPotion");
        typeLore.add("§7");
        typeLore.add("§f§l<!>§r §fClick to §nChange§r §fto §nAutomatic Giving");
      } else if (config.getString("gameSettings.invis.given").equals("auto")) {
        typeLore.add("§fMethod: §a§nAutomatic");
        typeLore.add("§7");
        typeLore.add("§f§l<!>§r §fClick to §nChange§r §fto §nPotion Giving");
      } 
      typeMeta.setLore(typeLore);
      typeMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
      type.setItemMeta(typeMeta);
      invisibilityMenu.setItem(1, type);
      ItemStack duration = new ItemStack(Material.CLOCK, 1);
      ItemMeta durationMeta = duration.getItemMeta();
      durationMeta.setDisplayName("§fInvisibility Duration");
      List<String> durationLore = new ArrayList<>();
      durationLore.add("§7Sets the duration the Invisibility");
      durationLore.add("§7is when its give out in the game.");
      durationLore.add("§7");
      durationLore.add("§fDuration: §a§n" + (config.getInt("gameSettings.invis.duration") / 60) + " minutes");
      durationLore.add("§7");
      durationLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nInvisibility Duration");
      durationMeta.setLore(durationLore);
      duration.setItemMeta(durationMeta);
      invisibilityMenu.setItem(2, duration);
      ItemStack delay = new ItemStack(Material.COMPASS, 1);
      ItemMeta delayMeta = delay.getItemMeta();
      delayMeta.setDisplayName("§fInvisibility Delay");
      List<String> delayLore = new ArrayList<>();
      delayLore.add("§7Sets the delay before the");
      delayLore.add("§7Invisibility is given out.");
      delayLore.add("§7");
      delayLore.add("§fDelay Before Giving: §a§n" + (config.getInt("gameSettings.invis.timeBeforeGive") / 60) + " minutes");
      delayLore.add("§7");
      delayLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nInvisibility Delay");
      delayMeta.setLore(delayLore);
      delay.setItemMeta(delayMeta);
      invisibilityMenu.setItem(3, delay);
      ItemStack contact = new ItemStack(Material.DIAMOND_SWORD, 1);
      ItemMeta contactMeta = contact.getItemMeta();
      contactMeta.setDisplayName("§fInvisibility Removal on Damage");
      List<String> contactLore = new ArrayList<>();
      contactLore.add("§7Sets whether the Invisibility is");
      contactLore.add("§7removed when the player takes");
      contactLore.add("§7damage from a mob/player.");
      contactLore.add("§7");
      if (config.getBoolean("gameSettings.invis.removeOnDamageDone")) {
        contactLore.add("§fEnabled: §a§nYes");
        contactLore.add("§7");
        contactLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nRemoval on Damage");
      } else {
        contactLore.add("§fEnabled: §c§nNo");
        contactLore.add("§7");
        contactLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nRemoval on Damage");
      } 
      contactMeta.setLore(contactLore);
      contactMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
      contact.setItemMeta(contactMeta);
      invisibilityMenu.setItem(4, contact);
    } 
    return invisibilityMenu;
  }
  
  public static Inventory inventoryPeaceSettings(FileConfiguration config) {
    Inventory peaceMenu = Bukkit.getServer().createInventory(null, 9, "Peace Settings");
    ItemStack status = new ItemStack(Material.ELYTRA, 1);
    ItemMeta statusMeta = status.getItemMeta();
    statusMeta.setDisplayName("§fPeace Status");
    List<String> statusLore = new ArrayList<>();
    statusLore.add("§7Sets whether Peace Mode");
    statusLore.add("§7is enabled in the game.");
    statusLore.add("§7");
    if (config.getBoolean("gameSettings.peaceful.enabled")) {
      statusLore.add("§fEnabled: §a§nYes");
      statusLore.add("§7");
      statusLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nPeace");
    } else {
      statusLore.add("§fEnabled: §c§nNo");
      statusLore.add("§7");
      statusLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nPeace");
    } 
    statusMeta.setLore(statusLore);
    status.setItemMeta(statusMeta);
    peaceMenu.setItem(0, status);
    if (config.getBoolean("gameSettings.peaceful.enabled")) {
      ItemStack delay = new ItemStack(Material.COMPASS, 1);
      ItemMeta delayMeta = delay.getItemMeta();
      delayMeta.setDisplayName("§fPeace Delay");
      List<String> delayLore = new ArrayList<>();
      delayLore.add("§7Sets the delay before the");
      delayLore.add("§7Peace Mode is disabled.");
      delayLore.add("§7");
      delayLore.add("§fDelay Before Removing: §a§n" + (config.getInt("gameSettings.peaceful.delayToRemove") / 60) + " minutes");
      delayLore.add("§7");
      delayLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nPeace Delay");
      delayMeta.setLore(delayLore);
      delay.setItemMeta(delayMeta);
      peaceMenu.setItem(1, delay);
    } 
    return peaceMenu;
  }
  
  public static Inventory inventoryBeaconSettings(FileConfiguration config) {
    Inventory beaconMenu = Bukkit.getServer().createInventory(null, 9, "Beacon Settings");
    ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE, 1);
    ItemMeta craftingMeta = crafting.getItemMeta();
    craftingMeta.setDisplayName("§fCrafting @ Beacon");
    List<String> craftingLore = new ArrayList<>();
    craftingLore.add("§7Sets whether players can");
    craftingLore.add("§7use their beacon for crafting.");
    craftingLore.add("§7");
    if (config.getBoolean("gameSettings.beacon.crafting")) {
      craftingLore.add("§fEnabled: §a§nYes");
      craftingLore.add("§7");
      craftingLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nCrafting @ Beacon");
    } else {
      craftingLore.add("§fEnabled: §c§nNo");
      craftingLore.add("§7");
      craftingLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nCrafting @ Beacon");
    } 
    craftingMeta.setLore(craftingLore);
    crafting.setItemMeta(craftingMeta);
    beaconMenu.setItem(0, crafting);
    ItemStack respawning = new ItemStack(Material.BLACK_BED, 1);
    ItemMeta respawningMeta = respawning.getItemMeta();
    respawningMeta.setDisplayName("§fRespawning @ Beacon");
    List<String> respawningLore = new ArrayList<>();
    respawningLore.add("§7Sets whether players can");
    respawningLore.add("§7use their beacon for respawning.");
    respawningLore.add("§7");
    if (config.getBoolean("gameSettings.beacon.respawn")) {
      respawningLore.add("§fEnabled: §a§nYes");
      respawningLore.add("§7");
      respawningLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nRespawning @ Beacon");
    } else {
      respawningLore.add("§fEnabled: §c§nNo");
      respawningLore.add("§7");
      respawningLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nRespawning @ Beacon");
    } 
    respawningMeta.setLore(respawningLore);
    respawning.setItemMeta(respawningMeta);
    beaconMenu.setItem(1, respawning);
    ItemStack leaderPlace = new ItemStack(Material.DIAMOND, 1);
    ItemMeta leaderPlaceMeta = leaderPlace.getItemMeta();
    leaderPlaceMeta.setDisplayName("§fLeader-Only Placing");
    List<String> leaderPlaceLore = new ArrayList<>();
    leaderPlaceLore.add("§7Sets whether only-leaders can");
    leaderPlaceLore.add("§7place their team beacon.");
    leaderPlaceLore.add("§7");
    if (config.getBoolean("gameSettings.beacon.leaderOnlyPlace")) {
      leaderPlaceLore.add("§fEnabled: §a§nYes");
      leaderPlaceLore.add("§7");
      leaderPlaceLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nLeader-Only Placing");
    } else {
      leaderPlaceLore.add("§fEnabled: §c§nNo");
      leaderPlaceLore.add("§7");
      leaderPlaceLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nLeader-Only Placing");
    } 
    leaderPlaceMeta.setLore(leaderPlaceLore);
    leaderPlace.setItemMeta(leaderPlaceMeta);
    beaconMenu.setItem(2, leaderPlace);
    return beaconMenu;
  }
  
  public static Inventory inventoryDeathSettings(FileConfiguration config) {
    Inventory deathMenu = Bukkit.getServer().createInventory(null, 9, "Death Settings");
    ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
    ItemMeta xpMeta = xp.getItemMeta();
    xpMeta.setDisplayName("§fKeep XP");
    List<String> xpLore = new ArrayList<>();
    xpLore.add("§7Sets whether players have a");
    xpLore.add("§7chance to save XP on death.");
    xpLore.add("§7");
    if (config.getBoolean("gameSettings.death.keepXPOnDeath")) {
      xpLore.add("§fEnabled: §a§nYes");
      xpLore.add("§7");
      xpLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nKeep XP on Death");
    } else {
      xpLore.add("§fEnabled: §c§nNo");
      xpLore.add("§7");
      xpLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nKeep XP on Death");
    } 
    xpMeta.setLore(xpLore);
    xp.setItemMeta(xpMeta);
    deathMenu.setItem(0, xp);
    ItemStack xpChance = new ItemStack(Material.CLOCK, 1);
    ItemMeta xpChanceMeta = xpChance.getItemMeta();
    xpChanceMeta.setDisplayName("§fXP Chances");
    List<String> xpChanceLore = new ArrayList<>();
    xpChanceLore.add("§7Sets the chances of players");
    xpChanceLore.add("§7keeping their XP on death.");
    xpChanceLore.add("§7");
    xpChanceLore.add("§fChances: §a§n" + (int)(config.getDouble("gameSettings.death.chanceXPSave") * 100.0D) + "%");
    xpChanceLore.add("§7");
    xpChanceLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nXP Chances");
    xpChanceMeta.setLore(xpChanceLore);
    xpChance.setItemMeta(xpChanceMeta);
    deathMenu.setItem(1, xpChance);
    ItemStack items = new ItemStack(Material.CHEST, 1);
    ItemMeta itemsMeta = items.getItemMeta();
    itemsMeta.setDisplayName("§fKeep Items");
    List<String> itemsLore = new ArrayList<>();
    itemsLore.add("§7Sets whether players have a");
    itemsLore.add("§7chance to save Items on death.");
    itemsLore.add("§7");
    if (config.getBoolean("gameSettings.death.keepItemsOnDeath")) {
      itemsLore.add("§fEnabled: §a§nYes");
      itemsLore.add("§7");
      itemsLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nKeep Items on Death");
    } else {
      itemsLore.add("§fEnabled: §c§nNo");
      itemsLore.add("§7");
      itemsLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nKeep Items on Death");
    } 
    itemsMeta.setLore(itemsLore);
    items.setItemMeta(itemsMeta);
    deathMenu.setItem(2, items);
    ItemStack itemChance = new ItemStack(Material.CLOCK, 1);
    ItemMeta itemChanceMeta = itemChance.getItemMeta();
    itemChanceMeta.setDisplayName("§fItem Chances");
    List<String> itemChanceLore = new ArrayList<>();
    itemChanceLore.add("§7Sets the chances of players");
    itemChanceLore.add("§7keeping their Items on death.");
    itemChanceLore.add("§7");
    itemChanceLore.add("§fChances: §a§n" + (int)(config.getDouble("gameSettings.death.chanceItemSave") * 100.0D) + "%");
    itemChanceLore.add("§7");
    itemChanceLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nItem Chances");
    itemChanceMeta.setLore(itemChanceLore);
    itemChance.setItemMeta(itemChanceMeta);
    deathMenu.setItem(3, itemChance);
    ItemStack kick = new ItemStack(Material.DARK_OAK_DOOR, 1);
    ItemMeta kickMeta = kick.getItemMeta();
    kickMeta.setDisplayName("§fAuto Kick");
    List<String> kickLore = new ArrayList<>();
    kickLore.add("§7Sets whether players are");
    kickLore.add("§7kicked after their last life.");
    kickLore.add("§7");
    if (config.getBoolean("gameSettings.death.autokick")) {
      kickLore.add("§fEnabled: §a§nYes");
      kickLore.add("§7");
      kickLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nAuto Kick");
    } else {
      kickLore.add("§fEnabled: §c§nNo");
      kickLore.add("§7");
      kickLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nAuto Kick");
    } 
    kickMeta.setLore(kickLore);
    kick.setItemMeta(kickMeta);
    deathMenu.setItem(4, kick);
    ItemStack whitelist = new ItemStack(Material.PAPER, 1);
    ItemMeta whitelistMeta = whitelist.getItemMeta();
    whitelistMeta.setDisplayName("§fAuto Unwhitelist");
    List<String> whitelistLore = new ArrayList<>();
    whitelistLore.add("§7Sets whether players are");
    whitelistLore.add("§7unwhitelisted after their last life.");
    whitelistLore.add("§7");
    if (config.getBoolean("gameSettings.death.autounwhitelist")) {
      whitelistLore.add("§fEnabled: §a§nYes");
      whitelistLore.add("§7");
      whitelistLore.add("§f§l<!>§r §fClick to §nDisable§r §f§nAuto Unwhitelist");
    } else {
      whitelistLore.add("§fEnabled: §c§nNo");
      whitelistLore.add("§7");
      whitelistLore.add("§f§l<!>§r §fClick to §nEnable§r §f§nAuto Unwhitelist");
    } 
    whitelistMeta.setLore(whitelistLore);
    whitelist.setItemMeta(whitelistMeta);
    deathMenu.setItem(5, whitelist);
    return deathMenu;
  }
  
  public static Inventory inventoryBossBarSettings(FileConfiguration config) {
    Inventory bossbarMenu = Bukkit.getServer().createInventory(null, 9, "Boss Bar Settings");
    ItemStack bossbar = new ItemStack(Material.DRAGON_HEAD, 1);
    ItemMeta bossbarMeta = bossbar.getItemMeta();
    bossbarMeta.setDisplayName("§fDisplay Boss Bar");
    List<String> bossbarLore = new ArrayList<>();
    bossbarLore.add("§7Sets whether players the");
    bossbarLore.add("§7boss bar is displayed.");
    bossbarLore.add("§7");
    if (config.getBoolean("gameSettings.bossbar.enabled")) {
      bossbarLore.add("§fEnabled: §a§nYes");
      bossbarLore.add("§7");
      bossbarLore.add("§f§l<!>§r §fClick to §nDisable§r §fthe §nBoss Bar");
    } else {
      bossbarLore.add("§fEnabled: §c§nNo");
      bossbarLore.add("§7");
      bossbarLore.add("§f§l<!>§r §fClick to §nEnable§r §fthe §nBoss Bar");
    } 
    bossbarMeta.setLore(bossbarLore);
    bossbar.setItemMeta(bossbarMeta);
    bossbarMenu.setItem(0, bossbar);
    if (config.getBoolean("gameSettings.bossbar.enabled")) {
      ItemStack disableForAll = new ItemStack(Material.BOOK, 1);
      ItemMeta disableForAllMeta = disableForAll.getItemMeta();
      disableForAllMeta.setDisplayName("§fDisable for All");
      List<String> disableForAllLore = new ArrayList<>();
      disableForAllLore.add("§7Sets whether all players");
      disableForAllLore.add("§7have the ability to disable");
      disableForAllLore.add("§7the boss bar.");
      disableForAllLore.add("§7");
      if (config.getBoolean("gameSettings.bossbar.allowDisableForAll")) {
        disableForAllLore.add("§fEnabled: §a§nYes");
        disableForAllLore.add("§7");
        disableForAllLore.add("§f§l<!>§r §fClick to §nDisable§r §fthe §nDisable for All");
      } else {
        disableForAllLore.add("§fEnabled: §c§nNo");
        disableForAllLore.add("§7");
        disableForAllLore.add("§f§l<!>§r §fClick to §nEnable§r §fthe §nDisable for All");
      } 
      disableForAllMeta.setLore(disableForAllLore);
      disableForAll.setItemMeta(disableForAllMeta);
      bossbarMenu.setItem(1, disableForAll);
    } 
    return bossbarMenu;
  }
  
  public static Inventory inventoryExplodeBlacklist(FileConfiguration config) {
    Inventory explodeMenu = Bukkit.getServer().createInventory(null, 54, "Explode Blacklist");
    if (!config.isSet("blacklist.0.valid")) {
      ConfigRegistery.loadExplodeBlacklist(HideOrHunt.getInstance(), "explode");
      return explodeMenu;
    } 
    return plotInventory(config, explodeMenu);
  }
  
  public static Inventory plotInventory(FileConfiguration config, Inventory invToPlot) {
    Inventory blacklistToPlot = invToPlot;
    ConfigurationSection blacklistedItems = config.getConfigurationSection("blacklist");
    for (String item : blacklistedItems.getKeys(false)) {
      if (config.getBoolean("blacklist." + item + ".valid")) {
        ItemStack guiitem = new ItemStack(Material.getMaterial(config.getString("blacklist." + item + ".material")), 1);
        ItemMeta guiitemMeta = guiitem.getItemMeta();
        guiitem.setItemMeta(guiitemMeta);
        blacklistToPlot.setItem(Integer.parseInt(item), guiitem);
      } 
    } 
    return blacklistToPlot;
  }
}
