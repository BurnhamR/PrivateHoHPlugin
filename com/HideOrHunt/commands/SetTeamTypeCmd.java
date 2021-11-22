package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.menus.GameSettingsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTeamTypeCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public SetTeamTypeCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("setteamtype").setExecutor(this);
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
    player.openInventory(GameSettingsMenu.inventoryTeamType());
    return true;
  }
}
