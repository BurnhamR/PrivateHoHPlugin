package com.HideOrHunt.config;

import com.HideOrHunt.HideOrHunt;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerRegistery {
  private HideOrHunt plugin;
  
  public PlayerRegistery(HideOrHunt plugin) {
    this.plugin = plugin;
  }
  
  public static void createUsersFolder(HideOrHunt plugin) {
    Bukkit.getLogger().info("Hide or Hunt > Checking for Players Folder.");
    File usersFolder = new File("plugins/PrivateHideOrHunt/", "userdata");
    if (!usersFolder.exists()) {
      usersFolder.mkdirs();
      Bukkit.getLogger().info("Hide or Hunt > Creating Users Folder.");
    } 
  }
  
  public static void updatePlayerFile(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    if (!playerFile.exists()) {
      try {
        playerFile.createNewFile();
        setDefaultValues(plugin, player);
        Bukkit.getLogger().info("Hide or Hunt > Creating Player Data file for " + uuid.toString() + ".");
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } else {
      setDefaultValues(plugin, player);
    } 
  }
  
  public static String getNickname(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    return yamlConfiguration.getString("names.nickname");
  }
  
  public static boolean isOnTeam(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    return yamlConfiguration.getBoolean("team.activeOnTeam");
  }
  
  public static String getPlayerTeamUUID(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    return yamlConfiguration.getString("team.uuid");
  }
  
  public static boolean isAlreadyInvited(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    return yamlConfiguration.getBoolean("team.pendingInvite");
  }
  
  public static String getPlayerInvitingTeamUUID(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    return yamlConfiguration.getString("team.pendingTeamInviteUUID");
  }
  
  public static void updatePlayerInvitingTeam(HideOrHunt plugin, Player player, String teamUUID, boolean newInvite) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    yamlConfiguration.set("team.pendingInvite", Boolean.valueOf(newInvite));
    yamlConfiguration.set("team.pendingTeamInviteUUID", teamUUID);
    try {
      yamlConfiguration.save(playerFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean getPlayerBossBar(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    return yamlConfiguration.getBoolean("showbossbar");
  }
  
  public static void setPlayerBossBar(HideOrHunt plugin, Player player, boolean canShow) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    yamlConfiguration.set("showbossbar", Boolean.valueOf(canShow));
    try {
      yamlConfiguration.save(playerFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void updatePlayerTeam(HideOrHunt plugin, Player player, String teamUUID, boolean newTeam) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    yamlConfiguration.set("team.activeOnTeam", Boolean.valueOf(newTeam));
    yamlConfiguration.set("team.uuid", teamUUID);
    try {
      yamlConfiguration.save(playerFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void setSpawnPoint(HideOrHunt plugin, Player player, Location newSpawnPoint) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    yamlConfiguration.set("spawnpoint.world", newSpawnPoint.getWorld().getName());
    yamlConfiguration.set("spawnpoint.x", Double.valueOf(newSpawnPoint.getX()));
    yamlConfiguration.set("spawnpoint.y", Double.valueOf(newSpawnPoint.getY()));
    yamlConfiguration.set("spawnpoint.z", Double.valueOf(newSpawnPoint.getZ()));
    try {
      yamlConfiguration.save(playerFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static Location getSpawnPoint(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    updatePlayerFile(plugin, player);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    Location respawn = new Location(Bukkit.getWorld(yamlConfiguration.getString("spawnpoint.world")), yamlConfiguration.getDouble("spawnpoint.x"), yamlConfiguration.getDouble("spawnpoint.y"), yamlConfiguration.getDouble("spawnpoint.z"));
    return respawn;
  }
  
  public static void setDefaultValues(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    try {
      if (!yamlConfiguration.isSet("team")) {
        yamlConfiguration.set("team.activeOnTeam", Boolean.valueOf(false));
        yamlConfiguration.set("team.uuid", "-1");
        yamlConfiguration.set("team.pendingInvite", Boolean.valueOf(false));
        yamlConfiguration.set("team.pendingTeamInviteUUID", "-1");
        yamlConfiguration.save(playerFile);
      } 
      if (!yamlConfiguration.isSet("spawnpoint")) {
        yamlConfiguration.set("spawnpoint.world", player.getWorld().getName());
        yamlConfiguration.set("spawnpoint.x", Double.valueOf(player.getWorld().getSpawnLocation().getX()));
        yamlConfiguration.set("spawnpoint.y", Double.valueOf(player.getWorld().getSpawnLocation().getY()));
        yamlConfiguration.set("spawnpoint.z", Double.valueOf(player.getWorld().getSpawnLocation().getZ()));
        yamlConfiguration.save(playerFile);
      } 
      if (!yamlConfiguration.isSet("names")) {
        yamlConfiguration.set("names.default", player.getName());
        yamlConfiguration.set("names.nickname", player.getName());
        yamlConfiguration.save(playerFile);
      } 
      if (!yamlConfiguration.isSet("uuid")) {
        yamlConfiguration.set("uuid", uuid.toString());
        yamlConfiguration.save(playerFile);
      } 
      if (!yamlConfiguration.isSet("showbossbar")) {
        yamlConfiguration.set("showbossbar", Boolean.valueOf(true));
        yamlConfiguration.save(playerFile);
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void resetDefaultValues(HideOrHunt plugin, Player player) {
    UUID uuid = player.getUniqueId();
    File playerFile = new File("plugins/PrivateHideOrHunt/userdata/", uuid + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(playerFile);
    try {
      yamlConfiguration.set("team.activeOnTeam", Boolean.valueOf(false));
      yamlConfiguration.set("team.uuid", "-1");
      yamlConfiguration.set("team.pendingInvite", Boolean.valueOf(false));
      yamlConfiguration.set("team.pendingTeamInviteUUID", "-1");
      yamlConfiguration.set("spawnpoint.world", player.getWorld().getName());
      yamlConfiguration.set("spawnpoint.x", Double.valueOf(player.getWorld().getSpawnLocation().getX()));
      yamlConfiguration.set("spawnpoint.y", Double.valueOf(player.getWorld().getSpawnLocation().getY()));
      yamlConfiguration.set("spawnpoint.z", Double.valueOf(player.getWorld().getSpawnLocation().getZ()));
      yamlConfiguration.set("names.default", player.getName());
      yamlConfiguration.set("names.nickname", player.getName());
      yamlConfiguration.set("uuid", uuid.toString());
      yamlConfiguration.set("showbossbar", Boolean.valueOf(true));
      yamlConfiguration.save(playerFile);
      Bukkit.getLogger().info("Hide or Hunt > Reseting Player Data file for " + uuid.toString() + ".");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
