package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameSettingsCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public GameSettingsCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("gamesettings").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    if (!player.isOp()) {
      player.sendMessage("§cYou cannot use this command!");
      return true;
    } 
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    Inventory menu = Bukkit.getServer().createInventory(null, 9, "Game Settings");
    ItemStack teamType = new ItemStack(Material.PLAYER_HEAD, 1);
    ItemMeta teamTypeMeta = teamType.getItemMeta();
    teamTypeMeta.setDisplayName("§eTeam Type");
    List<String> teamTypeLore = new ArrayList<>();
    teamTypeLore.add("§7Set the maximum amount of players");
    teamTypeLore.add("§7that a team will be able to hold.");
    teamTypeLore.add("§7");
    teamTypeLore.add("§fTeam Information:");
    teamTypeLore.add("§f - §7Team Type: §f§n" + yamlConfiguration.getString("teamsSettings.type"));
    if (yamlConfiguration.getString("teamsSettings.type").equals("SOLO")) {
      teamTypeLore.add("§f - §7Team Size: §f§n1");
      teamType.setAmount(1);
    } else if (yamlConfiguration.getString("teamsSettings.type").equals("DUO")) {
      teamTypeLore.add("§f - §7Team Size: §f§n2");
      teamType.setAmount(2);
    } else if (yamlConfiguration.getString("teamsSettings.type").equals("TRIO")) {
      teamTypeLore.add("§f - §7Team Size: §f§n3");
      teamType.setAmount(3);
    } else if (yamlConfiguration.getString("teamsSettings.type").equals("QUAD")) {
      teamTypeLore.add("§f - §7Team Size: §f§n4");
      teamType.setAmount(4);
    } else if (yamlConfiguration.getString("teamsSettings.type").equals("SQUAD10")) {
      teamTypeLore.add("§f - §7Team Size: §f§n10");
      teamType.setAmount(10);
    } 
    teamTypeLore.add("§7");
    teamTypeLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nTeam Size");
    teamTypeMeta.setLore(teamTypeLore);
    teamType.setItemMeta(teamTypeMeta);
    menu.setItem(0, teamType);
    ItemStack teamCount = new ItemStack(Material.WHITE_WOOL, 1);
    ItemMeta teamCountMeta = teamCount.getItemMeta();
    teamCountMeta.setDisplayName("§eTeam Count");
    List<String> teamCountLore = new ArrayList<>();
    teamCountLore.add("§7Set the maximum amount of teams that");
    teamCountLore.add("§7will be allowed to play to play in the game.");
    teamCountLore.add("§7");
    teamCountLore.add("§fMaximum Team Information:");
    teamCountLore.add("§f - §7Maximum Teams: §f§n" + yamlConfiguration.getInt("teamsSettings.maximumTeamCount"));
    if (yamlConfiguration.getInt("teamsSettings.maximumTeamCount") > 64) {
      teamCount.setAmount(64);
    } else if (yamlConfiguration.getInt("teamsSettings.maximumTeamCount") < 1) {
      teamCount.setAmount(1);
    } else {
      teamCount.setAmount(yamlConfiguration.getInt("teamsSettings.maximumTeamCount"));
    } 
    teamCountLore.add("§7");
    teamCountLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nMaximum Team Count");
    teamCountMeta.setLore(teamCountLore);
    teamCount.setItemMeta(teamCountMeta);
    menu.setItem(1, teamCount);
    ItemStack teamNameSettings = new ItemStack(Material.NAME_TAG, 1);
    ItemMeta teamNameSettingsMeta = teamNameSettings.getItemMeta();
    teamNameSettingsMeta.setDisplayName("§eTeam Name Settings");
    List<String> teamNameSettingsLore = new ArrayList<>();
    teamNameSettingsLore.add("§7A wide range of settings related");
    teamNameSettingsLore.add("§7to how players setup their team name.");
    teamNameSettingsLore.add("§7");
    teamNameSettingsLore.add("§fTeam Name Information:");
    teamNameSettingsLore.add("§f - §7Minimum Name Characters: §f§n" + yamlConfiguration.getInt("teamsSettings.minTeamNameCharacters"));
    teamNameSettingsLore.add("§f - §7Maximum Name Characters: §f§n" + yamlConfiguration.getInt("teamsSettings.maxTeamNameCharacters"));
    if (yamlConfiguration.getBoolean("teamsSettings.canTeamNameContainNumber")) {
      teamNameSettingsLore.add("§f - §7Numbers: §a§nAllowed");
    } else {
      teamNameSettingsLore.add("§f - §7Numbers: §c§nDenied");
    } 
    if (yamlConfiguration.getBoolean("teamsSettings.canTeamNameContainRandomCharacters")) {
      teamNameSettingsLore.add("§f - §7Random Characters: §a§nAllowed");
    } else {
      teamNameSettingsLore.add("§f - §7Random Characters: §c§nDenied");
    } 
    teamNameSettingsLore.add("§f §f - §7Characters include: §f!, ?, @, #, %, Brackets, <>");
    teamNameSettingsLore.add("§7");
    teamNameSettingsLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nTeam Name Settings");
    teamNameSettingsMeta.setLore(teamNameSettingsLore);
    teamNameSettings.setItemMeta(teamNameSettingsMeta);
    menu.setItem(2, teamNameSettings);
    ItemStack mobSettings = new ItemStack(Material.EGG, 1);
    ItemMeta mobSettingsMeta = mobSettings.getItemMeta();
    mobSettingsMeta.setDisplayName("§cMob Settings");
    List<String> mobSettingsLore = new ArrayList<>();
    mobSettingsLore.add("§7A wide range of settings related");
    mobSettingsLore.add("§7to interactions with mobs.");
    mobSettingsLore.add("§7");
    mobSettingsLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nMob Settings");
    mobSettingsMeta.setLore(mobSettingsLore);
    mobSettings.setItemMeta(mobSettingsMeta);
    menu.setItem(3, mobSettings);
    ItemStack serverSettings = new ItemStack(Material.NETHER_STAR, 1);
    ItemMeta serverSettingsMeta = serverSettings.getItemMeta();
    serverSettingsMeta.setDisplayName("§bServer Settings");
    List<String> serverSettingsLore = new ArrayList<>();
    serverSettingsLore.add("§7A wide range of settings related");
    serverSettingsLore.add("§7to the server & worlds.");
    serverSettingsLore.add("§7");
    serverSettingsLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nServer Settings");
    serverSettingsMeta.setLore(serverSettingsLore);
    serverSettings.setItemMeta(serverSettingsMeta);
    menu.setItem(4, serverSettings);
    ItemStack gameKits = new ItemStack(Material.CHEST, 1);
    ItemMeta gameKitsMeta = gameKits.getItemMeta();
    gameKitsMeta.setDisplayName("§dGame Kits");
    List<String> gameKitsLore = new ArrayList<>();
    gameKitsLore.add("§7A wide range of settings related");
    gameKitsLore.add("§7to the different game kits.");
    gameKitsLore.add("§7");
    gameKitsLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nGame Kits");
    gameKitsMeta.setLore(gameKitsLore);
    gameKits.setItemMeta(gameKitsMeta);
    menu.setItem(5, gameKits);
    ItemStack gameSettings = new ItemStack(Material.BOOK, 1);
    ItemMeta gameSettingsMeta = gameSettings.getItemMeta();
    gameSettingsMeta.setDisplayName("§dGame Settings");
    List<String> gameSettingsLore = new ArrayList<>();
    gameSettingsLore.add("§7A wide range of settings related");
    gameSettingsLore.add("§7to the different gamemodes.");
    gameSettingsLore.add("§7");
    gameSettingsLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nGame Settings");
    gameSettingsMeta.setLore(gameSettingsLore);
    gameSettings.setItemMeta(gameSettingsMeta);
    menu.setItem(6, gameSettings);
    ItemStack customGamemodes = new ItemStack(Material.MAP, 1);
    ItemMeta customGamemodesMeta = customGamemodes.getItemMeta();
    customGamemodesMeta.setDisplayName("§aCustom Gamemodes");
    List<String> customGamemodesLore = new ArrayList<>();
    customGamemodesLore.add("§7A wide range of settings related");
    customGamemodesLore.add("§7to the custom gamemodes.");
    customGamemodesLore.add("§7");
    customGamemodesLore.add("§f§l<!>§r §fClick to §nEdit§r §fthe §nCustom Gamemodes");
    customGamemodesMeta.setLore(customGamemodesLore);
    customGamemodes.setItemMeta(customGamemodesMeta);
    menu.setItem(7, customGamemodes);
    player.openInventory(menu);
    return true;
  }
}
