package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetPlayerCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public ResetPlayerCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("resetplayer").setExecutor(this);
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
      player.sendMessage("§cOptions for /ResetPlayer");
      player.sendMessage("§c/resetplayer <player>");
      return true;
    } 
    Player target = Bukkit.getPlayer(args[0]);
    if (target == null || !target.isOnline()) {
      player.sendMessage("§c§n\"" + args[0] + "\"§c is not Valid or is not Online!");
      return true;
    } 
    PlayerRegistery.resetDefaultValues(this.plugin, target);
    player.sendMessage("§a" + target.getName() + "'s Data was §nReset§a!");
    return true;
  }
}
