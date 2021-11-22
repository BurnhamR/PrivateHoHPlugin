package com.HideOrHunt.menus;

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

public class GameSettingsMenu implements Listener, InventoryHolder {
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
    if (menu.getTitle().equals("Game Settings")) {
      event.setCancelled(true);
      if (menu.getItem(slotClicked) == null)
        return; 
      if (item.getItemMeta().getDisplayName().equals("§eTeam Type")) {
        player.openInventory(inventoryTeamType());
      } else if (item.getItemMeta().getDisplayName().equals("§eTeam Count")) {
        player.openInventory(inventoryTeamCount((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§eTeam Name Settings")) {
        player.openInventory(inventoryTeamNameSettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§cMob Settings")) {
        player.openInventory(inventoryMobSettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§bServer Settings")) {
        player.openInventory(inventoryServerSettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§dGame Kits")) {
        player.openInventory(inventoryGameKits((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§dGame Settings")) {
        player.openInventory(inventoryGameSettings((FileConfiguration)yamlConfiguration1));
      } else if (item.getItemMeta().getDisplayName().equals("§aCustom Gamemodes")) {
        player.openInventory(inventoryCustomGamemodes((FileConfiguration)yamlConfiguration2));
      } 
    } 
  }
  
  public static Inventory inventoryTeamType() {
    Inventory teamTypeMenu = Bukkit.getServer().createInventory(null, 9, "Team Type");
    ItemStack solo = new ItemStack(Material.PLAYER_HEAD, 1);
    ItemMeta soloMeta = solo.getItemMeta();
    soloMeta.setDisplayName("§eSolo: 1 Player");
    List<String> soloLore = new ArrayList<>();
    soloLore.add("§fTeam Information:");
    soloLore.add("§f - §7Team Type: §f§nSOLO");
    soloLore.add("§f - §7Team Size: §f§n1");
    soloLore.add("§7");
    soloLore.add("§f§l<!>§r §fClick to §nSelect§r §fthe §nSolo Mode");
    soloMeta.setLore(soloLore);
    solo.setItemMeta(soloMeta);
    teamTypeMenu.setItem(0, solo);
    ItemStack duo = new ItemStack(Material.PLAYER_HEAD, 2);
    ItemMeta duoMeta = duo.getItemMeta();
    duoMeta.setDisplayName("§eDuo: 2 Players");
    List<String> duoLore = new ArrayList<>();
    duoLore.add("§fTeam Information:");
    duoLore.add("§f - §7Team Type: §f§nDUO");
    duoLore.add("§f - §7Team Size: §f§n2");
    duoLore.add("§7");
    duoLore.add("§f§l<!>§r §fClick to §nSelect§r §fthe §nDuo Mode");
    duoMeta.setLore(duoLore);
    duo.setItemMeta(duoMeta);
    teamTypeMenu.setItem(1, duo);
    ItemStack trio = new ItemStack(Material.PLAYER_HEAD, 3);
    ItemMeta trioMeta = trio.getItemMeta();
    trioMeta.setDisplayName("§eTrio: 3 Players");
    List<String> trioLore = new ArrayList<>();
    trioLore.add("§fTeam Information:");
    trioLore.add("§f - §7Team Type: §f§nTRIO");
    trioLore.add("§f - §7Team Size: §f§n3");
    trioLore.add("§7");
    trioLore.add("§f§l<!>§r §fClick to §nSelect§r §fthe §nTrio Mode");
    trioMeta.setLore(trioLore);
    trio.setItemMeta(trioMeta);
    teamTypeMenu.setItem(2, trio);
    ItemStack quad = new ItemStack(Material.PLAYER_HEAD, 4);
    ItemMeta quadMeta = quad.getItemMeta();
    quadMeta.setDisplayName("§eQuad: 4 Players");
    List<String> quadLore = new ArrayList<>();
    quadLore.add("§fTeam Information:");
    quadLore.add("§f - §7Team Type: §f§nQUAD");
    quadLore.add("§f - §7Team Size: §f§n4");
    quadLore.add("§7");
    quadLore.add("§f§l<!>§r §fClick to §nSelect§r §fthe §nQuad Mode");
    quadMeta.setLore(quadLore);
    quad.setItemMeta(quadMeta);
    teamTypeMenu.setItem(3, quad);
    ItemStack squad10 = new ItemStack(Material.PLAYER_HEAD, 10);
    ItemMeta squad10Meta = squad10.getItemMeta();
    squad10Meta.setDisplayName("§eSquad: 10 Players");
    List<String> squad10Lore = new ArrayList<>();
    squad10Lore.add("§fTeam Information:");
    squad10Lore.add("§f - §7Team Type: §f§nSQUAD");
    squad10Lore.add("§f - §7Team Size: §f§n10");
    squad10Lore.add("§7");
    squad10Lore.add("§f§l<!>§r §fClick to §nSelect§r §fthe §nSquad [10] Mode");
    squad10Meta.setLore(squad10Lore);
    squad10.setItemMeta(squad10Meta);
    teamTypeMenu.setItem(4, squad10);
    return teamTypeMenu;
  }
  
  public static Inventory inventoryTeamCount(FileConfiguration config) {
    Inventory teamCountMenu = Bukkit.getServer().createInventory(null, 54, "Team Count");
    for (int i = 0; i < 50; i++) {
      ItemStack teamCount = new ItemStack(Material.WHITE_WOOL, i + 2);
      if (i + 2 == config.getInt("teamsSettings.maximumTeamCount"))
        teamCount.setType(Material.RED_WOOL); 
      ItemMeta teamCountMeta = teamCount.getItemMeta();
      teamCountMeta.setDisplayName("§eMaximum Teams: " + (i + 2));
      List<String> teamCountLore = new ArrayList<>();
      teamCountLore.add("§fMaximum Team Information:");
      teamCountLore.add("§f - §7Maximum Teams: §f§n" + (i + 2));
      teamCountLore.add("§7");
      teamCountLore.add("§f§l<!>§r §fClick to §nSelect§r §f§n" + (i + 2) + "§f Maximum Teams");
      teamCountMeta.setLore(teamCountLore);
      teamCount.setItemMeta(teamCountMeta);
      teamCountMenu.setItem(i, teamCount);
    } 
    return teamCountMenu;
  }
  
  public static Inventory inventoryTeamNameSettings(FileConfiguration config) {
    Inventory teamNameSettingsMenu = Bukkit.getServer().createInventory(null, 9, "Team Name Settings");
    ItemStack teamMaximumCharacters = new ItemStack(Material.NAME_TAG, 1);
    ItemMeta teamMaximumCharactersMeta = teamMaximumCharacters.getItemMeta();
    teamMaximumCharactersMeta.setDisplayName("§eMaximum Name Characters");
    if (config.getInt("teamsSettings.maxTeamNameCharacters") > 64) {
      teamMaximumCharacters.setAmount(64);
    } else if (config.getInt("teamsSettings.maxTeamNameCharacters") < 1) {
      teamMaximumCharacters.setAmount(1);
    } else {
      teamMaximumCharacters.setAmount(config.getInt("teamsSettings.maxTeamNameCharacters"));
    } 
    List<String> teamMaximumCharactersLore = new ArrayList<>();
    teamMaximumCharactersLore.add("§7Set the maximum amount of allowed");
    teamMaximumCharactersLore.add("§7characters for a team name.");
    teamMaximumCharactersLore.add("§7");
    teamMaximumCharactersLore.add("§fName Information:");
    teamMaximumCharactersLore.add("§f - §7Maximum Characters: §f§n" + config.getInt("teamsSettings.maxTeamNameCharacters"));
    teamMaximumCharactersLore.add("§7");
    teamMaximumCharactersLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nMaximum Name Characters");
    teamMaximumCharactersMeta.setLore(teamMaximumCharactersLore);
    teamMaximumCharacters.setItemMeta(teamMaximumCharactersMeta);
    teamNameSettingsMenu.setItem(0, teamMaximumCharacters);
    ItemStack teamMinimumCharacters = new ItemStack(Material.NAME_TAG, 1);
    ItemMeta teamMinimumCharactersMeta = teamMinimumCharacters.getItemMeta();
    teamMinimumCharactersMeta.setDisplayName("§eMinimum Name Characters");
    if (config.getInt("teamsSettings.minTeamNameCharacters") > 64) {
      teamMinimumCharacters.setAmount(64);
    } else if (config.getInt("teamsSettings.minTeamNameCharacters") < 1) {
      teamMinimumCharacters.setAmount(1);
    } else {
      teamMinimumCharacters.setAmount(config.getInt("teamsSettings.minTeamNameCharacters"));
    } 
    List<String> teamMinimumCharactersLore = new ArrayList<>();
    teamMinimumCharactersLore.add("§7Set the minimum amount of allowed");
    teamMinimumCharactersLore.add("§7characters for a team name.");
    teamMinimumCharactersLore.add("§7");
    teamMinimumCharactersLore.add("§fName Information:");
    teamMinimumCharactersLore.add("§f - §7Minimum Characters: §f§n" + config.getInt("teamsSettings.minTeamNameCharacters"));
    teamMinimumCharactersLore.add("§7");
    teamMinimumCharactersLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nMinimum Name Characters");
    teamMinimumCharactersMeta.setLore(teamMinimumCharactersLore);
    teamMinimumCharacters.setItemMeta(teamMinimumCharactersMeta);
    teamNameSettingsMenu.setItem(1, teamMinimumCharacters);
    ItemStack teamNumberName = new ItemStack(Material.WHITE_WOOL, 1);
    ItemMeta teamNumberMeta = teamNumberName.getItemMeta();
    teamNumberMeta.setDisplayName("§eNumbers");
    List<String> teamNumberLore = new ArrayList<>();
    teamNumberLore.add("§7Set whether numbers can be");
    teamNumberLore.add("§7used inside of team names.");
    teamNumberLore.add("§7");
    teamNumberLore.add("§fName Information:");
    if (config.getBoolean("teamsSettings.canTeamNameContainNumber")) {
      teamNumberLore.add("§f - §7Numbers: §a§nAllowed");
    } else {
      teamNumberLore.add("§f - §7Numbers: §c§nDenied");
    } 
    teamNumberLore.add("§7");
    teamNumberLore.add("§f§l<!>§r §fClick to §nToggle§r §f§nNumbers§r §fin a §nTeam Name");
    teamNumberMeta.setLore(teamNumberLore);
    teamNumberName.setItemMeta(teamNumberMeta);
    teamNameSettingsMenu.setItem(2, teamNumberName);
    ItemStack teamCharacterName = new ItemStack(Material.SPONGE, 1);
    ItemMeta teamCharacterMeta = teamCharacterName.getItemMeta();
    teamCharacterMeta.setDisplayName("§eRandom Characters");
    List<String> teamCharacterLore = new ArrayList<>();
    teamCharacterLore.add("§7Set whether random characters can");
    teamCharacterLore.add("§7be used inside of team names.");
    teamCharacterLore.add("§7");
    teamCharacterLore.add("§fName Information:");
    if (config.getBoolean("teamsSettings.canTeamNameContainRandomCharacters")) {
      teamCharacterLore.add("§f - §7Random Characters: §a§nAllowed");
    } else {
      teamCharacterLore.add("§f - §7Random Characters: §c§nDenied");
    } 
    teamCharacterLore.add("§7");
    teamCharacterLore.add("§f§l<!>§r §fClick to §nToggle§r §f§nRandom Characters§r §fin a §nTeam Name");
    teamCharacterMeta.setLore(teamCharacterLore);
    teamCharacterName.setItemMeta(teamCharacterMeta);
    teamNameSettingsMenu.setItem(3, teamCharacterName);
    return teamNameSettingsMenu;
  }
  
  public Inventory inventoryMobSettings(FileConfiguration config) {
    Inventory mobSettingsMenu = Bukkit.getServer().createInventory(null, 9, "Mob Settings");
    ItemStack mobSpawn = new ItemStack(Material.PIG_SPAWN_EGG, 1);
    ItemMeta mobSpawnMeta = mobSpawn.getItemMeta();
    mobSpawnMeta.setDisplayName("§cMob Spawn Settings");
    List<String> mobSpawnLore = new ArrayList<>();
    mobSpawnLore.add("§7Set which mobs can spawn");
    mobSpawnLore.add("§7from any given spawn method.");
    mobSpawnLore.add("§7");
    mobSpawnLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nAllowed Mob Spawns");
    mobSpawnMeta.setLore(mobSpawnLore);
    mobSpawn.setItemMeta(mobSpawnMeta);
    mobSettingsMenu.setItem(0, mobSpawn);
    ItemStack mobBreed = new ItemStack(Material.WHEAT, 1);
    ItemMeta mobBreedMeta = mobBreed.getItemMeta();
    mobBreedMeta.setDisplayName("§cMob Breeding Settings");
    List<String> mobBreedLore = new ArrayList<>();
    mobBreedLore.add("§7Set which mobs can breed");
    mobBreedLore.add("§7with other mobs ingame.");
    mobBreedLore.add("§7");
    mobBreedLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nAllowed Mob Breeding");
    mobBreedMeta.setLore(mobBreedLore);
    mobBreed.setItemMeta(mobBreedMeta);
    mobSettingsMenu.setItem(1, mobBreed);
    ItemStack mobTame = new ItemStack(Material.BONE, 1);
    ItemMeta mobTameMeta = mobTame.getItemMeta();
    mobTameMeta.setDisplayName("§cMob Taming Settings");
    List<String> mobTameLore = new ArrayList<>();
    mobTameLore.add("§7Set which mobs can be tamed.");
    mobTameLore.add("§7");
    mobTameLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nAllowed Mob Taming");
    mobTameMeta.setLore(mobTameLore);
    mobTame.setItemMeta(mobTameMeta);
    mobSettingsMenu.setItem(2, mobTame);
    return mobSettingsMenu;
  }
  
  public Inventory inventoryServerSettings(FileConfiguration config) {
    Inventory serverSettingsMenu = Bukkit.getServer().createInventory(null, 9, "Server Settings");
    ItemStack weather = new ItemStack(Material.NETHER_STAR, 1);
    ItemMeta weatherMeta = weather.getItemMeta();
    weatherMeta.setDisplayName("§bWeather Settings");
    List<String> weatherLore = new ArrayList<>();
    weatherLore.add("§7Set whether weather rotates");
    weatherLore.add("§7or is set to a fixed type.");
    weatherLore.add("§7");
    weatherLore.add("§fWeather Information:");
    if (config.getString("serverSettings.weather").equals("rotate")) {
      weatherLore.add("§f - §7Weather Type: §a§nRotates");
    } else if (config.getString("serverSettings.weather").equals("sun")) {
      weatherLore.add("§f - §7Weather Type: §e§nSunny");
    } else if (config.getString("serverSettings.weather").equals("rain")) {
      weatherLore.add("§f - §7Weather Type: §b§nRainy");
    } else if (config.getString("serverSettings.weather").equals("storm")) {
      weatherLore.add("§f - §7Weather Type: §f§nStormy");
    } 
    weatherLore.add("§7");
    weatherLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nWeather");
    weatherMeta.setLore(weatherLore);
    weather.setItemMeta(weatherMeta);
    serverSettingsMenu.setItem(0, weather);
    ItemStack entryMessages = new ItemStack(Material.BOOK, 1);
    ItemMeta entryMessagesMeta = entryMessages.getItemMeta();
    entryMessagesMeta.setDisplayName("§bEntry Messages");
    List<String> entryMessagesLore = new ArrayList<>();
    entryMessagesLore.add("§7Set whether join/leave messages");
    entryMessagesLore.add("§7are displayed to everyone or not.");
    entryMessagesLore.add("§7");
    entryMessagesLore.add("§fMessage Information:");
    if (config.getString("serverSettings.entryMessages").equals("off")) {
      entryMessagesLore.add("§f - §7Messages: §c§nDisabled");
    } else if (config.getString("serverSettings.entryMessages").equals("on")) {
      entryMessagesLore.add("§f - §7Messages: §a§nEnabled");
    } else if (config.getString("serverSettings.entryMessages").equals("op")) {
      entryMessagesLore.add("§f - §7Messages: §d§nOP Only");
    } 
    entryMessagesLore.add("§7");
    entryMessagesLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nEntry Messages Visibility");
    entryMessagesMeta.setLore(entryMessagesLore);
    entryMessages.setItemMeta(entryMessagesMeta);
    serverSettingsMenu.setItem(1, entryMessages);
    ItemStack deathMessages = new ItemStack(Material.ENCHANTED_BOOK, 1);
    ItemMeta deathMessagesMeta = deathMessages.getItemMeta();
    deathMessagesMeta.setDisplayName("§bDeath Messages");
    List<String> deathMessagesLore = new ArrayList<>();
    deathMessagesLore.add("§7Set whether death messages");
    deathMessagesLore.add("§7are displayed to everyone or not.");
    deathMessagesLore.add("§7");
    deathMessagesLore.add("§fMessage Information:");
    if (config.getString("serverSettings.deathMessages").equals("off")) {
      deathMessagesLore.add("§f - §7Messages: §c§nDisabled");
    } else if (config.getString("serverSettings.deathMessages").equals("on")) {
      deathMessagesLore.add("§f - §7Messages: §a§nEnabled");
    } else if (config.getString("serverSettings.deathMessages").equals("op")) {
      deathMessagesLore.add("§f - §7Messages: §d§nOP Only");
    } 
    deathMessagesLore.add("§7");
    deathMessagesLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nDeath Messages Visibility");
    deathMessagesMeta.setLore(deathMessagesLore);
    deathMessages.setItemMeta(deathMessagesMeta);
    serverSettingsMenu.setItem(2, deathMessages);
    return serverSettingsMenu;
  }
  
  public static Inventory inventoryGameKits(FileConfiguration config) {
    Inventory gameKitsMenu = Bukkit.getServer().createInventory(null, 9, "Game Kits");
    ItemStack startingHOH = new ItemStack(Material.BEACON, 1);
    ItemMeta startingHOHMeta = startingHOH.getItemMeta();
    startingHOHMeta.setDisplayName("§dStarting Kit §8[§7HoH§8]");
    List<String> startingHOHLore = new ArrayList<>();
    startingHOHLore.add("§7Set the items that players");
    startingHOHLore.add("§7get when playing Normal HoH.");
    startingHOHLore.add("§7");
    startingHOHLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nStarting Kit");
    startingHOHMeta.setLore(startingHOHLore);
    startingHOH.setItemMeta(startingHOHMeta);
    gameKitsMenu.setItem(0, startingHOH);
    ItemStack deathHOH = new ItemStack(Material.SKELETON_SKULL, 1);
    ItemMeta deathHOHMeta = deathHOH.getItemMeta();
    deathHOHMeta.setDisplayName("§dDeath Kit §8[§7HoH§8]");
    List<String> deathHOHLore = new ArrayList<>();
    deathHOHLore.add("§7Set the items that players");
    deathHOHLore.add("§7get when dying in Normal HoH.");
    deathHOHLore.add("§7");
    deathHOHLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nDeath Kit");
    deathHOHMeta.setLore(deathHOHLore);
    deathHOH.setItemMeta(deathHOHMeta);
    gameKitsMenu.setItem(1, deathHOH);
    return gameKitsMenu;
  }
  
  public static Inventory inventoryGameSettings(FileConfiguration config) {
    Inventory gameSettingsMenu = Bukkit.getServer().createInventory(null, 9, "Specific Game Settings");
    ItemStack slowfall = new ItemStack(Material.FEATHER, 1);
    ItemMeta slowfallMeta = slowfall.getItemMeta();
    slowfallMeta.setDisplayName("§fSlowfall");
    List<String> slowfallLore = new ArrayList<>();
    slowfallLore.add("§7Sets the settings around");
    slowfallLore.add("§7Slowfall at the start of the game.");
    slowfallLore.add("§7");
    slowfallLore.add("§fSlowfall Information:");
    if (config.getBoolean("gameSettings.slowfall.enabled")) {
      slowfallLore.add("§f - §7Enabled: §a§nYes");
      slowfallLore.add("§f - §7Duration: §a§n" + config.getInt("gameSettings.slowfall.duration") + " seconds");
      if (config.getBoolean("gameSettings.slowfall.removeOnContact")) {
        slowfallLore.add("§f - §7Contact Removal: §a§nYes");
      } else {
        slowfallLore.add("§f - §7Contact Removal: §c§nNo");
      } 
    } else {
      slowfallLore.add("§f - §7Enabled: §c§nNo");
    } 
    slowfallLore.add("§7");
    slowfallLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nSlowfall Settings");
    slowfallMeta.setLore(slowfallLore);
    slowfall.setItemMeta(slowfallMeta);
    gameSettingsMenu.setItem(0, slowfall);
    ItemStack invis = new ItemStack(Material.SPIDER_EYE, 1);
    ItemMeta invisMeta = invis.getItemMeta();
    invisMeta.setDisplayName("§fInvisibility");
    List<String> invisLore = new ArrayList<>();
    invisLore.add("§7Sets the settings around");
    invisLore.add("§7Invisibility in the game.");
    invisLore.add("§7");
    invisLore.add("§fInvisibility Information:");
    if (config.getBoolean("gameSettings.invis.enabled")) {
      invisLore.add("§f - §7Enabled: §a§nYes");
      if (config.getString("gameSettings.invis.given").equals("potion")) {
        invisLore.add("§f - §7Form: §a§nPotion");
      } else if (config.getString("gameSettings.invis.given").equals("auto")) {
        invisLore.add("§f - §7Form: §a§nAutomatic");
      } 
      invisLore.add("§f - §7Delay Before Giving: §a§n" + (config.getInt("gameSettings.invis.timeBeforeGive") / 60) + " minutes");
      invisLore.add("§f - §7Duration: §a§n" + (config.getInt("gameSettings.invis.duration") / 60) + " minutes");
      if (config.getBoolean("gameSettings.invis.removeOnDamageDone")) {
        invisLore.add("§f - §7Contact Removal: §a§nYes");
      } else {
        invisLore.add("§f - §7Contact Removal: §c§nNo");
      } 
    } else {
      invisLore.add("§f - §7Enabled: §c§nNo");
    } 
    invisLore.add("§7");
    invisLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nInvisibility Settings");
    invisMeta.setLore(invisLore);
    invis.setItemMeta(invisMeta);
    gameSettingsMenu.setItem(1, invis);
    ItemStack peace = new ItemStack(Material.ELYTRA, 1);
    ItemMeta peaceMeta = peace.getItemMeta();
    peaceMeta.setDisplayName("§fPeace");
    List<String> peaceLore = new ArrayList<>();
    peaceLore.add("§7Sets the settings around");
    peaceLore.add("§7Peace Mode in the game.");
    peaceLore.add("§7");
    peaceLore.add("§fPeace Information:");
    if (config.getBoolean("gameSettings.peaceful.enabled")) {
      peaceLore.add("§f - §7Enabled: §a§nYes");
      peaceLore.add("§f - §7Delay Before Removing: §a§n" + (config.getInt("gameSettings.peaceful.delayToRemove") / 60) + " minutes");
    } else {
      peaceLore.add("§f - §7Enabled: §c§nNo");
    } 
    peaceLore.add("§7");
    peaceLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nPeace Settings");
    peaceMeta.setLore(peaceLore);
    peace.setItemMeta(peaceMeta);
    gameSettingsMenu.setItem(2, peace);
    ItemStack explode = new ItemStack(Material.TNT, 1);
    ItemMeta explodeMeta = explode.getItemMeta();
    explodeMeta.setDisplayName("§fExplode Blacklist");
    List<String> explodeLore = new ArrayList<>();
    explodeLore.add("§7Sets which blocks can be");
    explodeLore.add("§7destroyed with explosions.");
    explodeLore.add("§7");
    explodeLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nExplosion Settings");
    explodeMeta.setLore(explodeLore);
    explode.setItemMeta(explodeMeta);
    gameSettingsMenu.setItem(3, explode);
    ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE, 1);
    ItemMeta craftingMeta = crafting.getItemMeta();
    craftingMeta.setDisplayName("§fCrafting Table Functions");
    List<String> craftingLore = new ArrayList<>();
    craftingLore.add("§7Sets whether Crafting Tables");
    craftingLore.add("§7can be used to Craft items.");
    craftingLore.add("§7");
    craftingLore.add("§fCrafting Information:");
    if (config.getBoolean("gameSettings.craftingTablesEnabled")) {
      craftingLore.add("§f - §7Functions: §a§nTrue");
    } else {
      craftingLore.add("§f - §7Functions: §c§nFalse");
    } 
    craftingLore.add("§7");
    craftingLore.add("§f§l<!>§r §fClick to §nToggle§r §fthe §nCrafting Table Functions");
    craftingMeta.setLore(craftingLore);
    crafting.setItemMeta(craftingMeta);
    gameSettingsMenu.setItem(4, crafting);
    ItemStack beacon = new ItemStack(Material.BEACON, 1);
    ItemMeta beaconMeta = beacon.getItemMeta();
    beaconMeta.setDisplayName("§fBeacon Functions");
    List<String> beaconLore = new ArrayList<>();
    beaconLore.add("§7Sets different functions");
    beaconLore.add("§7relating to the beacon.");
    beaconLore.add("§7");
    beaconLore.add("§fBeacon Information:");
    if (config.getBoolean("gameSettings.beacon.crafting")) {
      beaconLore.add("§f - §7Crafting @ Beacon: §a§nTrue");
    } else {
      beaconLore.add("§f - §7Crafting @ Beacon: §c§nFalse");
    } 
    if (config.getBoolean("gameSettings.beacon.respawn")) {
      beaconLore.add("§f - §7Respawn @ Beacon: §a§nTrue");
    } else {
      beaconLore.add("§f - §7Respawn @ Beacon: §c§nFalse");
    } 
    if (config.getBoolean("gameSettings.beacon.leaderOnlyPlace")) {
      beaconLore.add("§f - §7Leader-Only Placing: §a§nTrue");
    } else {
      beaconLore.add("§f - §7Leader-Only Placing: §c§nFalse");
    } 
    beaconLore.add("§7");
    beaconLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nBeacon Settings");
    beaconMeta.setLore(beaconLore);
    beacon.setItemMeta(beaconMeta);
    gameSettingsMenu.setItem(5, beacon);
    ItemStack death = new ItemStack(Material.SKELETON_SKULL, 1);
    ItemMeta deathMeta = death.getItemMeta();
    deathMeta.setDisplayName("§fDeath");
    List<String> deathLore = new ArrayList<>();
    deathLore.add("§7Sets different functions");
    deathLore.add("§7relating to player deaths.");
    deathLore.add("§7");
    deathLore.add("§fDeath Information:");
    if (config.getBoolean("gameSettings.death.keepXPOnDeath")) {
      deathLore.add("§f - §7Keep XP: §a§nTrue§8 [§7" + (int)(config.getDouble("gameSettings.death.chanceXPSave") * 100.0D) + "%§8]");
    } else {
      deathLore.add("§f - §7Keep XP: §c§nFalse");
    } 
    if (config.getBoolean("gameSettings.death.keepItemsOnDeath")) {
      deathLore.add("§f - §7Keep Items: §a§nTrue§8 [§7" + (int)(config.getDouble("gameSettings.death.chanceItemSave") * 100.0D) + "%§8]");
    } else {
      deathLore.add("§f - §7Keep Items: §c§nFalse");
    } 
    if (config.getBoolean("gameSettings.death.autokick")) {
      deathLore.add("§f - §7Auto Kick: §a§nTrue");
    } else {
      deathLore.add("§f - §7Auto Kick: §c§nFalse");
    } 
    if (config.getBoolean("gameSettings.death.autounwhitelist")) {
      deathLore.add("§f - §7Auto Unwhitelist: §a§nTrue");
    } else {
      deathLore.add("§f - §7Auto Unwhitelist: §c§nFalse");
    } 
    deathLore.add("§7");
    deathLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nDeath Settings");
    deathMeta.setLore(deathLore);
    death.setItemMeta(deathMeta);
    gameSettingsMenu.setItem(6, death);
    ItemStack bossbar = new ItemStack(Material.DRAGON_HEAD, 1);
    ItemMeta bossbarMeta = bossbar.getItemMeta();
    bossbarMeta.setDisplayName("§fBoss Bar");
    List<String> bossbarLore = new ArrayList<>();
    bossbarLore.add("§7Set whether the boss bar is");
    bossbarLore.add("§7displayed when starting a game.");
    bossbarLore.add("§7");
    if (config.getBoolean("gameSettings.bossbar.enabled")) {
      bossbarLore.add("§f - §7Enabled: §a§nTrue");
      if (config.getBoolean("gameSettings.bossbar.allowDisableForAll")) {
        bossbarLore.add("§f - §7Disable for All: §a§nTrue");
      } else {
        bossbarLore.add("§f - §7Disable for All: §c§nFalse");
      } 
    } else {
      bossbarLore.add("§f - §7Enabled: §c§nFalse");
    } 
    bossbarLore.add("§7");
    bossbarLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nBoss Bar Settings");
    bossbarMeta.setLore(bossbarLore);
    bossbar.setItemMeta(bossbarMeta);
    gameSettingsMenu.setItem(7, bossbar);
    return gameSettingsMenu;
  }
  
  public static Inventory inventoryCustomGamemodes(FileConfiguration config) {
    Inventory customGamemodeMenu = Bukkit.getServer().createInventory(null, 9, "Custom Gamemodes");
    ItemStack timerDeathSwap = new ItemStack(Material.SKELETON_SKULL, 1);
    ItemMeta timerDeathSwapMeta = timerDeathSwap.getItemMeta();
    timerDeathSwapMeta.setDisplayName("§fTimer Death Swap");
    List<String> timerDeathSwapLore = new ArrayList<>();
    timerDeathSwapLore.add("§7Players will Teleport to another");
    timerDeathSwapLore.add("§7Player every x amount of Minutes.");
    timerDeathSwapLore.add("§7");
    timerDeathSwapLore.add("§fGamemode Information:");
    if (config.getBoolean("timerdeathswap.enabled")) {
      timerDeathSwapLore.add("§f - §7Enabled: §a§nYes");
      timerDeathSwapLore.add("§f - §7Time Between Swaps: §a§n" + (config.getInt("timerdeathswap.timeBetweenSwaps") / 60) + " minutes");
      if (config.getBoolean("timerdeathswap.swapDuringPeace")) {
        timerDeathSwapLore.add("§f - §7Swap in Peace Mode: §a§nYes");
      } else {
        timerDeathSwapLore.add("§f - §7Swap in Peace Mode: §c§nNo");
      } 
      if (config.getBoolean("timerdeathswap.notifyOfSwapper")) {
        timerDeathSwapLore.add("§f - §7Display who a Player Swaps with: §a§nYes");
      } else {
        timerDeathSwapLore.add("§f - §7Display who a Player Swaps with: §c§nNo");
      } 
      if (config.getBoolean("timerdeathswap.fixedSwapTime")) {
        timerDeathSwapLore.add("§f - §7Death Swap is: §a§nFixed Swap Time");
      } else {
        timerDeathSwapLore.add("§f - §7Death Swap is: §c§nRandom");
      } 
    } else {
      timerDeathSwapLore.add("§f - §7Enabled: §c§nNo");
    } 
    timerDeathSwapLore.add("§7");
    timerDeathSwapLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nTimer Death Swap");
    timerDeathSwapMeta.setLore(timerDeathSwapLore);
    timerDeathSwap.setItemMeta(timerDeathSwapMeta);
    customGamemodeMenu.setItem(0, timerDeathSwap);
    return customGamemodeMenu;
  }
}
