package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.ConfigRegistery;
import com.HideOrHunt.utilities.Tab;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ResetDataCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public ResetDataCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("resetdata").setExecutor(this);
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
    if (args.length <= 0 || args.length > 1) {
      player.sendMessage("§cOptions for /ResetData");
      player.sendMessage("§c/resetdata <password>");
      return true;
    } 
    if (args[0].equals("HideOrHunt1")) {
      deleteHOHPlayerFiles(this.plugin);
      player.sendMessage("§aHoH Player Data was §nReset§a!");
      deleteHOHTeamFiles(this.plugin);
      player.sendMessage("§aHoH Team Data was §nReset§a!");
      deleteHOHBeaconFiles(this.plugin);
      player.sendMessage("§aHoH Beacon Data was §nReset§a!");
      deleteHOHDataFiles(this.plugin);
      player.sendMessage("§aHoH Event Data was §nReset§a!");
      resetStatus(this.plugin);
      player.sendMessage("§aHoH Game Data was §nReset§a!");
      Bukkit.getScheduler().cancelTasks((Plugin)HideOrHunt.getInstance());
      Tab.runTab();
      for (Player oPlayer : Bukkit.getOnlinePlayers()) {
        BossBar header = HideOrHunt.getBossBar();
        for (Player listedPlayer : header.getPlayers()) {
          if (listedPlayer.getUniqueId().toString().equals(oPlayer.getUniqueId().toString()))
            header.removePlayer(oPlayer); 
        } 
      } 
    } else {
      player.sendMessage("§c§n\"" + args[0] + "\"§c is not the Password!");
      return true;
    } 
    return true;
  }
  
  public static void deleteHOHPlayerFiles(HideOrHunt plugin) {
    byte b;
    int i;
    File[] arrayOfFile;
    for (i = (arrayOfFile = (new File("plugins/PrivateHideOrHunt/userdata/")).listFiles()).length, b = 0; b < i; ) {
      File config = arrayOfFile[b];
      if (config.isFile()) {
        config.delete();
        Bukkit.getLogger().info("Hide or Hunt > Deleting HoH Player Data for " + config.getName() + ".");
      } 
      b++;
    } 
  }
  
  public static void deleteHOHBeaconFiles(HideOrHunt plugin) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "beacons.yml");
    if (configFile.exists()) {
      configFile.delete();
      Bukkit.getLogger().info("Hide or Hunt > Deleting HoH Beacon Data.");
    } 
    ConfigRegistery.createPluginFiles(plugin);
  }
  
  public static void deleteHOHDataFiles(HideOrHunt plugin) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "data.yml");
    if (configFile.exists()) {
      configFile.delete();
      Bukkit.getLogger().info("Hide or Hunt > Deleting HoH Event Data.");
    } 
    ConfigRegistery.createPluginFiles(plugin);
  }
  
  public static void deleteHOHTeamFiles(HideOrHunt plugin) {
    byte b;
    int i;
    File[] arrayOfFile;
    for (i = (arrayOfFile = (new File("plugins/PrivateHideOrHunt/teamdata/")).listFiles()).length, b = 0; b < i; ) {
      File config = arrayOfFile[b];
      if (config.isFile()) {
        config.delete();
        Bukkit.getLogger().info("Hide or Hunt > Deleting HoH Team Data for " + config.getName() + ".");
      } 
      b++;
    } 
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    yamlConfiguration.set("teamsSettings.currentTeamCount", Integer.valueOf(0));
    Bukkit.getLogger().info("Hide or Hunt > Updating the Current Team Count to 0.");
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [TeamSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void resetStatus(HideOrHunt plugin) {
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    yamlConfiguration.set("gameSettings.active", Boolean.valueOf(false));
    Bukkit.getLogger().info("Hide or Hunt > Updating the Game Status.");
    yamlConfiguration.set("gameSettings.peaceful.enabled", Boolean.valueOf(false));
    Bukkit.getLogger().info("Hide or Hunt > Updating the Peace Status.");
    yamlConfiguration.set("gameSettings.bossbar.isShowing", Boolean.valueOf(false));
    Bukkit.getLogger().info("Hide or Hunt > Updating the Boss Bar Status.");
    try {
      yamlConfiguration.save(configFile);
      Bukkit.getLogger().info("Hide or Hunt > Saving Configuration Files [GameSettings].");
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
