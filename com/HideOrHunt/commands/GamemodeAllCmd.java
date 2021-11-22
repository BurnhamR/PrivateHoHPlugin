package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeAllCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public GamemodeAllCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("gamemodeall").setExecutor(this);
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
      player.sendMessage("§cOptions for /GamemodeAll");
      player.sendMessage("§c/gamemodeall creative");
      player.sendMessage("§c/gamemodeall survival");
      player.sendMessage("§c/gamemodeall spectator");
      player.sendMessage("§c/gamemodeall adventure");
      return true;
    } 
    if (args[0].toLowerCase().equals("creative") || args[0].toLowerCase().equals("c")) {
      for (Player oplayer : Bukkit.getOnlinePlayers())
        oplayer.setGameMode(GameMode.CREATIVE); 
      player.sendMessage("§aGamemode set to §nCreative§a for all!");
    } else if (args[0].toLowerCase().equals("survival") || args[0].toLowerCase().equals("s")) {
      for (Player oplayer : Bukkit.getOnlinePlayers())
        oplayer.setGameMode(GameMode.SURVIVAL); 
      player.sendMessage("§aGamemode set to §nSurvival§a for all!");
    } else if (args[0].toLowerCase().equals("spectator") || args[0].toLowerCase().equals("sp")) {
      for (Player oplayer : Bukkit.getOnlinePlayers())
        oplayer.setGameMode(GameMode.SPECTATOR); 
      player.sendMessage("§aGamemode set to §nSpectator§a for all!");
    } else if (args[0].toLowerCase().equals("adventure") || args[0].toLowerCase().equals("a")) {
      for (Player oplayer : Bukkit.getOnlinePlayers())
        oplayer.setGameMode(GameMode.ADVENTURE); 
      player.sendMessage("§aGamemode set to §nAdventure§a for all!");
    } else {
      player.sendMessage("§cOptions for /GamemodeAll");
      player.sendMessage("§c/gamemodeall creative");
      player.sendMessage("§c/gamemodeall survival");
      player.sendMessage("§c/gamemodeall spectator");
      player.sendMessage("§c/gamemodeall adventure");
      return true;
    } 
    return true;
  }
}
