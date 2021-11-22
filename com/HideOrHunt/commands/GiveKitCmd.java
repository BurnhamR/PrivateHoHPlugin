package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.utilities.KitUtilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveKitCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public GiveKitCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("givekit").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    if (args.length <= 0 || args.length > 2 || args.length == 1) {
      player.sendMessage("§cOptions for /GiveKit");
      player.sendMessage("§c/givekit <player> startinghoh");
      player.sendMessage("§c/givekit <player> deathhoh");
      return true;
    } 
    if (!player.isOp()) {
      player.sendMessage("§cYou cannot use this command!");
      return true;
    } 
    Player target = Bukkit.getPlayer(args[0]);
    if (target == null || !target.isOnline()) {
      player.sendMessage("§c§n\"" + args[0] + "\"§c is not Valid or is not Online!");
      return true;
    } 
    if (args[1].toLowerCase().equals("startinghoh") || args[1].toLowerCase().equals("deathhoh")) {
      KitUtilities.giveKits(args[1].toLowerCase(), target);
      player.sendMessage("§aKit given.");
    } else {
      player.sendMessage("§cOptions for Kitname");
      player.sendMessage("§c- startinghoh");
      player.sendMessage("§c- deathhoh");
    } 
    return true;
  }
}
