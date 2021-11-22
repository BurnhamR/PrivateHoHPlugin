package com.HideOrHunt.config;

import com.HideOrHunt.HideOrHunt;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TeamRegistery {
  private HideOrHunt plugin;
  
  public TeamRegistery(HideOrHunt plugin) {
    this.plugin = plugin;
  }
  
  public static void createTeamsFolder(HideOrHunt plugin) {
    Bukkit.getLogger().info("Hide or Hunt > Checking for Teams Folder.");
    File teamsFolder = new File("plugins/PrivateHideOrHunt/", "teamdata");
    if (!teamsFolder.exists()) {
      teamsFolder.mkdirs();
      Bukkit.getLogger().info("Hide or Hunt > Creating Teams Folder.");
    } 
  }
  
  public static void registerTeam(HideOrHunt plugin, Player player, String teamName) {
    String teamID = UUID.randomUUID().toString().replace("-", "");
    Bukkit.getLogger().info("Hide or Hunt > Registering a New Team - " + teamID);
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(teamID) + ".yml");
    if (!teamFile.exists())
      try {
        teamFile.createNewFile();
        setDefaultTeamValues(plugin, teamID, player, teamName);
        Bukkit.getLogger().info("Hide or Hunt > Creating " + teamID + ".yml File.");
      } catch (IOException e) {
        Bukkit.getLogger().info("Hide or Hunt > Error! " + teamID + ".yml File Missing.");
      }  
  }
  
  public static void removeTeam(HideOrHunt plugin, Player player, String teamUUID) {
    Bukkit.getLogger().info("Hide or Hunt > Removing a Team - " + teamUUID);
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(teamUUID) + ".yml");
    Bukkit.getLogger().info("Hide or Hunt > Removing " + teamUUID + ".yml File.");
    teamFile.delete();
    PlayerRegistery.updatePlayerTeam(plugin, player, "-1", false);
    ConfigRegistery.updateTeamCount(plugin, -1);
  }
  
  public static void setDefaultTeamValues(HideOrHunt plugin, String uuid, Player player, String teamName) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    try {
      if (!yamlConfiguration.isSet("information")) {
        yamlConfiguration.set("information.name", teamName);
        yamlConfiguration.set("information.uuid", uuid);
        yamlConfiguration.set("information.size", Integer.valueOf(0));
        yamlConfiguration.set("information.beaconStatus", "pending");
        yamlConfiguration.set("information.teamEliminated", Boolean.valueOf(false));
        yamlConfiguration.set("information.totalBeaconsDestroyed", Integer.valueOf(0));
        yamlConfiguration.set("information.totalFinalKills", Integer.valueOf(0));
        yamlConfiguration.set("information.totalGeneralKills", Integer.valueOf(0));
        yamlConfiguration.save(teamFile);
      } 
      if (!yamlConfiguration.isSet("players")) {
        yamlConfiguration.set("players", "");
        yamlConfiguration.save(teamFile);
      } 
      registerPlayer(plugin, player, uuid, true);
    } catch (IOException e) {
      e.printStackTrace();
    } 
    ConfigRegistery.updateTeamCount(plugin, 1);
  }
  
  public static void registerPlayer(HideOrHunt plugin, Player player, String uuid, boolean isLeader) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    try {
      int currentTeamSize = yamlConfiguration.getInt("information.size");
      yamlConfiguration.set("information.size", Integer.valueOf(currentTeamSize + 1));
      yamlConfiguration.save(teamFile);
      if (!yamlConfiguration.isSet("players." + player.getUniqueId().toString())) {
        yamlConfiguration.set("players." + player.getUniqueId().toString() + ".leader", Boolean.valueOf(isLeader));
        yamlConfiguration.set("players." + player.getUniqueId().toString() + ".lastLife", Boolean.valueOf(false));
        yamlConfiguration.set("players." + player.getUniqueId().toString() + ".eliminated", Boolean.valueOf(false));
        yamlConfiguration.set("players." + player.getUniqueId().toString() + ".totalBeaconsDestroyed", Integer.valueOf(0));
        yamlConfiguration.set("players." + player.getUniqueId().toString() + ".totalFinalKills", Integer.valueOf(0));
        yamlConfiguration.set("players." + player.getUniqueId().toString() + ".totalGeneralKills", Integer.valueOf(0));
        yamlConfiguration.save(teamFile);
      } 
      PlayerRegistery.updatePlayerTeam(plugin, player, uuid, true);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void removePlayer(HideOrHunt plugin, Player player, String uuid) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    try {
      int currentTeamSize = yamlConfiguration.getInt("information.size");
      yamlConfiguration.set("information.size", Integer.valueOf(currentTeamSize - 1));
      yamlConfiguration.set("players." + player.getUniqueId().toString(), null);
      yamlConfiguration.save(teamFile);
      PlayerRegistery.updatePlayerTeam(plugin, player, "-1", false);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean isTeamLeader(HideOrHunt plugin, String uuid, Player player) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getBoolean("players." + player.getUniqueId().toString() + ".leader");
  }
  
  public static boolean isPlayerOnTeam(HideOrHunt plugin, String uuid, Player player) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    if (yamlConfiguration.isSet("players." + player.getUniqueId().toString()))
      return true; 
    return false;
  }
  
  public static int getTeamPlayerCount(HideOrHunt plugin, String uuid) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getInt("information.size");
  }
  
  public static String getTeamName(HideOrHunt plugin, String uuid) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getString("information.name");
  }
  
  public static void updateTeamName(HideOrHunt plugin, String uuid, String newName) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("information.name", newName);
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean getPlayerLastLife(HideOrHunt plugin, String uuid, String playerUUID) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getBoolean("players." + playerUUID + ".lastLife");
  }
  
  public static void updatePlayerLastLife(HideOrHunt plugin, String uuid, String playerUUID, boolean isOnLastLife) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("players." + playerUUID + ".lastLife", Boolean.valueOf(isOnLastLife));
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean getPlayerEliminated(HideOrHunt plugin, String uuid, String playerUUID) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getBoolean("players." + playerUUID + ".eliminated");
  }
  
  public static void updatePlayerEliminated(HideOrHunt plugin, String uuid, String playerUUID, boolean isEliminated) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("players." + playerUUID + ".eliminated", Boolean.valueOf(isEliminated));
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static boolean isTeamEliminated(HideOrHunt plugin, String uuid) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getBoolean("information.teamEliminated");
  }
  
  public static void updateTeamEliminated(HideOrHunt plugin, String uuid, boolean isElimiated) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("information.teamEliminated", Boolean.valueOf(isElimiated));
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static int getPlayerBeaconsDestroyed(HideOrHunt plugin, String uuid, String playerUUID) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getInt("players." + playerUUID + ".totalBeaconsDestroyed");
  }
  
  public static void updatePlayerBeaconsDestroyed(HideOrHunt plugin, String uuid, String playerUUID, int beaconsDestroyed) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("players." + playerUUID + ".totalBeaconsDestroyed", Integer.valueOf(beaconsDestroyed));
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static int getPlayerFinalKills(HideOrHunt plugin, String uuid, String playerUUID) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getInt("players." + playerUUID + ".totalFinalKills");
  }
  
  public static void updatePlayerFinalKills(HideOrHunt plugin, String uuid, String playerUUID, int finalKills) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("players." + playerUUID + ".totalFinalKills", Integer.valueOf(finalKills));
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static int getPlayerGeneralKills(HideOrHunt plugin, String uuid, String playerUUID) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getInt("players." + playerUUID + ".totalGeneralKills");
  }
  
  public static void updatePlayerGeneralKills(HideOrHunt plugin, String uuid, String playerUUID, int generalKills) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("players." + playerUUID + ".totalGeneralKills", Integer.valueOf(generalKills));
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static int getTeamBeaconsDestroyed(HideOrHunt plugin, String uuid) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getInt("information.totalBeaconsDestroyed");
  }
  
  public static void updateTeamBeaconsDestroyed(HideOrHunt plugin, String uuid, int beaconsDestroyed) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("information.totalBeaconsDestroyed", Integer.valueOf(beaconsDestroyed));
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static int getTeamFinalKills(HideOrHunt plugin, String uuid) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getInt("information.totalFinalKills");
  }
  
  public static void updateTeamFinalKills(HideOrHunt plugin, String uuid, int finalKills) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("information.totalFinalKills", Integer.valueOf(finalKills));
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static int getTeamGeneralKills(HideOrHunt plugin, String uuid) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getInt("information.totalGeneralKills");
  }
  
  public static void updateTeamGeneralKills(HideOrHunt plugin, String uuid, int generalKills) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    yamlConfiguration.set("information.totalGeneralKills", Integer.valueOf(generalKills));
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static String getTeamBeaconStatus(HideOrHunt plugin, String uuid) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    return yamlConfiguration.getString("information.beaconStatus");
  }
  
  public static void updateTeamBeaconStatus(HideOrHunt plugin, String uuid) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(uuid) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    if (yamlConfiguration.getString("information.beaconStatus").equals("pending")) {
      yamlConfiguration.set("information.beaconStatus", "placed");
    } else if (yamlConfiguration.getString("information.beaconStatus").equals("placed")) {
      yamlConfiguration.set("information.beaconStatus", "destroyed");
    } else if (yamlConfiguration.getString("information.beaconStatus").equals("destroyed")) {
      yamlConfiguration.set("information.beaconStatus", "error");
      for (Player oPlayer : Bukkit.getOnlinePlayers()) {
        if (oPlayer.isOp()) {
          oPlayer.sendMessage("Error with " + getTeamName(plugin, uuid) + " beacon place [" + uuid + "]");
          oPlayer.sendMessage("Screenshot and Report to Developer");
        } 
      } 
    } 
    try {
      yamlConfiguration.save(teamFile);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
