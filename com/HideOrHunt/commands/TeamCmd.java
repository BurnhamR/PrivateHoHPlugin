package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.config.PlayerRegistery;
import com.HideOrHunt.config.TeamRegistery;
import com.HideOrHunt.utilities.TeamUtilities;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class TeamCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public TeamCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("team").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (args.length == 0) {
      player.sendMessage("§d§nTeam Help:");
      player.sendMessage("§6 - /team create <name> §8- §fCreates a team");
      player.sendMessage("§6 - /team rename <name> §8- §fRenames your team");
      player.sendMessage("§6 - /team invite <player> §8- §fInvites a player to your team");
      player.sendMessage("§6 - /team accept §8- §fAccepts an active invite to join a team");
      player.sendMessage("§6 - /team deny §8- §fDenies an active invite to join a team");
      player.sendMessage("§6 - /team leave  §8- §fLeaves your current team");
      player.sendMessage("§6 - /team disband  §8- §fDisbands your current team");
      return true;
    } 
    if (args[0].toLowerCase().equals("create")) {
      if (args.length < 2 || args.length > 2) {
        player.sendMessage("§d§nTeam Help:");
        player.sendMessage("§6 - /team create <name> §8- §fCreates a team");
        return true;
      } 
      if (TeamUtilities.isTeamCreateValid((FileConfiguration)yamlConfiguration, player))
        if (TeamUtilities.isTeamNameValid((FileConfiguration)yamlConfiguration, args[1], player)) {
          TeamRegistery.registerTeam(this.plugin, player, args[1]);
          Bukkit.broadcastMessage("§a§n" + args[1] + "§a was created!");
        } else {
          return true;
        }  
    } else if (args[0].toLowerCase().equals("rename")) {
      if (args.length < 2 || args.length > 2) {
        player.sendMessage("§d§nTeam Help:");
        player.sendMessage("§6 - /team rename <name> §8- §fRenames your team");
        return true;
      } 
      if (PlayerRegistery.isOnTeam(this.plugin, player)) {
        if (TeamRegistery.isTeamLeader(this.plugin, PlayerRegistery.getPlayerTeamUUID(this.plugin, player), player)) {
          if (TeamUtilities.isTeamNameValid((FileConfiguration)yamlConfiguration, args[1], player)) {
            Bukkit.broadcastMessage("§a§n" + TeamRegistery.getTeamName(this.plugin, PlayerRegistery.getPlayerTeamUUID(this.plugin, player)) + "§a was renamed to §n" + args[1] + "§a!");
            TeamRegistery.updateTeamName(this.plugin, PlayerRegistery.getPlayerTeamUUID(this.plugin, player), args[1]);
          } else {
            return true;
          } 
        } else {
          player.sendMessage("§cYou must be the Team Leader to Rename the Team!");
          return true;
        } 
      } else {
        player.sendMessage("§cYou must be on a Team to Rename a Team!");
        return true;
      } 
    } else if (args[0].toLowerCase().equals("invite")) {
      if (args.length < 2 || args.length > 2) {
        player.sendMessage("§d§nTeam Help:");
        player.sendMessage("§6 - /team invite <player> §8- §fInvites a player to your team");
        return true;
      } 
      if (PlayerRegistery.isOnTeam(this.plugin, player)) {
        if (TeamRegistery.isTeamLeader(this.plugin, PlayerRegistery.getPlayerTeamUUID(this.plugin, player), player)) {
          Player target = Bukkit.getPlayer(args[1]);
          if (TeamUtilities.isTeamInviteValid((FileConfiguration)yamlConfiguration, player, target, args[1])) {
            PlayerRegistery.updatePlayerInvitingTeam(this.plugin, target, PlayerRegistery.getPlayerTeamUUID(this.plugin, player), true);
            TeamUtilities.sendTeamMessagePlayer(player, "§a§n" + PlayerRegistery.getNickname(this.plugin, player) + "§a invited §a§n" + PlayerRegistery.getNickname(this.plugin, target) + "§a to the Team!");
            if (target.isOnline()) {
              target.sendMessage("§aYou have been invited to join §n" + TeamRegistery.getTeamName(this.plugin, PlayerRegistery.getPlayerTeamUUID(this.plugin, player)) + "§a.");
              target.sendMessage("§d§nTeam Help:");
              target.sendMessage("§6 - /team accept §8- §fAccepts the invite");
              target.sendMessage("§6 - /team deny §8- §fDenies the invite");
            } 
          } else {
            return true;
          } 
        } else {
          player.sendMessage("§cYou must be the Team Leader to Invite Players to the Team!");
          return true;
        } 
      } else {
        player.sendMessage("§cYou must be on a Team to Invite Players to the Team!");
        return true;
      } 
    } else if (args[0].toLowerCase().equals("accept")) {
      if (args.length < 1 || args.length > 1) {
        player.sendMessage("§d§nTeam Help:");
        player.sendMessage("§6 - /team accept §8- §fAccepts an Invite to join a team");
        return true;
      } 
      if (TeamUtilities.isFutureTeamStatusValid((FileConfiguration)yamlConfiguration, player)) {
        TeamRegistery.registerPlayer(this.plugin, player, PlayerRegistery.getPlayerInvitingTeamUUID(this.plugin, player), false);
        PlayerRegistery.updatePlayerInvitingTeam(this.plugin, player, "-1", false);
        TeamUtilities.sendTeamMessagePlayer(player, "§a§n" + PlayerRegistery.getNickname(this.plugin, player) + "§a joined the Team!");
      } 
    } else if (args[0].toLowerCase().equals("deny")) {
      if (args.length < 1 || args.length > 1) {
        player.sendMessage("§d§nTeam Help:");
        player.sendMessage("§6 - /team deny §8- §fDenies an Invite to join a team");
        return true;
      } 
      if (TeamUtilities.isFutureTeamStatusValid((FileConfiguration)yamlConfiguration, player)) {
        TeamUtilities.sendTeamMessageUUID(PlayerRegistery.getPlayerInvitingTeamUUID(this.plugin, player), "§a§n" + PlayerRegistery.getNickname(this.plugin, player) + "§a denied the Team Invite!");
        player.sendMessage("§aYou denied the Team Invite!");
        PlayerRegistery.updatePlayerInvitingTeam(this.plugin, player, "-1", false);
      } 
    } else if (args[0].toLowerCase().equals("leave")) {
      if (args.length < 1 || args.length > 1) {
        player.sendMessage("§d§nTeam Help:");
        player.sendMessage("§6 - /team leave §8- §fLeaves your current team");
        return true;
      } 
      if (TeamUtilities.isLeavingValid((FileConfiguration)yamlConfiguration, player)) {
        TeamUtilities.sendTeamMessagePlayer(player, "§a§n" + PlayerRegistery.getNickname(this.plugin, player) + "§a left the Team!");
        TeamRegistery.removePlayer(this.plugin, player, PlayerRegistery.getPlayerTeamUUID(this.plugin, player));
      } 
    } else if (args[0].toLowerCase().equals("disband")) {
      if (args.length < 1 || args.length > 1) {
        player.sendMessage("§d§nTeam Help:");
        player.sendMessage("§6 - /team disband  §8- §fDisbands your current team");
        return true;
      } 
      if (TeamUtilities.isDisbandValid((FileConfiguration)yamlConfiguration, player)) {
        Bukkit.broadcastMessage("§a§n" + TeamRegistery.getTeamName(this.plugin, PlayerRegistery.getPlayerTeamUUID(this.plugin, player)) + "§a was disbanded!");
        TeamRegistery.removeTeam(this.plugin, player, PlayerRegistery.getPlayerTeamUUID(this.plugin, player));
      } 
    } else {
      player.sendMessage("§d§nTeam Help:");
      player.sendMessage("§6 - /team create <name> §8- §fCreates a team");
      player.sendMessage("§6 - /team rename <name> §8- §fRenames your team");
      player.sendMessage("§6 - /team invite <player> §8- §fInvites a player to your team");
      player.sendMessage("§6 - /team accept §8- §fAccepts an active invite to join a team");
      player.sendMessage("§6 - /team deny §8- §fDenies an active invite to join a team");
      player.sendMessage("§6 - /team leave  §8- §fLeaves your current team");
      player.sendMessage("§6 - /team disband  §8- §fDisbands your current team");
      return true;
    } 
    return true;
  }
}
