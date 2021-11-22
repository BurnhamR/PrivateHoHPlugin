package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class BorderCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public BorderCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("border").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    if (args.length <= 0 || args.length > 1) {
      player.sendMessage("§cOptions for /Border");
      player.sendMessage("§c/border <size>");
      return true;
    } 
    if (!player.isOp()) {
      player.sendMessage("§cYou cannot use this command!");
      return true;
    } 
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (isNumeric(args[0])) {
      double size = Double.parseDouble(args[0]);
      Bukkit.getWorld(player.getWorld().getName()).getWorldBorder().setCenter(player.getLocation());
      Bukkit.getWorld(player.getWorld().getName()).getWorldBorder().setSize(size);
      Location newSpawn = new Location(player.getWorld(), player.getLocation().getX(), Bukkit.getWorld(player.getWorld().getName()).getHighestBlockYAt(player.getLocation()), player.getLocation().getZ());
      Bukkit.getServer().getWorld(player.getLocation().getWorld().getName()).setSpawnLocation(newSpawn);
      if (yamlConfiguration.getBoolean("gameSettings.active")) {
        for (Player oPlayer : Bukkit.getOnlinePlayers()) {
          if (PlayerRegistery.isOnTeam(HideOrHunt.getInstance(), oPlayer)) {
            if (TeamRegistery.getTeamBeaconStatus(HideOrHunt.getInstance(), PlayerRegistery.getPlayerTeamUUID(HideOrHunt.getInstance(), oPlayer)).equals("placed"))
              continue; 
            PlayerRegistery.setSpawnPoint(HideOrHunt.getInstance(), oPlayer, newSpawn);
            continue;
          } 
          PlayerRegistery.setSpawnPoint(HideOrHunt.getInstance(), oPlayer, newSpawn);
        } 
      } else {
        for (Player oPlayer : Bukkit.getOnlinePlayers())
          PlayerRegistery.setSpawnPoint(HideOrHunt.getInstance(), oPlayer, newSpawn); 
      } 
      player.sendMessage("§aThe World Border was set to §n" + size + "§a blocks.");
      player.sendMessage("§aThe Spawn Point was Defined.");
    } else {
      player.sendMessage("§c§n\"" + args[0] + "\"§c is not a Number!");
      return true;
    } 
    return true;
  }
  
  public static boolean isNumeric(String strNum) {
    if (strNum == null)
      return false; 
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    } 
    return true;
  }
}
