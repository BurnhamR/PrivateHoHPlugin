package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.TeamRegistery;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViewTeamsCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public ViewTeamsCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("viewteams").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    String symbol = "";
    byte b;
    int i;
    File[] arrayOfFile;
    for (i = (arrayOfFile = (new File("plugins/PrivateHideOrHunt/teamdata/")).listFiles()).length, b = 0; b < i; ) {
      File configFile = arrayOfFile[b];
      if (configFile.isFile()) {
        if (TeamRegistery.getTeamBeaconStatus(this.plugin, configFile.getName().replace(".yml", "")).equals("pending"))
          symbol = "§cNot Placed"; 
        if (TeamRegistery.getTeamBeaconStatus(this.plugin, configFile.getName().replace(".yml", "")).equals("placed"))
          symbol = "§aAlive"; 
        if (TeamRegistery.getTeamBeaconStatus(this.plugin, configFile.getName().replace(".yml", "")).equals("destroyed"))
          symbol = "§cLast Life"; 
        if (TeamRegistery.isTeamEliminated(this.plugin, configFile.getName().replace(".yml", "")))
          symbol = "§4Eliminated"; 
        player.sendMessage("§c" + TeamRegistery.getTeamName(this.plugin, configFile.getName().replace(".yml", "")) + ": " + symbol);
      } 
      b++;
    } 
    return true;
  }
}
