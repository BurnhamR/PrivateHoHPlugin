package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public GamemodeCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("gamemode").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player target, player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    if (!player.isOp()) {
      player.sendMessage("§cYou cannot use this command!");
      return true;
    } 
    if (args.length <= 0 || args.length > 2) {
      player.sendMessage("§cOptions for /Gamemode");
      player.sendMessage("§c/gamemode creative [player]");
      player.sendMessage("§c/gamemode survival [player]");
      player.sendMessage("§c/gamemode spectator [player]");
      player.sendMessage("§c/gamemode adventure [player]");
      return true;
    } 
    if (args.length == 2) {
      target = Bukkit.getPlayer(args[1]);
      if (target == null || !target.isOnline()) {
        player.sendMessage("§c§n\"" + args[1] + "\"§c is not Valid or is not Online!");
        return true;
      } 
    } else {
      target = player;
    } 
    if (args[0].toLowerCase().equals("creative") || args[0].toLowerCase().equals("c")) {
      target.setGameMode(GameMode.CREATIVE);
      player.sendMessage("§aGamemode set to §nCreative§a!");
    } else if (args[0].toLowerCase().equals("survival") || args[0].toLowerCase().equals("s")) {
      target.setGameMode(GameMode.SURVIVAL);
      player.sendMessage("§aGamemode set to §nSurvival§a!");
    } else if (args[0].toLowerCase().equals("spectator") || args[0].toLowerCase().equals("sp")) {
      target.setGameMode(GameMode.SPECTATOR);
      player.sendMessage("§aGamemode set to §nSpectator§a!");
    } else if (args[0].toLowerCase().equals("adventure") || args[0].toLowerCase().equals("a")) {
      target.setGameMode(GameMode.ADVENTURE);
      player.sendMessage("§aGamemode set to §nAdventure§a!");
    } else {
      player.sendMessage("§cOptions for /Gamemode");
      player.sendMessage("§c/gamemode creative [player]");
      player.sendMessage("§c/gamemode survival [player]");
      player.sendMessage("§c/gamemode spectator [player]");
      player.sendMessage("§c/gamemode adventure [player]");
      return true;
    } 
    return true;
  }
}
