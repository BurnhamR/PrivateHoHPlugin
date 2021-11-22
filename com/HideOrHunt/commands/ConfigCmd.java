package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.ConfigRegistery;
import com.HideOrHunt.config.PlayerRegistery;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public ConfigCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("config").setExecutor(this);
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
      player.sendMessage("§cOptions for /Config");
      player.sendMessage("§c/config list");
      player.sendMessage("§c/config reload");
      return true;
    } 
    if (args[0].toLowerCase().equals("list")) {
      ConfigRegistery.listPluginFiles(this.plugin, player);
    } else if (args[0].toLowerCase().equals("reload")) {
      ConfigRegistery.loadPluginFiles(this.plugin);
      for (Player oplayer : Bukkit.getOnlinePlayers())
        PlayerRegistery.updatePlayerFile(this.plugin, oplayer); 
      player.sendMessage("§aConfig Reloaded");
    } else {
      player.sendMessage("§cOptions for /Config");
      player.sendMessage("§c/config list");
      player.sendMessage("§c/config reload");
      return true;
    } 
    return true;
  }
}
