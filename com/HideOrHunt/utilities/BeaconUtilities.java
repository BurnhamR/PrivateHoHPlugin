package com.HideOrHunt.utilities;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class BeaconUtilities {
  public static ItemStack giveBeacon(FileConfiguration config, Player player) {
    ItemStack beacon = new ItemStack(Material.BEACON, 1);
    String teamName = TeamRegistery.getTeamName(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player));
    String teamUUID = PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player);
    ItemMeta beaconMeta = beacon.getItemMeta();
    beaconMeta.setDisplayName("§b" + teamName + "'s Beacon");
    List<String> beaconLore = new ArrayList<>();
    beaconLore.add("§7Place this beacon down to interact");
    beaconLore.add("§7with it's many functions.");
    beaconLore.add("§7");
    beaconLore.add("§fBeacon Information:");
    beaconLore.add("§f - §7Team: §e" + teamName);
    beaconLore.add("§7");
    beaconLore.add("§fFunctionality Information:");
    if (config.getBoolean("gameSettings.beacon.crafting"))
      beaconLore.add("§f - §7Acts as a Crafting Table"); 
    if (config.getBoolean("gameSettings.beacon.respawn"))
      beaconLore.add("§f - §7Acts as a Respawn Point"); 
    beaconLore.add("§7");
    beaconLore.add("§c§l<!>§c This can only be placed once.");
    beaconMeta.setLore(beaconLore);
    NamespacedKey teamBeaconUUID = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "teambeaconuuid");
    NamespacedKey isCustomBeacon = new NamespacedKey((Plugin)HideOrHunt.getInstance(), "iscustombeacon");
    beaconMeta.getPersistentDataContainer().set(teamBeaconUUID, PersistentDataType.STRING, teamUUID);
    beaconMeta.getPersistentDataContainer().set(isCustomBeacon, PersistentDataType.STRING, "true");
    beacon.setItemMeta(beaconMeta);
    return beacon;
  }
  
  public static boolean searchBeacon(HideOrHunt plugin, Player player, Location beaconLocation) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "beacons.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    ConfigurationSection beaconSection = yamlConfiguration.getConfigurationSection("beacons");
    for (String uuid : beaconSection.getKeys(false)) {
      if (yamlConfiguration.getString("beacons." + uuid + ".world").equals(beaconLocation.getWorld().getName()) && 
        yamlConfiguration.getInt("beacons." + uuid + ".x") == beaconLocation.getBlockX() && 
        yamlConfiguration.getInt("beacons." + uuid + ".y") == beaconLocation.getBlockY() && 
        yamlConfiguration.getInt("beacons." + uuid + ".z") == beaconLocation.getBlockZ())
        return true; 
    } 
    return false;
  }
  
  public static String getBeacon(HideOrHunt plugin, Player player, Location beaconLocation) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "beacons.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    ConfigurationSection beaconSection = yamlConfiguration.getConfigurationSection("beacons");
    for (String uuid : beaconSection.getKeys(false)) {
      if (yamlConfiguration.getString("beacons." + uuid + ".world").equals(beaconLocation.getWorld().getName()) && 
        yamlConfiguration.getInt("beacons." + uuid + ".x") == beaconLocation.getBlockX() && 
        yamlConfiguration.getInt("beacons." + uuid + ".y") == beaconLocation.getBlockY() && 
        yamlConfiguration.getInt("beacons." + uuid + ".z") == beaconLocation.getBlockZ())
        return uuid; 
    } 
    return "-1";
  }
  
  public static void destroyBeacon(HideOrHunt plugin, Player player, String uuid, Location beaconLocation) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "beacons.yml");
    YamlConfiguration yamlConfiguration1 = YamlConfiguration.loadConfiguration(configFile);
    File gameConfigFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration2 = YamlConfiguration.loadConfiguration(gameConfigFile);
    if (player.isOp() && !PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
      Bukkit.getLogger().info("Hide or Hunt > Destroying a Beacon - " + uuid);
      yamlConfiguration1.set("beacons." + uuid + ".status", "destroyed");
      try {
        yamlConfiguration1.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [Beacon].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      TeamUtilities.sendTeamSoundUUID(uuid, Sound.BLOCK_ANVIL_DESTROY);
      TeamUtilities.sendTeamCommandUUID(uuid, "title %player% title {\"text\":\"Beacon Destroyed\",\"color\":\"red\",\"bold\":true}");
      TeamUtilities.updateTeamLastLife(uuid);
      TeamRegistery.updateTeamBeaconStatus(HideOrHunt.getInstance(), uuid);
      Bukkit.broadcastMessage("§a§n" + TeamRegistery.getTeamName(HideOrHunt.getInstance(), uuid) + "'s Beacon§a was Destroyed by §n" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + "§a.");
      beaconLocation.getBlock().setType(Material.AIR);
    } else {
      if (!PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
        player.sendMessage("§cYou must be on a Team to Eliminate another Team!");
        return;
      } 
      if (PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player).equals(uuid)) {
        player.sendMessage("§cYou cannot Eliminate your own Team!");
        return;
      } 
      if (yamlConfiguration2.getBoolean("gameSettings.peaceful.enabled")) {
        player.sendMessage("§cYou cannot Eliminate a Team during the Peace Mode!");
        return;
      } 
      Bukkit.getLogger().info("Hide or Hunt > Destroying a Beacon - " + uuid);
      yamlConfiguration1.set("beacons." + uuid + ".status", "destroyed");
      try {
        yamlConfiguration1.save(configFile);
        Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [Beacon].");
      } catch (IOException e) {
        e.printStackTrace();
      } 
      TeamUtilities.sendTeamSoundUUID(uuid, Sound.BLOCK_ANVIL_DESTROY);
      TeamUtilities.sendTeamCommandUUID(uuid, "title %player% title {\"text\":\"Beacon Destroyed\",\"color\":\"red\",\"bold\":true}");
      TeamUtilities.updateTeamLastLife(uuid);
      TeamRegistery.updateTeamBeaconStatus(HideOrHunt.getInstance(), uuid);
      Bukkit.broadcastMessage("§a§n" + TeamRegistery.getTeamName(HideOrHunt.getInstance(), uuid) + "'s Beacon§a was Destroyed by §n" + PlayerRegistery.getNickname(HideOrHunt.getInstance(), player) + "§a.");
      String playerUUID = player.getUniqueId().toString();
      TeamRegistery.updatePlayerBeaconsDestroyed(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player), playerUUID, TeamRegistery.getPlayerBeaconsDestroyed(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player), playerUUID) + 1);
      TeamRegistery.updateTeamBeaconsDestroyed(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player), TeamRegistery.getTeamBeaconsDestroyed(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) + 1);
      beaconLocation.getBlock().setType(Material.AIR);
    } 
  }
}
