package com.HideOrHunt.commands;

import com.HideOrHunt.HideOrHunt;
import com.HideOrHunt.menus.ServerSettingsMenu;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetMessagesCmd implements CommandExecutor {
  private HideOrHunt plugin;
  
  public SetMessagesCmd(HideOrHunt plugin) {
    this.plugin = plugin;
    plugin.getCommand("setmessages").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player player = (Player)sender;
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players may execute this command!");
      return true;
    } 
    if (args.length <= 0 || args.length > 1) {
      player.sendMessage("§cOptions for /SetMessages");
      player.sendMessage("§c/setmessages entry");
      player.sendMessage("§c/setmessages death");
      return true;
    } 
    if (!player.isOp()) {
      player.sendMessage("§cYou cannot use this command!");
      return true;
    } 
    File configFile = new File("plugins/PrivateHideOrHunt/", "gamesettings.yml");
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
    if (args[0].toLowerCase().equals("entry")) {
      player.openInventory(ServerSettingsMenu.inventoryEntryMessages((FileConfiguration)yamlConfiguration));
    } else if (args[0].toLowerCase().equals("death")) {
      player.openInventory(ServerSettingsMenu.inventoryDeathMessages((FileConfiguration)yamlConfiguration));
    } else {
      player.sendMessage("§cOptions for /SetMessages");
      player.sendMessage("§c/setmessages entry");
      player.sendMessage("§c/setmessages death");
      return true;
    } 
    return true;
  }
}
