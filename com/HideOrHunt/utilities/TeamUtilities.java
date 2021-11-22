package com.HideOrHunt.utilities;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import java.io.File;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class TeamUtilities {
  public static boolean isTeamCreateValid(FileConfiguration config, Player player) {
    if (config.getInt("teamsSettings.currentTeamCount") == config.getInt("teamsSettings.maximumTeamCount")) {
      player.sendMessage("§cThe §nMaximum Amount§c of teams has been reached at " + config.getInt("teamsSettings.maximumTeamCount") + "!");
      return false;
    } 
    if (PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
      player.sendMessage("§cYou are already on a Team! Use §6§n/team leave§c to leave your Current Team!");
      return false;
    } 
    return true;
  }
  
  public static boolean isTeamNameValid(FileConfiguration config, String name, Player player) {
    if (config.getBoolean("gameSettings.active")) {
      player.sendMessage("§cThe Team Name cannot be Altered while the game is §nActive§c!");
      return false;
    } 
    if (!config.getBoolean("teamsSettings.canTeamNameContainNumber"))
      for (int k = 0; k < name.length(); k++) {
        if (Character.isDigit(name.charAt(k))) {
          player.sendMessage("§cThe Team Name cannot contain §nNumbers§c!");
          player.sendMessage("§cCommand Usage: §6/team create <name>");
          return false;
        } 
      }  
    int i;
    for (i = 0; i < name.length(); i++) {
      if (Character.isWhitespace(name.charAt(i))) {
        player.sendMessage("§cThe Team Name cannot contain §nSpaces§c!");
        player.sendMessage("§cCommand Usage: §6/team create <name>");
        return false;
      } 
    } 
    if (!config.getBoolean("teamsSettings.canTeamNameContainRandomCharacters"))
      for (i = 0; i < name.length(); i++) {
        if (!Character.isLetter(name.charAt(i))) {
          player.sendMessage("§cThe Team Name cannot contain §nRandom Characters§c!");
          player.sendMessage("§cCommand Usage: §6/team create <name>");
          return false;
        } 
      }  
    if (name.length() < config.getInt("teamsSettings.minTeamNameCharacters")) {
      player.sendMessage("§cThe Team Name cannot be shorter than §n" + config.getInt("teamsSettings.minTeamNameCharacters") + "§c characters!");
      player.sendMessage("§cCommand Usage: §6/team create <name>");
      return false;
    } 
    if (name.length() > config.getInt("teamsSettings.maxTeamNameCharacters")) {
      player.sendMessage("§cThe Team Name cannot be longer than §n" + config.getInt("teamsSettings.maxTeamNameCharacters") + "§c characters!");
      player.sendMessage("§cCommand Usage: §6/team create <name>");
      return false;
    } 
    byte b;
    int j;
    File[] arrayOfFile;
    for (j = (arrayOfFile = (new File("plugins/PrivateHideOrHunt/teamdata/")).listFiles()).length, b = 0; b < j; ) {
      File teamdata = arrayOfFile[b];
      if (teamdata.isFile()) {
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamdata);
        if (yamlConfiguration.getString("information.name").toLowerCase().equals(name.toLowerCase())) {
          player.sendMessage("§cThe Team Name is already taken§c!");
          player.sendMessage("§cCommand Usage: §6/team create <name>");
          return false;
        } 
      } 
      b++;
    } 
    return true;
  }
  
  public static boolean isTeamInviteValid(FileConfiguration config, Player player, Player target, String playerName) {
    if (config.getBoolean("gameSettings.active")) {
      player.sendMessage("§cYou cannot Invite players to the Team while the game is §nActive§c!");
      return false;
    } 
    if (target == null || !target.isOnline()) {
      player.sendMessage("§c§n\"" + playerName + "\"§c is not Valid or is not Online!");
      return false;
    } 
    if (player.getName().equals(playerName)) {
      player.sendMessage("§cYou can't invite yourself to your Team!");
      return false;
    } 
    if (PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), target)) {
      player.sendMessage("§c§n\"" + playerName + "\"§c is already on a Team! Ask them to use §6§n/team leave§c to leave their Current Team!");
      return false;
    } 
    if (PlayerRegistery.isAlreadyInvited(HideOrHunt.getInstance(), target)) {
      player.sendMessage("§c§n\"" + playerName + "\"§c is already invited to join §n" + TeamRegistery.getTeamName(HideOrHunt.getInstance(), PlayerRegistery.getPlayerInvitingTeamUUID(HideOrHunt.getInstance(), target)) + "§c! Ask them to use §6§n/team deny§c to deny their Current Team Invite Request!");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("SOLO")) {
      player.sendMessage("§cYour team is full! You can have up to §n1§c player.");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("DUO") && TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 2) {
      player.sendMessage("§cYour team is full! You can have up to §n2§c players.");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("TRIO") && TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 3) {
      player.sendMessage("§cYour team is full! You can have up to §n3§c players.");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("QUAD") && TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 4) {
      player.sendMessage("§cYour team is full! You can have up to §n4§c players.");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("SQUAD10") && TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 10) {
      player.sendMessage("§cYour team is full! You can have up to §n10§c players.");
      return false;
    } 
    return true;
  }
  
  public static boolean isFutureTeamStatusValid(FileConfiguration config, Player player) {
    if (config.getBoolean("gameSettings.active")) {
      player.sendMessage("§cYou cannot Join a Team while the game is §nActive§c!");
      return false;
    } 
    if (PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
      player.sendMessage("§cYou are already on a Team! Use §6§n/team leave§c to leave your Current Team!");
      return false;
    } 
    if (!PlayerRegistery.isAlreadyInvited(HideOrHunt.getInstance(), player)) {
      player.sendMessage("§cYou don't have a §nPending§c Invite!");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("SOLO")) {
      player.sendMessage("§cThe team is full! There can only be up to §n1§c player.");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("DUO") && TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 2) {
      player.sendMessage("§cThe team is full! There can only be up to §n2§c players.");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("TRIO") && TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 3) {
      player.sendMessage("§cThe team is full! There can only be up to §n3§c players.");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("QUAD") && TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 4) {
      player.sendMessage("§cThe team is full! There can only be up to §n4§c players.");
      return false;
    } 
    if (config.getString("teamsSettings.type").equals("SQUAD10") && TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 10) {
      player.sendMessage("§cThe team is full! There can only be up to §n10§c players.");
      return false;
    } 
    return true;
  }
  
  public static boolean isLeavingValid(FileConfiguration config, Player player) {
    if (config.getBoolean("gameSettings.active")) {
      player.sendMessage("§cYou cannot Leave a Team while the game is §nActive§c!");
      return false;
    } 
    if (!PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
      player.sendMessage("§cYou must be on a Team to Leave a Team!");
      return false;
    } 
    if (TeamRegistery.isTeamLeader(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player), player)) {
      player.sendMessage("§cYou can't Leave your Team as the Leader. You must Disband your Team when everyone has Left the Team!");
      if (TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 1) {
        player.sendMessage("§d§nTeam Help:");
        player.sendMessage("§6 - /team disband  §8- §fDisbands your current team");
      } 
      return false;
    } 
    if (TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) == 1) {
      player.sendMessage("§cYou can't Leave your Team as the last player. You must Disband the Team!");
      player.sendMessage("§d§nTeam Help:");
      player.sendMessage("§6 - /team disband  §8- §fDisbands your current team");
    } 
    return true;
  }
  
  public static boolean isDisbandValid(FileConfiguration config, Player player) {
    if (config.getBoolean("gameSettings.active")) {
      player.sendMessage("§cYou cannot Leave a Team while the game is §nActive§c!");
      return false;
    } 
    if (!PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), player)) {
      player.sendMessage("§cYou must be on a Team to Disband a Team!");
      return false;
    } 
    if (!TeamRegistery.isTeamLeader(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player), player)) {
      player.sendMessage("§cYou can't Disband your Team! Only a Leader can Disband their Team!");
      return false;
    } 
    if (TeamRegistery.getTeamPlayerCount(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) > 1) {
      player.sendMessage("§cYou can't Disband your Team. You can Only Disband your Team when all your Team Members Leave the Team.");
      player.sendMessage("§d§nTeam Help:");
      player.sendMessage("§6 - /team disband  §8- §fDisbands your current team");
      return false;
    } 
    return true;
  }
  
  public static void sendTeamMessagePlayer(Player player, String message) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), player)) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    ConfigurationSection playerSection = yamlConfiguration.getConfigurationSection("players");
    for (String uuid : playerSection.getKeys(false)) {
      Player oPlayer = Bukkit.getPlayer(UUID.fromString(uuid));
      if (oPlayer.isOnline())
        oPlayer.sendMessage(message); 
    } 
  }
  
  public static void sendTeamMessageUUID(String teamUUID, String message) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(teamUUID) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    ConfigurationSection playerSection = yamlConfiguration.getConfigurationSection("players");
    for (String uuid : playerSection.getKeys(false)) {
      Player oPlayer = Bukkit.getPlayer(UUID.fromString(uuid));
      if (oPlayer.isOnline())
        oPlayer.sendMessage(message); 
    } 
  }
  
  public static void sendTeamSoundUUID(String teamUUID, Sound soundToPlay) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(teamUUID) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    ConfigurationSection playerSection = yamlConfiguration.getConfigurationSection("players");
    for (String uuid : playerSection.getKeys(false)) {
      Player oPlayer = Bukkit.getPlayer(UUID.fromString(uuid));
      if (oPlayer != null && 
        oPlayer.isOnline())
        oPlayer.playSound(oPlayer.getLocation(), soundToPlay, 1.0F, 3.0F); 
    } 
  }
  
  public static void sendTeamCommandUUID(String teamUUID, String message) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(teamUUID) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    ConfigurationSection playerSection = yamlConfiguration.getConfigurationSection("players");
    for (String uuid : playerSection.getKeys(false)) {
      Player oPlayer = Bukkit.getPlayer(UUID.fromString(uuid));
      if (oPlayer != null && 
        oPlayer.isOnline())
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), message.replace("%player%", oPlayer.getName())); 
    } 
  }
  
  public static void spawnTeamFireworks(String teamUUID, int amount) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(teamUUID) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    ConfigurationSection playerSection = yamlConfiguration.getConfigurationSection("players");
    for (String uuid : playerSection.getKeys(false)) {
      Player oPlayer = Bukkit.getPlayer(UUID.fromString(uuid));
      if (oPlayer != null && 
        oPlayer.isOnline()) {
        Location loc = oPlayer.getLocation();
        Firework fw = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());
        fw.setFireworkMeta(fwm);
        fw.detonate();
        for (int i = 0; i < amount; i++) {
          Firework fw2 = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
          fw2.setFireworkMeta(fwm);
        } 
      } 
    } 
  }
  
  public static void updateTeamSpawnpoint(String teamUUID, Location newSpawn) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(teamUUID) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    ConfigurationSection playerSection = yamlConfiguration.getConfigurationSection("players");
    for (String uuid : playerSection.getKeys(false)) {
      Player oPlayer = Bukkit.getPlayer(UUID.fromString(uuid));
      if (oPlayer != null && 
        oPlayer.isOnline())
        PlayerRegistery.setSpawnPoint(HideOrHunt.getInstance(), oPlayer, newSpawn); 
    } 
  }
  
  public static void updateTeamLastLife(String teamUUID) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(teamUUID) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    ConfigurationSection playerSection = yamlConfiguration.getConfigurationSection("players");
    for (String uuid : playerSection.getKeys(false))
      TeamRegistery.updatePlayerLastLife(HideOrHunt.getInstance(), teamUUID, uuid, true); 
  }
  
  public static boolean arePlayersRemaining(String teamUUID) {
    File teamFile = new File("plugins/PrivateHideOrHunt/teamdata/", String.valueOf(teamUUID) + ".yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamFile);
    ConfigurationSection playerSection = yamlConfiguration.getConfigurationSection("players");
    for (String uuid : playerSection.getKeys(false)) {
      if (TeamRegistery.getPlayerEliminated(HideOrHunt.getInstance(), teamUUID, uuid))
        continue; 
      return true;
    } 
    return false;
  }
  
  public static int getTeamsRemaining() {
    int remaining = 0;
    byte b;
    int i;
    File[] arrayOfFile;
    for (i = (arrayOfFile = (new File("plugins/PrivateHideOrHunt/teamdata/")).listFiles()).length, b = 0; b < i; ) {
      File teamdata = arrayOfFile[b];
      if (teamdata.isFile()) {
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamdata);
        if (!yamlConfiguration.getBoolean("information.teamEliminated"))
          remaining++; 
      } 
      b++;
    } 
    return remaining;
  }
  
  public static String getLastTeamUUID() {
    byte b;
    int i;
    File[] arrayOfFile;
    for (i = (arrayOfFile = (new File("plugins/PrivateHideOrHunt/teamdata/")).listFiles()).length, b = 0; b < i; ) {
      File teamdata = arrayOfFile[b];
      if (teamdata.isFile()) {
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(teamdata);
        if (!yamlConfiguration.getBoolean("information.teamEliminated"))
          return teamdata.getName().replace(".yml", ""); 
      } 
      b++;
    } 
    return "-1";
  }
}
